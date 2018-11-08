package com.wasp.landlordcommunication.views.chat;

import android.content.Context;
import android.graphics.Bitmap;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.wasp.landlordcommunication.R;
import com.wasp.landlordcommunication.models.ChatSession;
import com.wasp.landlordcommunication.utils.base.ImageEncoder;

import java.util.Objects;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import de.hdodenhof.circleimageview.CircleImageView;

public class ChatSessionsArrayAdapter extends ArrayAdapter<ChatSession> {

    @BindView(R.id.tv_name_of_contacted_person)
    TextView mNameContactedPersonTextView;

    @BindView(R.id.civ_profile_image)
    CircleImageView mProfilePictureCircleImageView;

    @Inject
    ImageEncoder mImageEncoder;

    private int mLoggedInUserId;

    @Inject
    public ChatSessionsArrayAdapter(@NonNull Context context) {
        super(context, -1);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        View view = convertView;
        if (Objects.equals(view, null)) {
            LayoutInflater inflater = LayoutInflater.from(this.getContext());
            view = inflater.inflate(R.layout.chat_session_item_layout, parent, false);
        }

        ButterKnife.bind(this, view);
        ChatSession chatSession = getItem(position);

        Bitmap userImage = null;
        String userName;

        if (Objects.requireNonNull(chatSession).getLandlordId() == mLoggedInUserId) {
            userName = chatSession.getTenant().getFirstName();

            if (!Objects.equals(chatSession.getTenant().getUserPicture(), null)) {
                userImage = mImageEncoder.decodeStringToBitmap(chatSession.getTenant().getUserPicture());
            }

        } else {
            userName = chatSession.getLandlord().getFirstName();

            if (!Objects.equals(chatSession.getLandlord().getUserPicture(), null)) {
                userImage = mImageEncoder.decodeStringToBitmap(chatSession.getLandlord().getUserPicture());
            }
        }


        if (!Objects.equals(userImage, null)) {
            mProfilePictureCircleImageView.setImageBitmap(userImage);
        } else {
            mProfilePictureCircleImageView.setImageResource(R.drawable.defaultuserpicture);
        }

        mNameContactedPersonTextView.setText(userName);


        return view;
    }

    //this serves as indication for which user is logged in in order to show the picture of the other user in the view
    public void setLoggedInUserId(int loggedInUserId) {
        mLoggedInUserId = loggedInUserId;
    }
}
