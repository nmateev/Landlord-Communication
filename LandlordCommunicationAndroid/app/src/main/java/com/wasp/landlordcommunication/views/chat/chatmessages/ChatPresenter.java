package com.wasp.landlordcommunication.views.chat.chatmessages;

import javax.inject.Inject;

public class ChatPresenter implements ChatContracts.Presenter {


    private ChatContracts.View mView;
    private int mUserId;
    private String mUserType;

    @Inject
    public ChatPresenter() {

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

    }

    @Override
    public void setFirstChatMember(int firstChatMemberId) {

    }

    @Override
    public void setSecondChatMember(int secondChatMemberId) {

    }
}
