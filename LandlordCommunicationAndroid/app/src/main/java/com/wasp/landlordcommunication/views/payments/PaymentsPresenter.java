package com.wasp.landlordcommunication.views.payments;

import com.wasp.landlordcommunication.async.base.SchedulerProvider;
import com.wasp.landlordcommunication.models.Payment;
import com.wasp.landlordcommunication.services.base.PaymentsService;
import com.wasp.landlordcommunication.utils.Constants;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.Observable;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.disposables.Disposable;

public class PaymentsPresenter implements PaymentsContracts.Presenter {

    private PaymentsContracts.View mView;
    private final PaymentsService mPaymentsService;
    private final SchedulerProvider mSchedulerProvider;

    @Inject
    PaymentsPresenter(PaymentsService paymentsService, SchedulerProvider schedulerProvider) {

        mPaymentsService = paymentsService;
        mSchedulerProvider = schedulerProvider;

    }
    @Override
    public void subscribe(PaymentsContracts.View view) {
        mView=view;
    }

    @Override
    public void unsubscribe() {
        mView=null;
    }

    @Override
    public void showPaymentsList() {
        mView.showProgressBarLoading();

        Disposable observable = Observable
                .create((ObservableOnSubscribe<List<Payment>>) emitter -> {
                    List<Payment> payments = mPaymentsService.getAllPayments();
                    emitter.onNext(payments);
                    emitter.onComplete();
                })
                .subscribeOn(mSchedulerProvider.backgroundThread())
                .observeOn(mSchedulerProvider.uiThread())
                .doFinally(mView::hideProgressBarLoading)
                .subscribe(allPayments -> presentPaymentsToView(allPayments, Constants.NO_PAYMENTS_AVAILABLE_MESSAGE), mView::showError);
    }

    @Override
    public void presentPaymentsToView(List<Payment> allPayments, String message) {
        if (allPayments.isEmpty()) {
            mView.showMessage(message);
        } else {
            mView.showAllPayments(allPayments);
        }
    }
}
