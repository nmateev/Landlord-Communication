package com.wasp.landlordcommunication.views.chat;

import com.wasp.landlordcommunication.async.base.SchedulerProvider;
import com.wasp.landlordcommunication.models.ChatSession;
import com.wasp.landlordcommunication.services.base.ChatSessionsService;
import com.wasp.landlordcommunication.utils.Constants;
import com.wasp.landlordcommunication.utils.base.ImageEncoder;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.Observable;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.disposables.Disposable;

public class ChatListPresenter implements ChatListContracts.Presenter {

    private final ChatSessionsService mChatSessionsService;
    private final SchedulerProvider mSchedulerProvider;
    private final ImageEncoder mImageEncoder;
    private ChatListContracts.View mView;
    private int mUserId;
    private String mUserType;

    @Inject
    public ChatListPresenter(ChatSessionsService chatSessionsService, SchedulerProvider schedulerProvider, ImageEncoder imageEncoder) {
        mChatSessionsService = chatSessionsService;
        mSchedulerProvider = schedulerProvider;
        mImageEncoder = imageEncoder;
    }

    @Override
    public void subscribe(ChatListContracts.View view) {
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
    public void loadChatSessions() {
        mView.setLoggedInUserIdToAdapter(mUserId);


        mView.showProgressBar();
        Disposable observable = Observable
                .create((ObservableOnSubscribe<List<ChatSession>>) emitter -> {
                    List<ChatSession> chatSessions = mChatSessionsService.getAllChatSessionsByUserTypeAndId(mUserType, mUserId);
                    emitter.onNext(chatSessions);
                    emitter.onComplete();
                })
                .subscribeOn(mSchedulerProvider.backgroundThread())
                .observeOn(mSchedulerProvider.uiThread())
                .doFinally(mView::hideProgressBar)
                .subscribe(this::presentChatSessionsToView, error -> mView.showError(error));

    }

    @Override
    public void chatSessionIsSelected(ChatSession chatSession) {
        mView.showChatMessages(chatSession.getChatSessionId(), chatSession.getTenantId(), chatSession.getLandlordId());
    }

    private void presentChatSessionsToView(List<ChatSession> chatSessions) {
        if (chatSessions.isEmpty()) {
            mView.showEmptyChatSessionsMessage(Constants.NO_CHAT_SESSIONS_MESSAGE);
        } else {
            mView.showChatSessionsList(chatSessions);
        }
    }
}
