package com.wasp.landlordcommunication.views.settings;


import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.TextView;
import android.widget.Toast;

import com.wasp.landlordcommunication.R;
import com.wasp.landlordcommunication.utils.Constants;

import org.angmarch.views.NiceSpinner;

import java.util.Arrays;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;


public class SettingsFragment extends Fragment implements SettingsContracts.View, AdapterView.OnItemSelectedListener {


    @BindView(R.id.tv_preferences_layout_title)
    TextView mProeprtiesLayoutTitleTextView;

    @BindView(R.id.tv_preferences_layout_description)
    TextView mIndividualisedDescriptionTextView;

    @BindView(R.id.sp_properties_layout_options)
    NiceSpinner mPropertiesLayoutOptionsSpinner;

    private String[] mLayoutOptions;
    private String mSelectedPropertiesLayoutOption;
    private SettingsContracts.Presenter mPresenter;
    private SharedPreferences mSharedPreferences;
    private SharedPreferences.Editor mEditor;

    @Inject
    public SettingsFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_settings, container, false);
        ButterKnife.bind(this, view);
        mSharedPreferences = PreferenceManager.getDefaultSharedPreferences(getContext());

        mLayoutOptions = getResources().getStringArray(R.array.properties_layout_options);
        mPropertiesLayoutOptionsSpinner.attachDataSource(Arrays.asList(mLayoutOptions));
        mSelectedPropertiesLayoutOption = mSharedPreferences.getString(Constants.PREFERENCES_PROPERTY_LISTING_TYPE_KEY, Constants.EMPTY_STRING);
        mPropertiesLayoutOptionsSpinner.setOnItemSelectedListener(this);


        return view;
    }

    @Override
    public void onResume() {
        super.onResume();
        mPresenter.subscribe(this);
        mPresenter.loadPreferencesOptions(mSelectedPropertiesLayoutOption, mLayoutOptions);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        mPresenter = null;
    }

    @Override
    public void setPresenter(SettingsContracts.Presenter presenter) {
        mPresenter = presenter;
    }


    @Override
    public void showMessage(String message) {
        Toast
                .makeText(getContext(), message, Toast.LENGTH_LONG)
                .show();
    }

    @Override
    public void savePropertiesLayoutPreference(String selectedPropertiesLayoutPreference) {
        mEditor = mSharedPreferences.edit();
        mEditor.putString(Constants.PREFERENCES_PROPERTY_LISTING_TYPE_KEY, selectedPropertiesLayoutPreference);
        mEditor.commit();
    }

    @Override
    public void showPreferencesOptions(String individualisation, int positionOfAlreadySelected) {
        mProeprtiesLayoutTitleTextView.setVisibility(View.VISIBLE);
        mIndividualisedDescriptionTextView.setVisibility(View.VISIBLE);
        mIndividualisedDescriptionTextView.setText(individualisation);

        mPropertiesLayoutOptionsSpinner.setSelectedIndex(positionOfAlreadySelected);
        mPropertiesLayoutOptionsSpinner.setVisibility(View.VISIBLE);
    }

    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int position, long id) {
        mSelectedPropertiesLayoutOption = mLayoutOptions[position];
        mPresenter.propertiesLayoutPreferenceIsSelected(mSelectedPropertiesLayoutOption);
    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {
        mSelectedPropertiesLayoutOption = mLayoutOptions[0];
    }
}
