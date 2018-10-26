package com.wasp.landlordcommunication.views.home;

import com.wasp.landlordcommunication.async.base.SchedulerProvider;
import com.wasp.landlordcommunication.models.User;
import com.wasp.landlordcommunication.services.base.RatingsService;
import com.wasp.landlordcommunication.services.base.UsersService;

import javax.inject.Inject;

import io.reactivex.Observable;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.disposables.Disposable;

public class HomeActivityPresenter implements HomeActivityContracts.Presenter {

    private final UsersService mUsersService;
    private final RatingsService mRatingsService;
    private final SchedulerProvider mSchedulerProvider;
    private HomeActivityContracts.View mView;
    private String mUserName;
    private int mUserId;

    @Inject
    public HomeActivityPresenter(UsersService usersService, RatingsService ratingsService, SchedulerProvider schedulerProvider) {
        mUsersService = usersService;
        mRatingsService = ratingsService;
        mSchedulerProvider = schedulerProvider;
    }

    @Override
    public void subscribe(HomeActivityContracts.View view) {
        mView = view;
    }

    @Override
    public void unsubscribe() {
        mView = null;
    }

    @Override
    public void loadUserInformation() {
        loadUserPictureAndName();
        loadUserRating();
    }

    @Override
    public void setUserName(String userName) {
        mUserName = userName;
    }

    @Override
    public void setUserId(int userId) {
        mUserId = userId;
    }

    @Override
    public void loadUserPictureAndName() {
        mView.showProgressBar();
        Disposable observable = Observable
                .create((ObservableOnSubscribe<User>) emitter -> {
                    User user = mUsersService.getUserByUserName(mUserName);
                    emitter.onNext(user);
                    emitter.onComplete();
                })
                .subscribeOn(mSchedulerProvider.backgroundThread())
                .observeOn(mSchedulerProvider.uiThread())
                .doFinally(mView::hideProgressBar)
                .subscribe(userResult -> {

                            //TODO SHOW PICTURE
                            String fullName = userResult.getFirstName() + " " + userResult.getLastName();
                            mView.showUserName(fullName);

                        },
                        error -> mView.showError(error));
    }

    @Override
    public void loadUserRating() {
        mView.showProgressBar();
        Disposable observable = Observable
                .create((ObservableOnSubscribe<Double>) emitter -> {
                    double userRating = mRatingsService.getUserRatingById(mUserId);
                    emitter.onNext(userRating);
                    emitter.onComplete();
                })
                .subscribeOn(mSchedulerProvider.backgroundThread())
                .observeOn(mSchedulerProvider.uiThread())
                .doFinally(mView::hideProgressBar)
                .subscribe(userRatingResult -> mView.showUserRating(userRatingResult),
                        error -> mView.showError(error));
    }
}
