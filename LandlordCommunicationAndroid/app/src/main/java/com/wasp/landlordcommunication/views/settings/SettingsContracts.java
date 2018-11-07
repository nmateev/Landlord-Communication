package com.wasp.landlordcommunication.views.settings;

public interface SettingsContracts {

    interface View {

        void setPresenter(Presenter presenter);

        void showMessage(String message);

        void savePropertiesLayoutPreference(String selectedPropertiesLayoutPreference);

        void showPreferencesOptions(String individualisationForProperties, int indexOfAlreadySelectedLayoutOption, int indexOfAlreadySelectedFormalityOption);

        void saveTemplateFormalityPreference(String selectedTemplateFormalityOption);
    }

    interface Presenter {

        void subscribe(View view);

        void unsubscribe();

        void setUserType(String userType);

        void propertiesLayoutPreferenceIsSelected(String selectedPropertiesLayoutPreference);

        void loadPreferencesOptions(String selectedPropertiesLayoutOption, String[] layoutOptions, String selectedTemplateFormalityOption, String[] templateFormalityOptions);

        void templateMessagesFormalityIsSelected(String selectedTemplateFormalityOption);
    }
}
