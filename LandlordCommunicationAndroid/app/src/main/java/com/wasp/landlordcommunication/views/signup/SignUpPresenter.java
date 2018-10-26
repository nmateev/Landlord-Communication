package com.wasp.landlordcommunication.views.signup;

import com.wasp.landlordcommunication.async.base.SchedulerProvider;
import com.wasp.landlordcommunication.models.User;
import com.wasp.landlordcommunication.services.base.UsersService;
import com.wasp.landlordcommunication.utils.Constants;

import java.util.Objects;

import javax.inject.Inject;

import io.reactivex.Observable;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.disposables.Disposable;

public class SignUpPresenter implements SignUpContracts.Presenter {
    private final UsersService mUsersService;
    private final SchedulerProvider mSchedulerProvider;
    private SignUpContracts.View mView;
    private Boolean isUsernameAvailable = true;

    @Inject
    public SignUpPresenter(UsersService usersService, SchedulerProvider schedulerProvider) {
        mUsersService = usersService;
        mSchedulerProvider = schedulerProvider;
    }

    @Override
    public void subscribe(SignUpContracts.View view) {
        mView = view;
    }

    @Override
    public void unsubscribe() {
        mView = null;
    }

    @Override
    public void continueRegistration(String userName, String userPassword, String userPasswordRepeat, boolean isUserNameTaken) {

        boolean isInputCorrect = true;
        if (userName.length() < Constants.MIN_LENGTH_VALUE ||
                userPassword.length() < Constants.MIN_LENGTH_VALUE ||
                userPasswordRepeat.length() < Constants.MIN_LENGTH_VALUE) {
            isInputCorrect = false;
            mView.showMessage(Constants.LOGIN_INVALID_FIELDS_MESSAGE);
        }

        if (!userPassword.equals(userPasswordRepeat)) {
            isInputCorrect = false;
            mView.showMessage(Constants.PASSWORDS_MATCH_ERROR_MESSAGE);
        }

        if (isUserNameTaken) {
            isInputCorrect = false;
            mView.showMessage(Constants.USERNAME_ALREADY_TAKEN_MESSAGE);
        }
        if (isInputCorrect) {
            mView.continueToNextRegistrationForm(userName, userPassword);
        }

    }

    @Override
    public void checkIfUsernameIsAvailable(String userName) {
        Disposable observable = Observable
                .create((ObservableOnSubscribe<User>) emitter -> {
                    User user = mUsersService.getUserByUserName(userName);
                    emitter.onNext(user);
                    emitter.onComplete();
                })
                .subscribeOn(mSchedulerProvider.backgroundThread())
                .observeOn(mSchedulerProvider.uiThread())
                .doFinally(() -> mView.hideProgressBar())
                .subscribe(user -> mView.showUserNameIsTaken(),
                        error -> {
                            if (error instanceof NullPointerException) {
                                mView.hideUserNameIsTakenMessage();
                            } else {
                                mView.showError(error);
                            }
                        });
    }

}
