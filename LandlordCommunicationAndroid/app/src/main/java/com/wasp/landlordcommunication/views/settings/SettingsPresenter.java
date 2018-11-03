package com.wasp.landlordcommunication.views.settings;

import com.wasp.landlordcommunication.utils.Constants;

import javax.inject.Inject;

import static com.wasp.landlordcommunication.utils.Constants.TENANT;

public class SettingsPresenter implements SettingsContracts.Presenter {


    private SettingsContracts.View mView;
    private String mUserType;

    @Inject
    public SettingsPresenter() {

    }

    @Override
    public void subscribe(SettingsContracts.View view) {
        mView = view;
    }

    @Override
    public void unsubscribe() {
        mView = null;
    }

    @Override
    public void setUserType(String userType) {
        mUserType = userType;
    }

    @Override
    public void propertiesLayoutPreferenceIsSelected(String selectedPropertiesLayoutPreference) {
        mView.savePropertiesLayoutPreference(selectedPropertiesLayoutPreference);

        mView.showMessage(new StringBuilder()
                .append(Constants.PREFERENCE_SAVED_MESSAGE)
                .append(selectedPropertiesLayoutPreference)
                .append(Constants.PREFERENCE_PROPERTIES_LAYOUT_VIEW_SELECTION_MESSAGE)
                .toString());
    }

    @Override
    public void loadPreferencesOptions(String selectedPropertiesLayoutOption, String[] layoutOptions) {
        String individualisation;
        if (mUserType.equals(TENANT)) {
            individualisation = Constants.PREFERENCE_PROPERTIES_LAYOUT_DESCRIPTION_FOR_TENANT;
        } else {
            individualisation = Constants.PREFERENCE_PROPERTIES_LAYOUT_DESCRIPTION_FOR_LANDLORD;
        }

        //if a preference is not selected already the index is 0 as first option
        int indexOfSelected = 0;

        //else we find the already selected option in order to set it in spinner as currently chosen
        for (int i = 0; i < layoutOptions.length; i++) {
            if (layoutOptions[i].equals(selectedPropertiesLayoutOption)) {
                indexOfSelected = i;
                break;
            }
        }
        mView.showPreferencesOptions(individualisation, indexOfSelected);
    }

}
