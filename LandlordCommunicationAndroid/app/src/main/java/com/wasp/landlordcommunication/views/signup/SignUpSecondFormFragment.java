package com.wasp.landlordcommunication.views.signup;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.wasp.landlordcommunication.R;

import javax.inject.Inject;


public class SignUpSecondFormFragment extends Fragment {

    @Inject
    public SignUpSecondFormFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_sign_up_fragment_continued_registration, container, false);
    }

}
