package com.wasp.landlordcommunication.views.camera;


import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;

import com.wasp.landlordcommunication.R;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;


public class CameraFragment extends Fragment implements CameraContracts.View{


    private static final int CAMERA_REQUEST = 111;
    private CameraContracts.Presenter mPresenter;

    @BindView(R.id.captured_image)
    ImageView mPhoto;
    @BindView(R.id.btn_send)
    Button buttonSend;

    @Inject
    public CameraFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_camera, container, false);
        ButterKnife.bind(this, view);

        return view;
    }


    @OnClick(R.id.btn_open_camera)
    public void onCameraButtonClick(View view) {
        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        startActivityForResult(intent, CAMERA_REQUEST);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode,resultCode,data);
        if (data!=null) {
            if (data.getExtras() != null) {
                Bitmap photo = (Bitmap) data.getExtras().get("data");
                mPhoto.setImageBitmap(photo);
                buttonSend.setVisibility(View.VISIBLE);
            }
        }
    }

    @OnClick(R.id.btn_send)
    public void onSendButtonClick(View view) {

    }
    @Override
    public void sendImage(ImageView image) {

    }


    @Override
    public void setPresenter(CameraContracts.Presenter presenter) {
        mPresenter = presenter;
    }

}
