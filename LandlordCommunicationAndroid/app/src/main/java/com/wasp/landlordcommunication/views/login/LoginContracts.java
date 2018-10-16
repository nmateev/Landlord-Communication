package com.wasp.landlordcommunication.views.login;

public interface LoginContracts {

    interface View {

        void setPresenter(Presenter presenter);

    }

    interface Presenter {

        void subscribe(View view);

        void unsubscribe();
    }
}
