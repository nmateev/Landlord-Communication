package com.wasp.landlordcommunication.views.landlordslist;

import android.graphics.Bitmap;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.wasp.landlordcommunication.R;
import com.wasp.landlordcommunication.models.User;
import com.wasp.landlordcommunication.utils.base.ImageEncoder;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class UsersAdapter extends RecyclerView.Adapter<UsersAdapter.UserViewHolder> {
    private static final int HEIGHT_DIVIDER = 3;
    private final ImageEncoder mImageEncoder;
    private OnUserItemClickListener mOnUserItemClickListener;
    private List<User> mUsersList;


    @Inject
    public UsersAdapter(ImageEncoder imageEncoder) {
        mImageEncoder = imageEncoder;
        mUsersList = new ArrayList<>();

    }

    @NonNull
    @Override
    public UsersAdapter.UserViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int viewType) {
        View view = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.user_layout_item, viewGroup, false);

        int height = viewGroup.getMeasuredHeight() / HEIGHT_DIVIDER;

        ViewGroup.LayoutParams layoutParams = view.getLayoutParams();
        layoutParams.height = height;
        view.setLayoutParams(layoutParams);
        view.setMinimumHeight(height);

        return new UserViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull UsersAdapter.UserViewHolder userViewHolder, int position) {
        userViewHolder.setOnClickListener(mOnUserItemClickListener);
        userViewHolder.bind(mUsersList.get(position));
    }

    @Override
    public int getItemCount() {
        return mUsersList.size();
    }

    public User getUserItem(int position) {
        return mUsersList.get(position);
    }

    public void clear() {
        mUsersList.clear();
    }

    public void addAll(List<User> users) {
        mUsersList.addAll(users);
    }

    public void setOnUserItemClickListener(OnUserItemClickListener onUserItemClickListener) {
        this.mOnUserItemClickListener = onUserItemClickListener;
    }


    public class UserViewHolder extends RecyclerView.ViewHolder {


        @BindView(R.id.iv_user_picture)
        ImageView mUserImageView;

        @BindView(R.id.tv_first_name)
        TextView mFirstNameTextView;

        @BindView(R.id.tv_last_name)
        TextView mLastNameTextView;
        private OnUserItemClickListener mOnUserClickListener;
        private User mUser;


        private UserViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);
        }

        private void bind(User user) {
            if (Objects.equals(user.getUserPicture(), null)) {
                mUserImageView.setImageResource(R.drawable.defaultuserpicture);
            } else {
                Bitmap userImage = mImageEncoder.decodeStringToBitmap(user.getUserPicture());
                if (Objects.equals(userImage, null)) {
                    mUserImageView.setImageResource(R.drawable.defaultuserpicture);
                } else {
                    mUserImageView.setImageBitmap(userImage);
                }
            }
            mFirstNameTextView.setText(user.getFirstName());
            mLastNameTextView.setText(user.getLastName());
            mUser = user;
        }

        @OnClick
        public void onClick() {
            mOnUserClickListener.onClick(mUser);
        }

        private void setOnClickListener(OnUserItemClickListener onUserItemClickListener) {
            mOnUserClickListener = onUserItemClickListener;
        }

    }

    interface OnUserItemClickListener {
        void onClick(User user);
    }
}

