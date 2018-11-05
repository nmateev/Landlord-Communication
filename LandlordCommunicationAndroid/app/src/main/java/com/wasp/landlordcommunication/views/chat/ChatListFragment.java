package com.wasp.landlordcommunication.views.chat;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.wasp.landlordcommunication.R;
import com.wasp.landlordcommunication.models.ChatSession;
import com.wasp.landlordcommunication.utils.Constants;

import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnItemClick;


public class ChatListFragment extends Fragment implements ChatListContracts.View {

    @BindView(R.id.prb_loading_view)
    ProgressBar mProgressBarView;

    @BindView(R.id.tv_no_chat_sessions_message)
    TextView mNoChatsMessageTextView;

    @BindView(R.id.lv_chat_sessions_list)
    ListView mChatSessionsListView;

    @Inject
    ChatSessionsArrayAdapter mChatSessionsArrayAdapter;

    private ChatListContracts.Presenter mPresenter;
    private ChatListContracts.Navigator mNavigator;


    @Inject
    public ChatListFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_chat_list, container, false);

        ButterKnife.bind(this, view);

        mChatSessionsListView.setAdapter(mChatSessionsArrayAdapter);

        return view;
    }

    @Override
    public void onResume() {
        super.onResume();
        mPresenter.subscribe(this);
        mPresenter.loadChatSessions();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        mPresenter.unsubscribe();
    }

    @Override
    public void setPresenter(ChatListContracts.Presenter presenter) {
        mPresenter = presenter;
    }

    @Override
    public void showProgressBar() {
        mProgressBarView.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideProgressBar() {
        mProgressBarView.setVisibility(View.GONE);
    }

    @Override
    public void showError(Throwable error) {
        String errorMessage = Constants.ERROR_MESSAGE + error.getMessage();
        Toast.makeText(getContext(), errorMessage, Toast.LENGTH_LONG).show();
    }

    @Override
    public void showMessage(String message) {

        Toast.makeText(getContext(), message, Toast.LENGTH_SHORT)
                .show();
    }

    @Override
    public void showEmptyChatSessionsMessage(String message) {
        mChatSessionsListView.setVisibility(View.GONE);
        mNoChatsMessageTextView.setVisibility(View.VISIBLE);
        mNoChatsMessageTextView.setText(message);
    }

    @Override
    public void showChatSessionsList(List<ChatSession> chatSessions) {
        mNoChatsMessageTextView.setVisibility(View.GONE);
        mChatSessionsListView.setVisibility(View.VISIBLE);

        mChatSessionsArrayAdapter.clear();
        mChatSessionsArrayAdapter.addAll(chatSessions);
        mChatSessionsArrayAdapter.notifyDataSetChanged();
    }

    @OnItemClick(R.id.lv_chat_sessions_list)
    public void onListViewClick(int position) {
        ChatSession chatSession = mChatSessionsArrayAdapter.getItem(position);
        mPresenter.chatSessionIsSelected(chatSession);
    }

    @Override
    public void setLoggedInUserIdToAdapter(int userId) {
        mChatSessionsArrayAdapter.setLoggedInUserId(userId);
    }

    @Override
    public void showChatMessages(int chatSessionId, int tenantId, int landlordId) {
        mNavigator.navigateToChat(chatSessionId, tenantId, landlordId);
    }

    public void setNavigator(ChatListContracts.Navigator navigator) {
        mNavigator = navigator;
    }
}
