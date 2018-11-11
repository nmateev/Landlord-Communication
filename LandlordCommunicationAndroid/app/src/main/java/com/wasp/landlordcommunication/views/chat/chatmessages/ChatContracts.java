package com.wasp.landlordcommunication.views.chat.chatmessages;

import android.graphics.Bitmap;

import com.wasp.landlordcommunication.models.ChatMessage;

import java.util.List;

public interface ChatContracts {

    interface View {

        void setPresenter(Presenter presenter);

        void showProgressBar();

        void hideProgressBar();

        void showError(Throwable error);

        void showMessage(String message);

        void setLoggedInUserToAdapter(int userId);

        void setUserImageToShowInAdapter(Bitmap userImage);

        void showChatMessages(List<ChatMessage> chatMessages);

        void showNewMessage(ChatMessage newMessage);

        void scrollChatToBottom();

        void clearMessageTextInput();

        void showTemplateMessages(List<String> templateMessages);

        void setTextToMessageInput(String pickedTemplate);

        void dismissTemplatePicker();

        void shakeUserOptionsLayout();

        void presentOptionToTakePicture();

        void showImage(String imageMessage);

        void showNewChatMessages(List<ChatMessage> chatMessages);
    }

    interface Presenter {

        void subscribe(View view);

        void unsubscribe();

        void setUserId(int userId);

        void setUserType(String userType);

        void setChatSessionId(int chatSessionId);

        void setFirstChatMember(int firstChatMemberId);

        void setSecondChatMember(int secondChatMemberId);

        void loadChatSessionMessages();

        void checkIfChatSessionAlreadyExistsBetweenUsersAndLoadMessages(int mTenantId, int mLandlordId);

        void setChatSessionTenantId(int tenantId);

        void setChatSessionLandlordId(int landlordId);

        void stopChatLooping();

        void sendButtonIsClicked(String message);

        void templatePickerIsClickedWithPreference(String preference);

        void templateMessageIsSelected(String pickedTemplate);

        void takePictureButtonIsClicked();

        void errorOccurredOnTakingPicture();

        void pictureIsTaken(Bitmap image);

        void chatMessageIsClicked(ChatMessage chatMessage);
    }

    interface Navigator {
        void navigateToImageView(String imageMessage);
    }
}
