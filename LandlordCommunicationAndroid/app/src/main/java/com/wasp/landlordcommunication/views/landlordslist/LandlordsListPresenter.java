package com.wasp.landlordcommunication.views.landlordslist;

import com.wasp.landlordcommunication.async.base.SchedulerProvider;
import com.wasp.landlordcommunication.models.User;
import com.wasp.landlordcommunication.services.base.UsersService;
import com.wasp.landlordcommunication.utils.Constants;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.Observable;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.disposables.Disposable;

import static com.wasp.landlordcommunication.utils.Constants.LANDLORD;

public class LandlordsListPresenter implements LandlordsListContracts.Presenter {
    private final UsersService mUsersService;
    private final SchedulerProvider mSchedulerProvider;
    private LandlordsListContracts.View mView;
    private int mUserId;
    private String mUserType;

    @Inject
    public LandlordsListPresenter(UsersService usersService, SchedulerProvider schedulerProvider) {
        mUsersService = usersService;
        mSchedulerProvider = schedulerProvider;

    }

    @Override
    public void subscribe(LandlordsListContracts.View view) {
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
    public void loadAllLandlords() {
        mView.showProgressBar();
        Disposable observable = Observable
                .create((ObservableOnSubscribe<List<User>>) emitter -> {
                    List<User> landlordsList = mUsersService.getAllUsersByType(LANDLORD, mUserId);
                    emitter.onNext(landlordsList);
                    emitter.onComplete();
                })
                .subscribeOn(mSchedulerProvider.backgroundThread())
                .observeOn(mSchedulerProvider.uiThread())
                .doFinally(mView::hideProgressBar)
                .subscribe(this::presentLandlordsToView,
                        error -> mView.showError(error));
    }

    private void presentLandlordsToView(List<User> landlordsList) {
        if (landlordsList.isEmpty()) {
            mView.showNoLandlordsAvailableMessage(Constants.NO_LANDLORDS_AVAILABLE_MESSAGE);
        } else {
            mView.showLandlords(landlordsList);
        }
    }

    @Override
    public void landlordIsSelected(User user) {

        mView.showPropertiesForUser(user);

    }

    @Override
    public void filterLandlordsWithPattern(String pattern) {
        List<User> landlordsList = mUsersService.getFilteredUsersByName(pattern);
        presentFilteredLandlordsToView(landlordsList);
    }

    private void presentFilteredLandlordsToView(List<User> landlordsList) {
        if (landlordsList.isEmpty()) {
            mView.clearLandlordsList();
        } else {
            mView.showLandlords(landlordsList);
        }
    }
}
