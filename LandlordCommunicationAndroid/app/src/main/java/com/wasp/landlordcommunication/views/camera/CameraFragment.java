package com.wasp.landlordcommunication.views.camera;


import android.app.Fragment;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.hardware.Camera;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.Toast;

import com.wasp.landlordcommunication.R;

import javax.inject.Inject;

import butterknife.ButterKnife;

/**
 * A simple {@link Fragment} subclass.
 */
public class CameraFragment extends Fragment implements CameraContracts.View{
    private CameraContracts.Presenter mPresenter;


    @Inject
    public CameraFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view=inflater.inflate(R.layout.fragment_camera, container, false);
        ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void captureImage(Camera deviceCamera) {
        deviceCamera.takePicture(
                null,
                null,
                (data, camera) -> {
                    Bitmap bitmap = BitmapFactory.decodeByteArray(data, 0, data.length);
                    if (bitmap == null) {
                        Toast.makeText(getActivity(), "Captured image is empty", Toast.LENGTH_LONG).show();
                        return;
                    }
                    ImageView capturedImageHolder = getView().findViewById(R.id.captured_image);
                    capturedImageHolder.setImageBitmap(Bitmap.createScaledBitmap(bitmap, 500, 300, true));
                });
    }

    @Override
    public void sendImage(ImageView image) {

    }


    @Override
    public void setPresenter(CameraContracts.Presenter presenter) {
        mPresenter=presenter;
    }
}
