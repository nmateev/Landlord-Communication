package com.wasp.landlordcommunication.views.properties;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.wasp.landlordcommunication.R;
import com.wasp.landlordcommunication.models.Property;
import com.wasp.landlordcommunication.utils.Constants;

import java.util.Objects;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;

public class PropertiesArrayAdapter extends ArrayAdapter<Property> {

    @BindView(R.id.tv_property_address)
    TextView mPropertyAddressTextView;

    @BindView(R.id.tv_property_rent_due_date)
    TextView mRentDueDateTextView;


    @Inject
    public PropertiesArrayAdapter(@NonNull Context context) {
        super(context, -1);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        View view = convertView;
        if (Objects.equals(view, null)) {
            LayoutInflater inflater = LayoutInflater.from(this.getContext());
            view = inflater.inflate(R.layout.property_item_array_adapter_layout, parent, false);
        }

        ButterKnife.bind(this, view);
        Property property = getItem(position);

        mPropertyAddressTextView
                .setText(property.getPropertyAddress());
        mRentDueDateTextView
                .setText(new StringBuilder()
                        .append(Constants.RENT_IS_DUE_AT)
                        .append(property.getDueDate())
                        .toString());

        return view;
    }
}
