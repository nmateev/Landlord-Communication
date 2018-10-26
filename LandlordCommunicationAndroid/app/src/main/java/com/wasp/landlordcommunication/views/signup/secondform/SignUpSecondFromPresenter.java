package com.wasp.landlordcommunication.views.signup.secondform;

import com.wasp.landlordcommunication.async.base.SchedulerProvider;
import com.wasp.landlordcommunication.models.User;
import com.wasp.landlordcommunication.services.base.UsersService;
import com.wasp.landlordcommunication.utils.Constants;

import javax.inject.Inject;

import io.reactivex.Observable;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.disposables.Disposable;

public class SignUpSecondFromPresenter implements SignUpSecondFromContracts.Presenter {

    private final UsersService mUsersService;
    private final SchedulerProvider mSchedulerProvider;
    private SignUpSecondFromContracts.View mView;
    private String mUserName;
    private String mUserPassword;

    @Inject
    public SignUpSecondFromPresenter(UsersService usersService, SchedulerProvider schedulerProvider) {
        mUsersService = usersService;
        mSchedulerProvider = schedulerProvider;
    }

    @Override
    public void subscribe(SignUpSecondFromContracts.View view) {
        mView = view;
    }

    @Override
    public void unsubscribe() {
        mView = null;
    }

    @Override
    public void setRegistrationInformation(String userName, String userPassword) {
        mUserName = userName;
        mUserPassword = userPassword;
    }

    @Override
    public void finishRegistration(String userTypeOption, String firstName, String lastName) {
        if (firstName.length() < Constants.MIN_NAME_LENGTH || lastName.length() < Constants.MIN_NAME_LENGTH) {
            mView.showMessage(Constants.NAME_FIELDS_ERROR_MESSAGE);
        } else {

            mView.showProgressBar();
            User userToCreate = new User(mUserName, mUserPassword, firstName, lastName, userTypeOption);
            Disposable observable = Observable
                    .create((ObservableOnSubscribe<User>) emitter -> {
                        User createdUser = mUsersService.createUser(userToCreate);
                        emitter.onNext(createdUser);
                        emitter.onComplete();
                    })
                    .subscribeOn(mSchedulerProvider.backgroundThread())
                    .observeOn(mSchedulerProvider.uiThread())
                    .doFinally(mView::hideProgressBar)
                    .subscribe(userResult -> mView.showHomeActivityWithUser(userResult),
                            error -> {
                                if (error instanceof NullPointerException) {
                                    mView.showMessage(Constants.UNSUCCESSFUL_REGISTRATION);
                                } else {
                                    mView.showError(error);
                                }
                            });
        }
    }
}
