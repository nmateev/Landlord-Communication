package com.wasp.landlordcommunication.diconfig.viewsmodules;

import com.wasp.landlordcommunication.diconfig.ActivityScoped;
import com.wasp.landlordcommunication.diconfig.FragmentScoped;
import com.wasp.landlordcommunication.views.signup.SignUpContracts;
import com.wasp.landlordcommunication.views.signup.SignUpFragment;
import com.wasp.landlordcommunication.views.signup.secondform.SignUpSecondFormFragment;
import com.wasp.landlordcommunication.views.signup.SignUpPresenter;
import com.wasp.landlordcommunication.views.signup.secondform.SignUpSecondFromContracts;
import com.wasp.landlordcommunication.views.signup.secondform.SignUpSecondFromPresenter;

import dagger.Binds;
import dagger.Module;
import dagger.android.ContributesAndroidInjector;

@Module
public abstract class SignUpModule {

    @FragmentScoped
    @ContributesAndroidInjector
    abstract SignUpFragment signUpFragment();

    @FragmentScoped
    @ContributesAndroidInjector
    abstract SignUpSecondFormFragment signUpSecondFormFragment();

    @ActivityScoped
    @Binds
    abstract SignUpContracts.Presenter signUpPresenter(SignUpPresenter signUpPresenter);

    @ActivityScoped
    @Binds
    abstract SignUpSecondFromContracts.Presenter signUpSecondFormPresenter(SignUpSecondFromPresenter signUpSecondFromPresenter);

}
