package com.wasp.landlordcommunication.views.propertymanagement;

import android.graphics.Bitmap;

import com.wasp.landlordcommunication.async.base.SchedulerProvider;
import com.wasp.landlordcommunication.models.CardTransaction;
import com.wasp.landlordcommunication.models.Payment;
import com.wasp.landlordcommunication.models.Property;
import com.wasp.landlordcommunication.models.Rating;
import com.wasp.landlordcommunication.services.base.PaymentsService;
import com.wasp.landlordcommunication.services.base.PropertiesService;
import com.wasp.landlordcommunication.services.base.RatingsService;
import com.wasp.landlordcommunication.utils.Constants;
import com.wasp.landlordcommunication.utils.ValidationException;
import com.wasp.landlordcommunication.utils.base.DateFormatter;
import com.wasp.landlordcommunication.utils.base.ImageEncoder;
import com.wasp.landlordcommunication.utils.base.Validator;

import java.util.Date;
import java.util.Locale;
import java.util.Objects;

import javax.inject.Inject;

import io.reactivex.Observable;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.disposables.Disposable;

import static com.wasp.landlordcommunication.utils.Constants.LANDLORD;
import static com.wasp.landlordcommunication.utils.Constants.TENANT;

public class PropertyManagementPresenter implements PropertyManagementContracts.Presenter {
    private final PropertiesService mPropertiesService;
    private final RatingsService mRatingsService;
    private final PaymentsService mPaymentsService;
    private final SchedulerProvider mSchedulerProvider;
    private final ImageEncoder mImageEncoder;
    private final Validator<CardTransaction> mCardTransactionValidator;
    private final DateFormatter mDateFormatter;
    private PropertyManagementContracts.View mView;
    private int mUserId;
    private String mUserType;
    int mOtherUserId;
    private int mSelectedPropertyId;
    private double mSelectedPropertyRentPrice;
    private boolean mIsRentPaidForCurrentMonth;
    private String mSelectedProeprtyAddress;

    @Inject
    public PropertyManagementPresenter(PropertiesService propertiesService, RatingsService ratingsService, PaymentsService paymentsService, SchedulerProvider schedulerProvider,
                                       ImageEncoder imageEncoder, Validator<CardTransaction> cardTransactionValidator, DateFormatter dateFormatter) {
        mPropertiesService = propertiesService;
        mRatingsService = ratingsService;
        mPaymentsService = paymentsService;
        mSchedulerProvider = schedulerProvider;
        mImageEncoder = imageEncoder;
        mCardTransactionValidator = cardTransactionValidator;
        mDateFormatter = dateFormatter;
    }

    @Override
    public void subscribe(PropertyManagementContracts.View view) {
        mView = view;
    }

    @Override
    public void unsubscribe() {
        mView = null;
    }

    @Override
    public void setUserId(int userId) {
        mUserId = userId;
    }

    @Override
    public void setUserType(String userType) {
        mUserType = userType;
    }

    @Override
    public void setSelectedPropertyId(int propertyId) {
        mSelectedPropertyId = propertyId;
    }

    @Override
    public void loadPropertyManagementOptions() {
        mView.showProgressBar();
        Disposable observable = Observable
                .create((ObservableOnSubscribe<Property>) emitter -> {
                    Property property = mPropertiesService.getPropertyById(mSelectedPropertyId);
                    emitter.onNext(property);
                    emitter.onComplete();
                })
                .subscribeOn(mSchedulerProvider.backgroundThread())
                .observeOn(mSchedulerProvider.uiThread())
                .doFinally(() -> mView.hideProgressBar())
                .subscribe(property -> {
                            preparePropertyDetails(property);
                            loadOtherUsersRating();
                        },
                        error -> mView.showError(error));


    }

    private void loadOtherUsersRating() {
        mView.showProgressBar();
        Disposable observable = Observable
                .create((ObservableOnSubscribe<Double>) emitter -> {
                    double userRating = mRatingsService.getUserRatingById(mOtherUserId);
                    emitter.onNext(userRating);
                    emitter.onComplete();
                })
                .subscribeOn(mSchedulerProvider.backgroundThread())
                .observeOn(mSchedulerProvider.uiThread())
                .doFinally(mView::hideProgressBar)
                .subscribe(userRatingResult -> mView.showUserRating(userRatingResult),
                        error -> mView.showError(error));
    }

    @Override
    public void rateButtonIsClicked() {
        mView.showRatingDialog();
    }

    @Override
    public void ratingWasCancelled() {
        mView.showMessage(Constants.RATING_CANCELLED_MESSAGE);
    }

