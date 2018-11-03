package com.wasp.landlordcommunication.diconfig.viewsmodules;


import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.widget.ArrayAdapter;

import com.wasp.landlordcommunication.models.Property;
import com.wasp.landlordcommunication.utils.base.DateFormatter;
import com.wasp.landlordcommunication.utils.base.ImageEncoder;
import com.wasp.landlordcommunication.views.landlordslist.UsersAdapter;
import com.wasp.landlordcommunication.views.landlordspropertieslist.LandlordsPropertiesAdapter;
import com.wasp.landlordcommunication.views.payments.PaymentsAdapter;
import com.wasp.landlordcommunication.views.properties.PropertiesAdapter;
import com.wasp.landlordcommunication.views.properties.PropertiesArrayAdapter;

import javax.inject.Named;

import dagger.Module;
import dagger.Provides;

@Module
public class ViewsModule {

    @Provides
    public RecyclerView.Adapter<PaymentsAdapter.PaymentViewHolder> paymentsAdapter() {
        return new PaymentsAdapter();
    }

    @Provides
    public RecyclerView.Adapter<UsersAdapter.UserViewHolder> usersAdapter(ImageEncoder imageEncoder) {
        return new UsersAdapter(imageEncoder);
    }

    @Provides
    public RecyclerView.Adapter<LandlordsPropertiesAdapter.PropertyViewHolder> landlordsPropertiesAdapter(ImageEncoder imageEncoder) {
        return new LandlordsPropertiesAdapter(imageEncoder);
    }

    @Provides
    public ArrayAdapter<Property> propertiesArrayAdapter(Context context) {
        return new PropertiesArrayAdapter(context);
    }

    @Provides
    public RecyclerView.Adapter<PropertiesAdapter.PropertyViewHolder> propertiesAdapter(ImageEncoder imageEncoder) {
        return new PropertiesAdapter(imageEncoder);
    }
}
