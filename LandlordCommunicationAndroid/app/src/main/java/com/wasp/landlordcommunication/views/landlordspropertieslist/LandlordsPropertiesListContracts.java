package com.wasp.landlordcommunication.views.landlordspropertieslist;

import com.wasp.landlordcommunication.models.Property;
import com.wasp.landlordcommunication.models.User;

import java.util.List;

public interface LandlordsPropertiesListContracts {

    interface View {

        void setPresenter(Presenter presenter);

        void showProgressBar();

        void hideProgressBar();

        void showError(Throwable error);

        void showMessage(String message);

        void showLandlordsRating(double rating);

        void showLandlordsProperties(List<Property> landlordsPropertiesList);

        void showNoPropertiesText(String message);

        void showLandlordsName(String name);
    }

    interface Presenter {

        void subscribe(View view);

        void unsubscribe();

        void setUserId(int userId);

        void setUserType(String userType);

        void setSelectedLandlord(User selectedLandlord);

        void loadLandlordsData();

        void loadLandlordsRating(int userId);

        void loadLandlordsProperties(int userId, String userType);
    }

    interface Navigator {

    }
}
