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

import static com.wasp.landlordcommunication.utils.Constants.TENANT;

public class PaymentsPresenter implements PaymentsContracts.Presenter {

    private final PaymentsService mPaymentsService;
    private final SchedulerProvider mSchedulerProvider;
    private PaymentsContracts.View mView;
    private String mUserType;
    private int mUserId;

    @Inject
    public PaymentsPresenter(PaymentsService paymentsService, SchedulerProvider schedulerProvider) {
        mPaymentsService = paymentsService;
        mSchedulerProvider = schedulerProvider;
    }

    @Override
    public void subscribe(PaymentsContracts.View view) {
        mView = view;
    }

    @Override
    public void unsubscribe() {
        mView = null;
    }

    @Override
    public void showPaymentsList() {
        mView.showProgressBarLoading();


        Disposable observable = Observable
                .create((ObservableOnSubscribe<List<Payment>>) emitter -> {
                    List<Payment> payments = mPaymentsService.getAllPaymentsById(mUserType, mUserId);
                    emitter.onNext(payments);
                    emitter.onComplete();
                })
                .subscribeOn(mSchedulerProvider.backgroundThread())
                .observeOn(mSchedulerProvider.uiThread())
                .doFinally(mView::hideProgressBarLoading)
                .subscribe(this::presentPaymentsToView,
                        mView::showError);
    }

    @Override
    public void presentPaymentsToView(List<Payment> allPayments) {
        if (allPayments.isEmpty()) {
            mView.showNoPaymentsMessage(Constants.NO_PAYMENTS_AVAILABLE_MESSAGE);
        } else {
            mView.showAllPayments(allPayments);
        }
    }

    @Override
    public void setUserType(String userType) {
        mUserType = userType;
    }

    @Override
    public void setUserId(int userId) {
        mUserId = userId;
    }

    @Override
    public void showIndividualisedText() {
        String titleText;
        if (mUserType.equals(TENANT)) {
            titleText = Constants.PAYMENTS_SENT_TITLE;
        } else {
            titleText = Constants.PAYMENTS_RECEIVED_TITLE;
        }
        mView.setIndividualisedPaymentsTitleText(titleText);
    }
}