    @Override
    public void userIsRated(int ratingValue) {

        Rating ratingConnectionToCheck = new Rating(mUserId, mOtherUserId);
        mView.showProgressBar();
        Disposable observable = Observable
                .create((ObservableOnSubscribe<Rating>) emitter -> {
                    Rating ratingCheck = mRatingsService.checkIfUserAlreadyRatedByVoter(ratingConnectionToCheck);
                    emitter.onNext(ratingCheck);
                    emitter.onComplete();
                })
                .subscribeOn(mSchedulerProvider.backgroundThread())
                .observeOn(mSchedulerProvider.uiThread())
                .doFinally(mView::hideProgressBar)
                .subscribe(userRatingResult -> mView.showMessage(Constants.ALREADY_RATED_MESSAGE),
                        error -> {
                            if (error instanceof NullPointerException) {
                                submitUserRating(ratingValue, mUserId, mOtherUserId);
                            } else {
                                mView.showError(error);
                            }
                        });


    }

    @Override
    public void messageButtonIsClicked() {
        mView.showChatWithUsers(mUserId, mOtherUserId);
    }

    @Override
    public void payRentButtonIsClicked() {
        if (mIsRentPaidForCurrentMonth) {
            mView.showMessage(Constants.ALREADY_PAID_RENT_MESSAGE);
        } else {
            mView.showPaymentInputOption();
        }
    }

    @Override
    public void finishPaymentButtonIsClicked(String firstName, String lastName, String validThruMonth, String validThruYear, String cardNumber, String cardCvvNumber) {

        CardTransaction cardTransaction;
        try {
            cardTransaction = new CardTransaction(firstName, lastName, Integer.parseInt(validThruMonth), Integer.parseInt(validThruYear), cardNumber, Integer.parseInt(cardCvvNumber));
            mCardTransactionValidator.validateEntity(cardTransaction);
            makePaymentTransaction(cardTransaction);
        } catch (NumberFormatException nfe) {
            mView.showMessage(Constants.ALL_FIELDS_MUST_BE_FILLED_IN);
        } catch (ValidationException validationException) {
            mView.showMessage(validationException.getMessage());
        }

    }

    @Override
    public void rentChangeButtonIsClicked(String newRentAmountInString) {
        double newRentAmount;
        try {
            newRentAmount = Double.parseDouble(newRentAmountInString);
            if (newRentAmount < Constants.RENT_AMOUNT_MIN_BOUND || newRentAmount > Constants.RENT_AMOUNT_MAX_BOUND) {
                mView.showMessage(Constants.RENT_AMOUNT_BOUNDS_NOT_MET_MESSAGE);
            } else {
                loadPropertyAndUpdateRentPrice(newRentAmount, mSelectedPropertyId);

            }
        } catch (NumberFormatException nfe) {
            mView.showMessage(Constants.INPUT_NOT_CORRECT_MESSAGE);
        }
    }

    private void loadPropertyAndUpdateRentPrice(double newRentAmount, int selectedPropertyId) {
        mView.showProgressBar();
        Disposable observable = Observable
                .create((ObservableOnSubscribe<Property>) emitter -> {
                    Property property = mPropertiesService.getPropertyById(selectedPropertyId);
                    emitter.onNext(property);
                    emitter.onComplete();
                })
                .subscribeOn(mSchedulerProvider.backgroundThread())
                .observeOn(mSchedulerProvider.uiThread())
                .doFinally(mView::hideProgressBar)
                .subscribe(property -> updatePropertyRentPrice(newRentAmount, property),
                        error -> mView.showError(error));


    }

    private void updatePropertyRentPrice(double newRentAmount, Property propertyToUpdate) {
        propertyToUpdate.setRentPrice(newRentAmount);

        propertyToUpdate.setPropertyPicture(null);
        mView.showProgressBar();
        Disposable observable = Observable
                .create((ObservableOnSubscribe<Property>) emitter -> {
                    Property property = mPropertiesService.updateProperty(propertyToUpdate, propertyToUpdate.getPropertyId());
                    emitter.onNext(property);
                    emitter.onComplete();
                })
                .subscribeOn(mSchedulerProvider.backgroundThread())
                .observeOn(mSchedulerProvider.uiThread())
                .doFinally(mView::hideProgressBar)
                .subscribe(property -> mView.showMessage(Constants.SUCCESSFUL_CHANGE_OF_RENT_MESSAGE), error -> mView.showError(error));

    }

