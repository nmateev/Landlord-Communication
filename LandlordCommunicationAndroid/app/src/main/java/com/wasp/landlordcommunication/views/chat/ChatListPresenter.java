package com.wasp.landlordcommunication.views.chat;

import javax.inject.Inject;

public class ChatListPresenter implements ChatListContracts.Presenter {


    private ChatListContracts.View mView;
    private int mUserId;
    private String mUserType;

    @Inject
    public ChatListPresenter() {

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

    }
}
