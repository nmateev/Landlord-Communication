package com.wasp.landlordcommunication.views.chat;

import com.wasp.landlordcommunication.models.ChatSession;

import java.util.List;

public interface ChatListContracts {

    interface View {

        void setPresenter(Presenter presenter);

        void showProgressBar();

        void hideProgressBar();

        void showError(Throwable error);

        void showMessage(String message);

        void showEmptyChatSessionsMessage(String message);

        void showChatSessionsList(List<ChatSession> chatSessions);

        void setLoggedInUserIdToAdapter(int userId);

        void showChatMessages(int chatSessionId, int tenantId, int landlordId);
    }

    interface Presenter {

        void subscribe(View view);

        void unsubscribe();

        void setUserId(int userId);

        void setUserType(String userType);

        void loadChatSessions();

        void chatSessionIsSelected(ChatSession chatSession);
    }

    interface Navigator {

        void navigateToChat(int chatSessionId, int tenantId, int landlordId);
    }
}
