package com.wasp.landlordcommunication.views.chat.chatmessages;


import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.daimajia.androidanimations.library.Techniques;
import com.daimajia.androidanimations.library.YoYo;
import com.orhanobut.dialogplus.DialogPlus;
import com.wasp.landlordcommunication.R;
import com.wasp.landlordcommunication.models.ChatMessage;
import com.wasp.landlordcommunication.utils.Constants;

import java.util.List;
import java.util.Objects;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

import static com.daimajia.androidanimations.library.YoYo.with;


public class ChatFragment extends Fragment implements ChatContracts.View {

    @BindView(R.id.prb_loading_view)
    ProgressBar mProgressBarView;

    @BindView(R.id.et_message_input)
    EditText mInputMessageEditText;

    @BindView(R.id.rl_user_options)
    RelativeLayout mUserOptionsRelativeLayout;

    @BindView(R.id.rv_chat_messages)
    RecyclerView mChatMessagesRecyclerView;

    @Inject
    ChatAdapter mChatMessagesAdapter;

    private SharedPreferences mPreferences;
    private ArrayAdapter<String> mTemplateMessagesAdapter;
    private DialogPlus mTemplatePickerDialog;

    private LinearLayoutManager mChatMessagesViewLayoutManager;
    private ChatContracts.Presenter mPresenter;

    @Inject
    public ChatFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_chat, container, false);
        ButterKnife.bind(this, view);

        mChatMessagesRecyclerView.setAdapter(mChatMessagesAdapter);
        mChatMessagesViewLayoutManager = new LinearLayoutManager(getContext());
        mChatMessagesRecyclerView.setLayoutManager(mChatMessagesViewLayoutManager);

        mPreferences = PreferenceManager.getDefaultSharedPreferences(getContext());
        mTemplateMessagesAdapter = new ArrayAdapter<>(Objects.requireNonNull(getContext()), android.R.layout.simple_list_item_1);
        return view;
    }

    @Override
    public void onResume() {
        super.onResume();
        mPresenter.subscribe(this);
        mPresenter.loadChatSessionMessages();
    }

    @Override
    public void onStop() {
        super.onStop();
        mPresenter.stopChatLooping();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        mPresenter.unsubscribe();
    }

    @Override
    public void setPresenter(ChatContracts.Presenter presenter) {
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
    public void setLoggedInUserToAdapter(int userId) {
        mChatMessagesAdapter.setLoggedInUserId(userId);
    }

    @Override
    public void setUserImageToShowInAdapter(Bitmap userImage) {
        mChatMessagesAdapter.setReceiverImage(userImage);
    }

    @Override
    public void showChatMessages(List<ChatMessage> chatMessages) {
        mChatMessagesAdapter.addAllMessages(chatMessages);
        mChatMessagesAdapter.notifyDataSetChanged();
    }

    @Override
    public void showNewMessage(ChatMessage newMessage) {
        mChatMessagesAdapter.addSingleMessage(newMessage);
        mChatMessagesAdapter.notifyDataSetChanged();
    }

    @Override
    public void scrollChatToBottom() {
        mChatMessagesRecyclerView
                .smoothScrollToPosition(
                        Objects.requireNonNull(mChatMessagesRecyclerView
                                .getAdapter())
                                .getItemCount() - 1);
    }

    @Override
    public void clearMessageTextInput() {
        mInputMessageEditText
                .getText()
                .clear();
    }

    @Override
    public void showTemplateMessages(List<String> templateMessages) {
        mTemplateMessagesAdapter.clear();
        mTemplateMessagesAdapter.addAll(templateMessages);


        mTemplatePickerDialog = DialogPlus.newDialog(Objects.requireNonNull(getContext()))
                .setAdapter(mTemplateMessagesAdapter)
                .setOnItemClickListener((dialog, item, view, position) -> {
                    String pickedTemplate = mTemplateMessagesAdapter.getItem(position);
                    mPresenter.templateMessageIsSelected(pickedTemplate);
                })
                .setContentBackgroundResource(R.color.colorWindow)
                .setExpanded(true)
                .setGravity(Gravity.TOP)
                .setPadding(10, 50, 10, 10)
                .setCancelable(true)
                .setInAnimation(R.anim.fade_in_animation)
                .setOutAnimation(R.anim.fade_out_animation)
                .create();

        mTemplatePickerDialog.show();
    }

    @Override
    public void setTextToMessageInput(String pickedTemplate) {
        mInputMessageEditText.setText(pickedTemplate);
    }

    @Override
    public void dismissTemplatePicker() {
        mTemplatePickerDialog.dismiss();
    }

    @Override
    public void shakeUserOptionsLayout() {
        YoYo
                .with(Techniques.Shake)
                .duration(Constants.SHAKE_ANIMATION_DURATION_VALUE)
                .playOn(mUserOptionsRelativeLayout);
    }

    @OnClick(R.id.ib_template_picker)
    public void onTemplatePickerButtonClick() {

        String preference = mPreferences
                .getString(Constants.PREFERENCES_TEMPLATE_MESSAGES_FORMALITY_KEY, Constants.EMPTY_STRING);

        mPresenter.templatePickerIsClickedWithPreference(preference);
    }

    @OnClick(R.id.ib_send_message)
    public void onSendMessageButtonClick() {
        String message = mInputMessageEditText.getText().toString();
        mPresenter.sendButtonIsClicked(message);
    }
}
