package com.wasp.landlordcommunication.views.chat.chatmessages;

import android.graphics.Bitmap;

import com.wasp.landlordcommunication.async.base.SchedulerProvider;
import com.wasp.landlordcommunication.models.ChatMessage;
import com.wasp.landlordcommunication.models.ChatSession;
import com.wasp.landlordcommunication.models.TemplateMessage;
import com.wasp.landlordcommunication.services.base.ChatMessagesService;
import com.wasp.landlordcommunication.services.base.ChatSessionsService;
import com.wasp.landlordcommunication.services.base.TemplateMessagesService;
import com.wasp.landlordcommunication.utils.Constants;
import com.wasp.landlordcommunication.utils.base.ImageEncoder;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Objects;
import java.util.concurrent.TimeUnit;

import javax.inject.Inject;

import io.reactivex.Observable;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.disposables.Disposable;

import static com.wasp.landlordcommunication.utils.Constants.TENANT;

public class ChatPresenter implements ChatContracts.Presenter {
    private static final int LOOPER_DELAY_AMOUNT_SECONDS = 4;

    private final ChatSessionsService mChatSessionsService;
    private final ChatMessagesService mChatMessagesService;
    private final TemplateMessagesService mTemplateMessagesService;
    private final SchedulerProvider mSchedulerProvider;
    private final ImageEncoder mImageEncoder;
    private ChatContracts.View mView;
    private Disposable mChatLooperDisposable;


    private int mUserId;
    private String mUserType;

    //used when the user navigates to chat activity from chat lists
    private int mChatSessionId;
    private int mChatSessionTenantId;
    private int mChatSessionLandlordId;

    //used when the user navigates to chat activity from property management
    private int mFirstChatMemberId;
    private int mSecondChatMemberId;

    //used as final identification for the chat
    private int mChatId;
    private int mTenantId;
    private int mLandlordId;


    @Inject
    public ChatPresenter(ChatSessionsService chatSessionsService, ChatMessagesService chatMessagesService, TemplateMessagesService templateMessagesService, SchedulerProvider schedulerProvider, ImageEncoder imageEncoder) {
        mChatSessionsService = chatSessionsService;
        mChatMessagesService = chatMessagesService;
        mTemplateMessagesService = templateMessagesService;
        mSchedulerProvider = schedulerProvider;
        mImageEncoder = imageEncoder;
    }

    @Override
    public void subscribe(ChatContracts.View view) {
        mView = view;
    }

    @Override
    public void unsubscribe() {
        mView = null;
    }

    @Override
    public void setUserId(int userId) {
        mUserId = userId;
    }

    @Override
    public void setUserType(String userType) {
        mUserType = userType;
    }

    @Override
    public void setChatSessionId(int chatSessionId) {
        mChatSessionId = chatSessionId;
    }

    @Override
    public void setFirstChatMember(int firstChatMemberId) {
        mFirstChatMemberId = firstChatMemberId;
    }

    @Override
    public void setSecondChatMember(int secondChatMemberId) {
        mSecondChatMemberId = secondChatMemberId;
    }

    @Override
    public void setChatSessionTenantId(int tenantId) {
        mChatSessionTenantId = tenantId;
    }

    @Override
    public void setChatSessionLandlordId(int landlordId) {
        mChatSessionLandlordId = landlordId;
    }

    @Override
    public void stopChatLooping() {
        mChatLooperDisposable.dispose();
    }

    @Override
    public void loadChatSessionMessages() {

       /* If the chat session id is different from 0 the user navigated from his chat lists activity and there is no need to
       check if the chat session between the users exists and we load the messages from the session directly */
        if (mChatSessionId != 0) {
            mChatId = mChatSessionId;
            mTenantId = mChatSessionTenantId;
            mLandlordId = mChatSessionLandlordId;
            getChatSessionByTenantAndLandlordAndLoadMessages(mTenantId, mLandlordId);

        } else if ((mFirstChatMemberId != 0) && (mSecondChatMemberId != 0)) {
           /* If the first chat member id and the second chat member id are different from zero then the user selected the
            option to navigate to chat through property management view. In this case we have to check if the chat sessions
            between the users already exists and if not, first create it and then show chat view*/
            assignTenantAndLandlordId(mFirstChatMemberId, mSecondChatMemberId);
            checkIfChatSessionAlreadyExistsBetweenUsersAndLoadMessages(mTenantId, mLandlordId);
        } else {
            mView.showMessage(Constants.UNEXPECTED_ERROR);
        }

    }

