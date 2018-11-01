package com.wasp.landlordcommunication.views.properties;

import com.wasp.landlordcommunication.models.Property;

import java.util.List;

public interface PropertiesContracts {

    interface View {

        void setPresenter(Presenter presenter);

        void showProgressBar();

        void hideProgressBar();

        void showError(Throwable error);

        void showMessage(String message);

        void showTextMessageOnScreen(String message);

        void showCompactPropertiesView(List<Property> propertiesResult);

        void showDetailedPropertiesView(List<Property> propertiesResult);

        void showPropertyManagementOptions(int propertyId);
    }

    interface Presenter {

        void subscribe(View view);

        void unsubscribe();

        void setUserId(int userId);

        void setUserType(String userType);

        void loadUserProperties(String preference);

        void propertyIsSelected(Property selectedProperty);

        void filterPropertiesWithOption(String preference, String selectedOption);
    }

    interface Navigator {

        void navigateToPropertyManagementOptions(int propertyId);
    }
}
