package com.wasp.landlordcommunication.views.login;

import com.wasp.landlordcommunication.async.base.SchedulerProvider;
import com.wasp.landlordcommunication.models.User;
import com.wasp.landlordcommunication.services.base.UsersService;
import com.wasp.landlordcommunication.utils.Constants;

import javax.inject.Inject;

import io.reactivex.Observable;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.disposables.Disposable;

import static com.wasp.landlordcommunication.utils.Constants.EMPTY_STRING_LENGTH_VALUE;

public class LoginPresenter implements LoginContracts.Presenter {

    private final UsersService mUsersService;
    private final SchedulerProvider mSchedulerProvider;
    private LoginContracts.View mView;

    @Inject
    public LoginPresenter(UsersService usersService, SchedulerProvider schedulerProvider) {
        mUsersService = usersService;
        mSchedulerProvider = schedulerProvider;
    }

    @Override
    public void subscribe(LoginContracts.View view) {
        mView = view;
    }

    @Override
    public void unsubscribe() {
        mView = null;
    }

    @Override
    public void handleCustomLoginAttempt(String username, String password, int errorVisibilityCode) {
        if (username.length() == EMPTY_STRING_LENGTH_VALUE || password.length() == EMPTY_STRING_LENGTH_VALUE) {
            mView.showLoginCredentialsProblemMessage(Constants.LOGIN_EMPTY_FIELDS_MESSAGE);
        } else if (username.length() < Constants.MIN_LENGTH_VALUE || password.length() < Constants.MIN_LENGTH_VALUE) {
            mView.showLoginCredentialsProblemMessage(Constants.LOGIN_INVALID_FIELDS_MESSAGE);
        } else {
            if (errorVisibilityCode == Constants.VISIBLE_CODE_VALUE) {
                mView.hideLoginProblemMessage();
            }
            mView.showProgressBar();
            Disposable observable = Observable
                    .create((ObservableOnSubscribe<User>) emitter -> {
                        User user = mUsersService.loginUser(new User(username, password));
                        emitter.onNext(user);
                        emitter.onComplete();
                    })
                    .subscribeOn(mSchedulerProvider.backgroundThread())
                    .observeOn(mSchedulerProvider.uiThread())
                    .doFinally(mView::hideProgressBar)
                    .subscribe(user -> mView.showHomeActivityWithUser(user),
                            error -> {
                                if (error instanceof NullPointerException) {
                                    /*  if the user passes invalid login credentials the result is null and onNext() is called with
                                     * null which throws null pointer exception, then we know the credentials are not correct*/
                                    mView.showLoginCredentialsProblemMessage(Constants.LOGIN_INVALID_USERNAME_OR_PASSWORD_MESSAGE);
                                } else {
                                    mView.showError(error);
                                }
                            });
        }
    }

    @Override
    public void handleLoginFieldFocusChange(int errorVisibilityCode) {
        if (errorVisibilityCode == Constants.VISIBLE_CODE_VALUE) {
            mView.hideLoginProblemMessage();
        }
    }

    @Override
    public void handleSignUpButtonClick() {
        mView.showSignUpActivity();
    }
}