    @Override
    public void checkIfChatSessionAlreadyExistsBetweenUsersAndLoadMessages(int tenantId, int landlordId) {

        mView.showProgressBar();
        Disposable observable = Observable
                .create((ObservableOnSubscribe<Boolean>) emitter -> {
                    boolean isChatSessionsAlreadyCreated = mChatSessionsService.isChatSessionCreatedBetweenTenantAndLandlord(new ChatSession(tenantId, landlordId));
                    emitter.onNext(isChatSessionsAlreadyCreated);
                    emitter.onComplete();
                })
                .subscribeOn(mSchedulerProvider.backgroundThread())
                .observeOn(mSchedulerProvider.uiThread())
                .doFinally(mView::hideProgressBar)
                .subscribe(isChatSessionsAlreadyCreated -> {
                    if (isChatSessionsAlreadyCreated) {
                        //chat session already exists
                        getChatSessionByTenantAndLandlordAndLoadMessages(tenantId, landlordId);
                    } else {
                        createChatSessionBetweenTenantAndLandlordAndLoadMessages(mTenantId, mLandlordId);
                    }
                }, error -> mView.showError(error));


    }

    private void createChatSessionBetweenTenantAndLandlordAndLoadMessages(int tenantId, int landlordId) {
        ChatSession chatSessionToCreate = new ChatSession(tenantId, landlordId);

        mView.showProgressBar();
        Disposable observable = Observable
                .create((ObservableOnSubscribe<ChatSession>) emitter -> {
                    ChatSession createdChatSession = mChatSessionsService.createChatSessionBetweenTenantAndLandlord(chatSessionToCreate);
                    emitter.onNext(createdChatSession);
                    emitter.onComplete();
                })
                .subscribeOn(mSchedulerProvider.backgroundThread())
                .observeOn(mSchedulerProvider.uiThread())
                .doFinally(mView::hideProgressBar)
                .subscribe(createdChatSession ->
                                getChatSessionByTenantAndLandlordAndLoadMessages(tenantId, landlordId),
                        error -> mView.showError(error));

    }

    private void getChatSessionByTenantAndLandlordAndLoadMessages(int tenantId, int landlordId) {

        mView.showProgressBar();
        Disposable observable = Observable
                .create((ObservableOnSubscribe<ChatSession>) emitter -> {
                    ChatSession chatSession = mChatSessionsService.getChatSessionByTenantAndLandlord(new ChatSession(tenantId, landlordId));
                    emitter.onNext(chatSession);
                    emitter.onComplete();
                })
                .subscribeOn(mSchedulerProvider.backgroundThread())
                .observeOn(mSchedulerProvider.uiThread())
                .doFinally(mView::hideProgressBar)
                .subscribe(chatSessionResult ->
                                loadInitialMessagesForChatSession(chatSessionResult, tenantId, landlordId),
                        error -> mView.showError(error));
    }

