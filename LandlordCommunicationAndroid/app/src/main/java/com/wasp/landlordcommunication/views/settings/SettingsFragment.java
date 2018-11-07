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
    TextView mPropertiesLayoutTitleTextView;

    @BindView(R.id.tv_preferences_layout_description)
    TextView mIndividualisedDescriptionTextView;

    @BindView(R.id.sp_properties_layout_options)
    NiceSpinner mPropertiesLayoutOptionsSpinner;

    @BindView(R.id.tv_preferences_template_formality_title)
    TextView mTemplateFormalityTitleTextView;

    @BindView(R.id.tv_preferences_template_formality_description)
    TextView mTemplateFormalityDescriptionTextView;

    @BindView(R.id.sp_template_formality_options)
    NiceSpinner mTemplateFormalityOptionsSpinner;

    private String[] mLayoutOptions;
    private String[] mTemplateFormalityOptions;

    private String mSelectedPropertiesLayoutOption;
    private String mSelectedTemplateFormalityOption;

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

        mTemplateFormalityOptions = getResources().getStringArray(R.array.template_messages_formality_options);
        mTemplateFormalityOptionsSpinner.attachDataSource(Arrays.asList(mTemplateFormalityOptions));

        mSelectedPropertiesLayoutOption = mSharedPreferences.getString(Constants.PREFERENCES_PROPERTY_LISTING_TYPE_KEY, Constants.EMPTY_STRING);
        mSelectedTemplateFormalityOption = mSharedPreferences.getString(Constants.PREFERENCES_TEMPLATE_MESSAGES_FORMALITY_KEY, Constants.EMPTY_STRING);
        mPropertiesLayoutOptionsSpinner.setOnItemSelectedListener(this);
        mTemplateFormalityOptionsSpinner.setOnItemSelectedListener(this);


        return view;
    }

    @Override
    public void onResume() {
        super.onResume();
        mPresenter.subscribe(this);
        mPresenter.loadPreferencesOptions(mSelectedPropertiesLayoutOption, mLayoutOptions, mSelectedTemplateFormalityOption, mTemplateFormalityOptions);
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
    public void showPreferencesOptions(String individualisationForProperties, int indexOfAlreadySelectedLayoutOption, int indexOfAlreadySelectedFormalityOption) {
        mPropertiesLayoutTitleTextView.setVisibility(View.VISIBLE);
        mIndividualisedDescriptionTextView.setVisibility(View.VISIBLE);
        mIndividualisedDescriptionTextView.setText(individualisationForProperties);

        mPropertiesLayoutOptionsSpinner.setSelectedIndex(indexOfAlreadySelectedLayoutOption);
        mPropertiesLayoutOptionsSpinner.setVisibility(View.VISIBLE);


        mTemplateFormalityTitleTextView.setVisibility(View.VISIBLE);
        mTemplateFormalityDescriptionTextView.setVisibility(View.VISIBLE);

        mTemplateFormalityOptionsSpinner.setSelectedIndex(indexOfAlreadySelectedFormalityOption);
        mTemplateFormalityOptionsSpinner.setVisibility(View.VISIBLE);


    }

    @Override
    public void saveTemplateFormalityPreference(String selectedTemplateFormalityOption) {
        mEditor = mSharedPreferences.edit();
        mEditor.putString(Constants.PREFERENCES_TEMPLATE_MESSAGES_FORMALITY_KEY, selectedTemplateFormalityOption);
        mEditor.commit();
    }

    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int position, long id) {

        switch (adapterView.getId()) {
            case R.id.sp_properties_layout_options:
                mSelectedPropertiesLayoutOption = mLayoutOptions[position];
                mPresenter.propertiesLayoutPreferenceIsSelected(mSelectedPropertiesLayoutOption);

                break;
            case R.id.sp_template_formality_options:
                mSelectedTemplateFormalityOption = mTemplateFormalityOptions[position];
                mPresenter.templateMessagesFormalityIsSelected(mSelectedTemplateFormalityOption);

                break;
        }
    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {
        mSelectedPropertiesLayoutOption = mLayoutOptions[0];
        mSelectedTemplateFormalityOption = mTemplateFormalityOptions[0];
    }
}
