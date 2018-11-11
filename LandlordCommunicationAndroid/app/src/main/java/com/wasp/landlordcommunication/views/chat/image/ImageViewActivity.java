package com.wasp.landlordcommunication.views.chat.image;

import android.content.Intent;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;

import com.wasp.landlordcommunication.R;
import com.wasp.landlordcommunication.utils.Constants;

import javax.inject.Inject;

import butterknife.ButterKnife;
import dagger.android.support.DaggerAppCompatActivity;

public class ImageViewActivity extends DaggerAppCompatActivity {

    @Inject
    ImageViewContracts.Presenter mImageViewPresenter;

    @Inject
    ImageViewFragment mImageViewFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_image_view);
        ButterKnife.bind(this);


        Intent intent = getIntent();
        String imageString = intent.getStringExtra(Constants.IMAGE_MESSAGE_EXTRA);

        mImageViewPresenter.setImage(imageString);

        mImageViewFragment.setPresenter(mImageViewPresenter);

        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.fr_image_view, mImageViewFragment)
                .commit();
    }
}
