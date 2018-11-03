package com.wasp.landlordcommunication.views.settings;

public interface SettingsContracts {

    interface View {

        void setPresenter(Presenter presenter);

        void showMessage(String message);

        void savePropertiesLayoutPreference(String selectedPropertiesLayoutPreference);

        void showPreferencesOptions(String individualisation, int indexOfAlreadySelectedOption);
    }

    interface Presenter {

        void subscribe(View view);

        void unsubscribe();

        void setUserType(String userType);

        void propertiesLayoutPreferenceIsSelected(String selectedPropertiesLayoutPreference);

        void loadPreferencesOptions(String selectedPropertiesLayoutOption, String[] layoutOptions);
    }
}
