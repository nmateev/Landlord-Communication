package com.wasp.landlordcommunication.views.home;

import com.wasp.landlordcommunication.async.base.SchedulerProvider;
import com.wasp.landlordcommunication.models.Rating;
import com.wasp.landlordcommunication.models.User;
import com.wasp.landlordcommunication.services.base.RatingsService;
import com.wasp.landlordcommunication.services.base.UsersService;

import java.util.List;

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


    private void loadUserPictureAndName() {
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

    private void loadUserRating() {
        mView.showProgressBar();
        Disposable observable = Observable
                .create((ObservableOnSubscribe<List<Rating>>) emitter -> {
                    List<Rating> userRatings = mRatingsService.getUserRatingById(mUserId);
                    emitter.onNext(userRatings);
                    emitter.onComplete();
                })
                .subscribeOn(mSchedulerProvider.backgroundThread())
                .observeOn(mSchedulerProvider.uiThread())
                .doFinally(mView::hideProgressBar)
                .subscribe(userRatingsResult -> {

                            double rating = calculateRating(userRatingsResult);
                            mView.showUserRating(rating);

                        },
                        error -> mView.showError(error));
    }


    private double calculateRating(List<Rating> userRatings) {

        //TODO Replace with streams when sdk is set to 24
        double rating = 0;
        int size = userRatings.size();
        if (size == 0) {
            return rating;
        } else {
            for (int i = 0; i < userRatings.size(); i++) {
                rating += userRatings.get(i).getRating();
            }
        }
        return rating / size;
    }
}
