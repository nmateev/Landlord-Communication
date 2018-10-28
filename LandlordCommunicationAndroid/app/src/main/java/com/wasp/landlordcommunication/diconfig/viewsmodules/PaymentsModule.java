package com.wasp.landlordcommunication.diconfig.viewsmodules;

import com.wasp.landlordcommunication.diconfig.ActivityScoped;
import com.wasp.landlordcommunication.diconfig.FragmentScoped;
import com.wasp.landlordcommunication.views.payments.PaymentsContracts;
import com.wasp.landlordcommunication.views.payments.PaymentsFragment;
import com.wasp.landlordcommunication.views.payments.PaymentsPresenter;

import dagger.Binds;
import dagger.Module;
import dagger.android.ContributesAndroidInjector;

@Module
public abstract class PaymentsModule {

    @FragmentScoped
    @ContributesAndroidInjector
    abstract PaymentsFragment paymentsFragment();

    @ActivityScoped
    @Binds
    abstract PaymentsContracts.Presenter paymentsPresenter(PaymentsPresenter paymentsPresenter);
}
