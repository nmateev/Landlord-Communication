package com.wasp.landlordcommunication.views.propertymanagement;

public interface PropertyManagementContracts {

    interface View {

        void setPresenter(Presenter presenter);

        void showProgressBar();

        void hideProgressBar();

        void showError(Throwable error);

        void showMessage(String message);

    }

    interface Presenter {

        void subscribe(View view);

        void unsubscribe();

        void setUserId(int userId);

        void setUserType(String userType);

        void setSelectedPropertyId(int propertyId);

        void loadPropertyManagementOptions();
    }

    interface Navigator {


    }
}
