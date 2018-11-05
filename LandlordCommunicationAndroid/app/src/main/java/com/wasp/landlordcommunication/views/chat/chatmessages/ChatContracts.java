package com.wasp.landlordcommunication.views.chat.chatmessages;

public interface ChatContracts {

    interface View {

        void setPresenter(Presenter presenter);

        void showProgressBar();

        void hideProgressBar();

        void showError(Throwable error);

        void showMessage(String message);

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
    }
}