    private void makePaymentTransaction(CardTransaction cardTransaction) {
        int tenantId;
        int landlordId;
        //if payment is presented as option the mUserId must be the tenantId but we check it again
        if (mUserType.equals(TENANT)) {
            tenantId = mUserId;
            landlordId = mOtherUserId;
        } else {
            tenantId = mOtherUserId;
            landlordId = mUserId;
        }
        Date date = new Date();
        String formattedCurrentDate = mDateFormatter.formatDateToString(date);

        StringBuilder cardNumberFormatter = new StringBuilder(cardTransaction.getCardNumber());
        //formats card number and puts slash(-)after every four digits for readability
        for (int i = 4; i < 19; i += 5) {
            cardNumberFormatter.insert(i, "-");
        }


        Payment payment = new Payment(tenantId, landlordId,mSelectedProeprtyAddress, mSelectedPropertyId, mSelectedPropertyRentPrice, formattedCurrentDate, cardNumberFormatter.toString());

        mView.showProgressBar();
        Disposable observable = Observable
                .create((ObservableOnSubscribe<Payment>) emitter -> {
                    Payment paymentResult = mPaymentsService.makePayment(payment);
                    emitter.onNext(paymentResult);
                    emitter.onComplete();
                })
                .subscribeOn(mSchedulerProvider.backgroundThread())
                .observeOn(mSchedulerProvider.uiThread())
                .doFinally(mView::hideProgressBar)
                .subscribe(paymentResult -> loadPropertyAndUpdateRentPaidStatus(mSelectedPropertyId),
                        error -> mView.showError(error));


    }

    private void loadPropertyAndUpdateRentPaidStatus(int selectedPropertyId) {
        mView.showProgressBar();
        Disposable observable = Observable
                .create((ObservableOnSubscribe<Property>) emitter -> {
                    Property property = mPropertiesService.getPropertyById(selectedPropertyId);
                    emitter.onNext(property);
                    emitter.onComplete();
                })
                .subscribeOn(mSchedulerProvider.backgroundThread())
                .observeOn(mSchedulerProvider.uiThread())
                .doFinally(mView::hideProgressBar)
                .subscribe(this::updateStatusAsPaidForProperty,
                        error -> mView.showError(error));

    }

    private void updateStatusAsPaidForProperty(Property propertyToUpdate) {
        propertyToUpdate.setRentPaid(true);
        //setting property picture to null in order to transfer little data to speed up process, picture is not deleted in the database
        propertyToUpdate.setPropertyPicture(null);
        mView.showProgressBar();
        Disposable observable = Observable
                .create((ObservableOnSubscribe<Property>) emitter -> {
                    Property property = mPropertiesService.updateProperty(propertyToUpdate, propertyToUpdate.getPropertyId());
                    emitter.onNext(property);
                    emitter.onComplete();
                })
                .subscribeOn(mSchedulerProvider.backgroundThread())
                .observeOn(mSchedulerProvider.uiThread())
                .doFinally(mView::hideProgressBar)
                .subscribe(property -> mView.showMessage(Constants.SUCCESSFUL_PAYMENT_MESSAGE), error -> mView.showError(error));


    }

    private void submitUserRating(int ratingValue, int voterId, int votedForId) {
        Rating newRating = new Rating(voterId, votedForId, ratingValue);

        mView.showProgressBar();
        Disposable observable = Observable
                .create((ObservableOnSubscribe<Rating>) emitter -> {
                    Rating rating = mRatingsService.submitRating(newRating);
                    emitter.onNext(rating);
                    emitter.onComplete();
                })
                .subscribeOn(mSchedulerProvider.backgroundThread())
                .observeOn(mSchedulerProvider.uiThread())
                .doFinally(mView::hideProgressBar)
                .subscribe(rating -> {
                            mView.showMessage(Constants.SUCCESSFUL_RATING);
                            loadOtherUsersRating();
                        },
                        error -> {
                            if (error instanceof NullPointerException) {
                                mView.showMessage(Constants.UNEXPECTED_ERROR);
                            } else {
                                mView.showError(error);
                            }
                        });

    }

    private void preparePropertyDetails(Property property) {
        if (Objects.equals(property.getPropertyPicture(), null)) {
            mView.showDefaultPropertyPicture();
        } else {
            Bitmap propertyImage = mImageEncoder.decodeStringToBitmap(property.getPropertyPicture());
            if (Objects.equals(propertyImage, null)) {
                mView.showDefaultPropertyPicture();
            } else {
                mView.showPropertyPicture(propertyImage);
            }
        }
        String individualisation;
        if (mUserType.equals(TENANT)) {
            individualisation = LANDLORD;
        } else {
            individualisation = TENANT;
        }

        if (mUserId == property.getTenantId()) {
            mOtherUserId = property.getLandlordId();
        } else {
            mOtherUserId = property.getTenantId();
        }
        mSelectedPropertyRentPrice = property.getRentPrice();
        mSelectedProeprtyAddress = property.getPropertyAddress();
        mIsRentPaidForCurrentMonth = property.getRentPaid();


        mView.showPropertyDetails(property, individualisation);

        if (mUserType.equals(TENANT)) {
            mView.showPayButtonOption();
        }
    }
}
