package com.wasp.landlordcommunication.diconfig.viewsmodules;


import android.support.v7.widget.RecyclerView;

import com.wasp.landlordcommunication.views.payments.PaymentsAdapter;

import dagger.Module;
import dagger.Provides;

@Module
public class ViewsModule {

    @Provides
    public RecyclerView.Adapter<PaymentsAdapter.PaymentViewHolder> paymentsAdapter() {
        return new PaymentsAdapter();
    }

}