    private void loadInitialMessagesForChatSession(ChatSession chatSession, int tenantId, int landlordId) {
        mView.showProgressBar();
        Bitmap userImage = null;
        if (mUserId == tenantId) {
            //If the logged in user is the tenant we have to show the landlords image in chat
            if (!Objects.equals(chatSession.getLandlord().getUserPicture(), null)) {
                userImage = mImageEncoder.decodeStringToBitmap(chatSession.getLandlord().getUserPicture());
            }
        } else {
            //we have to show the tenants image in chat
            if (!Objects.equals(chatSession.getTenant().getUserPicture(), null)) {
                userImage = mImageEncoder.decodeStringToBitmap(chatSession.getTenant().getUserPicture());
            }

        }
        mView.setLoggedInUserToAdapter(mUserId);
        mView.setUserImageToShowInAdapter(userImage);


        mView.showProgressBar();
        int chatSessionId = chatSession.getChatSessionId();

        mChatId = chatSessionId;

        Disposable observable = Observable
                .create((ObservableOnSubscribe<List<ChatMessage>>) emitter -> {
                    List<ChatMessage> chatMessages = mChatMessagesService.getChatMessagesByChatSessionId(chatSessionId);
                    emitter.onNext(chatMessages);
                    emitter.onComplete();
                })
                .subscribeOn(mSchedulerProvider.backgroundThread())
                .observeOn(mSchedulerProvider.uiThread())
                .doFinally(mView::hideProgressBar)
                .subscribe(chatMessages -> {
                    if (!chatMessages.isEmpty()) {
                        mView.showChatMessages(chatMessages);
                        mView.scrollChatToBottom();
                        updateDeliveredStatusToMessagesForLoggedInUser(chatMessages);
                    }

                }, error -> mView.showError(error));


        loadUndeliveredMessagesByUserTypeFromChatSessionIdLooper(mChatId);

    }

    private void loadUndeliveredMessagesByUserTypeFromChatSessionIdLooper(int chatSessionId) {


        mChatLooperDisposable = Observable
                .create((ObservableOnSubscribe<List<ChatMessage>>) emitter -> {
                    List<ChatMessage> chatMessages = mChatMessagesService.getUndeliveredMessagesByChatSessionIdAndUserType(chatSessionId, mUserType);
                    emitter.onNext(chatMessages);
                    emitter.onComplete();
                })
                .subscribeOn(mSchedulerProvider.backgroundThread())
                .observeOn(mSchedulerProvider.uiThread())
                .doFinally(mView::hideProgressBar)
                .repeatWhen(completed -> completed.delay(LOOPER_DELAY_AMOUNT_SECONDS, TimeUnit.SECONDS))
                .subscribe(chatMessages -> {
                    if (!chatMessages.isEmpty()) {
                        mView.showNewChatMessages(chatMessages);
                        mView.scrollChatToBottom();
                        updateDeliveredStatusToMessagesForLoggedInUser(chatMessages);
                    }

                }, error -> mView.showError(error));

    }

    private void updateDeliveredStatusToMessagesForLoggedInUser(List<ChatMessage> chatMessages) {

        for (ChatMessage message : chatMessages) {
            if (mUserType.equals(TENANT)) {
                if (!message.getDeliveredToTenant()) {
                    message.setDeliveredToTenant(true);
                    updateMessageStatus(message);
                }

            } else {
                if (!message.getDeliveredToLandlord()) {
                    message.setDeliveredToLandlord(true);
                    updateMessageStatus(message);
                }
            }
        }
    }

    private void updateMessageStatus(ChatMessage message) {
        Disposable observable = Observable
                .create((ObservableOnSubscribe<ChatMessage>) emitter -> {
                    ChatMessage chatMessagesToUpdate = mChatMessagesService.updateDeliveredStatusForChatMessage(message);
                    emitter.onNext(chatMessagesToUpdate);
                    emitter.onComplete();
                })
                .subscribeOn(mSchedulerProvider.backgroundThread())
                .observeOn(mSchedulerProvider.uiThread())
                .subscribe(chatMessage -> {
                }, error -> mView.showError(error));
    }

    @Override
    public void sendButtonIsClicked(String message) {
        if (message.length() == 0) {
            mView.shakeUserOptionsLayout();
            return;
        }

        mView.clearMessageTextInput();

        ChatMessage newChatMessage;
        if (mUserType.equals(TENANT)) {
            newChatMessage = new ChatMessage(mTenantId, mLandlordId, mUserId, mChatId, new Date(), message, null, true, false);
        } else {
            newChatMessage = new ChatMessage(mTenantId, mLandlordId, mUserId, mChatId, new Date(), message, null, false, true);
        }


        Disposable observable = Observable
                .create((ObservableOnSubscribe<ChatMessage>) emitter -> {
                    ChatMessage chatMessageToPost = mChatMessagesService.postChatMessage(newChatMessage);
                    emitter.onNext(chatMessageToPost);
                    emitter.onComplete();
                })
                .subscribeOn(mSchedulerProvider.backgroundThread())
                .observeOn(mSchedulerProvider.uiThread())
                .subscribe(postedChatMessage -> {
                            mView.showNewMessage(postedChatMessage);
                            mView.scrollChatToBottom();
                        },
                        error -> mView.showError(error));


    }

