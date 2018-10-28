package com.wasp.landlordcommunication.views.payments;

import com.wasp.landlordcommunication.models.Payment;

import java.util.List;

public interface PaymentsContracts {

    interface View {
        void setPresenter(PaymentsContracts.Presenter presenter);

        void showAllPayments(List<Payment> allPayments);

        void showProgressBarLoading();

        void hideProgressBarLoading();

        void showError(Throwable error);

        void showMessage(String message);

        void showNoPaymentsMessage(String message);
    }

    interface Presenter {
        void subscribe(PaymentsContracts.View view);

        void unsubscribe();

        void showPaymentsList();

        void presentPaymentsToView(List<Payment> allPayments);

        void setUserType(String userType);

        void setUserId(int userId);
    }
}
