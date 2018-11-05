package com.wasp.landlordcommunication.views.chat.chatmessages;

import com.wasp.landlordcommunication.utils.Constants;

import javax.inject.Inject;

public class ChatPresenter implements ChatContracts.Presenter {


    private ChatContracts.View mView;
    private int mUserId;
    private String mUserType;
    private int mChatSessionId;
    private int mFirstChatMemberId;
    private int mSecondChatMemberId;

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
    public void loadChatSessionMessages() {

       /* If the chat session id is different from 0 the user navigated from his chat lists activity and there is no need to
       check if the chat session between the users exists and we load the messages from the session directly */
        if (mChatSessionId != 0) {

        } else if ((mFirstChatMemberId != 0) && (mSecondChatMemberId != 0)) {
           /* If the first chat member id and the second chat member id are different from zero then the user selected the
            option to navigate to chat through property management view.In this case we have to check if the chat sessions
            between the users already exists and if not, first create it and then show chat view*/

        } else {
            mView.showMessage(Constants.UNEXPECTED_ERROR);
        }

    }
}