    @Override
    public void templatePickerIsClickedWithPreference(String preference) {

        //in the case there is no preference set already the default preference is Normal
        if (preference.equals(Constants.EMPTY_STRING)) {
            preference = Constants.NORMAL;
        }
        //else use selected preference

        String templateType = preference;

        Disposable observable = Observable
                .create((ObservableOnSubscribe<List<String>>) emitter -> {
                    List<String> templateMessages = mTemplateMessagesService.getAllTemplateMessagesByType(templateType);
                    emitter.onNext(templateMessages);
                    emitter.onComplete();
                })
                .subscribeOn(mSchedulerProvider.backgroundThread())
                .observeOn(mSchedulerProvider.uiThread())
                .subscribe(templateMessagesResult ->
                                mView.showTemplateMessages(templateMessagesResult),
                        error -> mView.showError(error));

    }

    @Override
    public void templateMessageIsSelected(String pickedTemplate) {
        mView.setTextToMessageInput(pickedTemplate);
        mView.dismissTemplatePicker();
    }

    @Override
    public void takePictureButtonIsClicked() {
        mView.presentOptionToTakePicture();
    }

    @Override
    public void errorOccurredOnTakingPicture() {
        mView.showMessage(Constants.IMAGE_CAPTURE_FAILURE);
    }

    @Override
    public void pictureIsTaken(Bitmap image) {

        String imageInString = mImageEncoder.encodeBitmapToString(image);

        ChatMessage newChatMessage;

        if (mUserType.equals(TENANT)) {
            newChatMessage = new ChatMessage(mTenantId, mLandlordId, mUserId, mChatId, new Date(), Constants.IMAGE_CHAT_MESSAGE, imageInString, true, false);
        } else {
            newChatMessage = new ChatMessage(mTenantId, mLandlordId, mUserId, mChatId, new Date(), Constants.IMAGE_CHAT_MESSAGE, imageInString, false, true);
        }

        Disposable observable = Observable
                .create((ObservableOnSubscribe<ChatMessage>) emitter -> {
                    ChatMessage chatMessageToPost = mChatMessagesService.postChatMessage(newChatMessage);
                    emitter.onNext(chatMessageToPost);
                    emitter.onComplete();
                })
                .subscribeOn(mSchedulerProvider.backgroundThread())
                .observeOn(mSchedulerProvider.uiThread())
                .subscribe(postedChatMessage -> {
                            mView.showNewMessage(postedChatMessage);
                            mView.scrollChatToBottom();
                        },
                        error -> mView.showError(error));
    }

    @Override
    public void chatMessageIsClicked(ChatMessage chatMessage) {
        if (Objects.equals(chatMessage.getImageMessage(), null)) {
            return;
        }

        mView.showImage(chatMessage.getImageMessage());

    }

    private void assignTenantAndLandlordId(int mFirstChatMemberId, int mSecondChatMemberId) {
        //mUserId  is the id of the logged user in the app
        //mUserType the user account type of the logged in user

        if (mUserType.equals(TENANT)) {
            mTenantId = mUserId;
            if (mFirstChatMemberId == mUserId) {
                mLandlordId = mSecondChatMemberId;
            } else {
                mLandlordId = mFirstChatMemberId;
            }

        } else {
            //in the case the landlord is the logged in user
            mLandlordId = mUserId;

            if (mFirstChatMemberId == mUserId) {
                mTenantId = mSecondChatMemberId;
            } else {
                mTenantId = mFirstChatMemberId;
            }
        }
    }
}

