package com.wasp.landlordcommunication.diconfig.viewsmodules;


import android.support.v7.widget.RecyclerView;

import com.wasp.landlordcommunication.utils.base.DateFormatter;
import com.wasp.landlordcommunication.utils.base.ImageEncoder;
import com.wasp.landlordcommunication.views.landlordslist.UsersAdapter;
import com.wasp.landlordcommunication.views.landlordspropertieslist.LandlordsPropertiesAdapter;
import com.wasp.landlordcommunication.views.payments.PaymentsAdapter;

import dagger.Module;
import dagger.Provides;

@Module
public class ViewsModule {

    @Provides
    public RecyclerView.Adapter<PaymentsAdapter.PaymentViewHolder> paymentsAdapter(DateFormatter dateFormatter) {
        return new PaymentsAdapter(dateFormatter);
    }

    @Provides
    public RecyclerView.Adapter<UsersAdapter.UserViewHolder> usersAdapter(ImageEncoder imageEncoder) {
        return new UsersAdapter(imageEncoder);
    }

    @Provides
    public RecyclerView.Adapter<LandlordsPropertiesAdapter.PropertyViewHolder> propertiesAdapter(ImageEncoder imageEncoder) {
        return new LandlordsPropertiesAdapter(imageEncoder);
    }
}
