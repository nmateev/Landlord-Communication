package com.wasp.landlordcommunication.views.chat.chatmessages;

import android.graphics.Bitmap;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.wasp.landlordcommunication.R;
import com.wasp.landlordcommunication.models.ChatMessage;
import com.wasp.landlordcommunication.utils.base.DateFormatter;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Objects;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import de.hdodenhof.circleimageview.CircleImageView;

public class ChatAdapter extends RecyclerView.Adapter {

    private static final int VIEW_TYPE_MESSAGE_SENT = 0;
    private static final int VIEW_TYPE_MESSAGE_RECEIVED = 1;

    private final DateFormatter mDateFormatter;
    private int mLoggedInUserId;
    private Bitmap mReceiverImage;
    private List<ChatMessage> mChatMessages;

    @Inject
    public ChatAdapter(DateFormatter dateFormatter) {
        mDateFormatter = dateFormatter;
        mChatMessages = new ArrayList<>();

    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int viewType) {
        View view;

        if (viewType == VIEW_TYPE_MESSAGE_SENT) {
            view = LayoutInflater.from(viewGroup.getContext())
                    .inflate(R.layout.chat_message_sent_item_layout, viewGroup, false);
            return new SentMessageHolder(view);
        } else {
            //  viewType == VIEW_TYPE_MESSAGE_RECEIVED;
            view = LayoutInflater.from(viewGroup.getContext())
                    .inflate(R.layout.chat_message_received_item_layout, viewGroup, false);
            return new ReceivedMessageHolder(view);
        }

    }

    @Override
    public int getItemViewType(int position) {
        ChatMessage message = mChatMessages.get(position);

        if (mLoggedInUserId == message.getSenderId()) {
            return VIEW_TYPE_MESSAGE_SENT;
        } else {
            return VIEW_TYPE_MESSAGE_RECEIVED;
        }
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder viewHolder, int position) {
        ChatMessage message = mChatMessages.get(position);

        switch (viewHolder.getItemViewType()) {
            case VIEW_TYPE_MESSAGE_SENT:
                ((SentMessageHolder) viewHolder).bind(message);
                break;
            case VIEW_TYPE_MESSAGE_RECEIVED:
                ((ReceivedMessageHolder) viewHolder).bind(message);
        }
    }


    @Override
    public int getItemCount() {
        return mChatMessages.size();
    }

    public ChatMessage getChatMessageItem(int position) {
        return mChatMessages.get(position);
    }

    public void clear() {
        mChatMessages.clear();
    }

    public void addAll(List<ChatMessage> chatMessages) {
        mChatMessages.addAll(chatMessages);
    }

    public void addSingleMessage(ChatMessage newMessage) {
        mChatMessages.add(newMessage);
    }

    public void setReceiverImage(Bitmap userImage) {
        mReceiverImage = userImage;
    }

    public void setLoggedInUserId(int userId) {
        mLoggedInUserId = userId;
    }


    class SentMessageHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.tv_message)
        TextView mSenderMessageTextView;

        @BindView(R.id.text_message_time_sent)
        TextView mSentTimeTextView;


        SentMessageHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);
        }

        void bind(ChatMessage message) {

            mSenderMessageTextView.setText(message.getMessageText());
            String timeStamp = mDateFormatter.formatDateToString(message.getDateSent());
            mSentTimeTextView.setText(timeStamp);

        }
    }

    class ReceivedMessageHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.tv_message)
        TextView mReceiverMessageTextView;

        @BindView(R.id.text_message_time_sent)
        TextView mSentTimeTextView;

        @BindView(R.id.civ_receiver_image)
        CircleImageView mReceiverCircleImageView;

        ReceivedMessageHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);

        }

        void bind(ChatMessage message) {

            mReceiverMessageTextView.setText(message.getMessageText());
            String timeStamp = mDateFormatter.formatDateToString(message.getDateSent());
            mSentTimeTextView.setText(timeStamp);

            if (Objects.equals(mReceiverImage, null)) {
                mReceiverCircleImageView.setImageResource(R.drawable.defaultuserpicture);
            } else {
                mReceiverCircleImageView.setImageBitmap(mReceiverImage);
            }
        }
    }
}


