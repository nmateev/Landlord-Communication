package com.wasp.landlordcommunication.views.payments;

import android.content.Context;
import android.support.annotation.NonNull;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.wasp.landlordcommunication.R;
import com.wasp.landlordcommunication.models.Payment;

import javax.inject.Inject;

import butterknife.BindView;

public class PaymentsArrayAdapter extends ArrayAdapter<Payment> {

    @BindView(R.id.tv_property_address_payment)
    TextView mPropertyAddressTextView;
    @BindView(R.id.tv_amount_payment)
    TextView mPaymentAmountTextView;
    @BindView(R.id.tv_date_payment)
    TextView mPaymentDateTextView;
    @BindView(R.id.tv_card_number_payment)
    TextView mCardNumberTextView;


    @Inject
    public PaymentsArrayAdapter(@NonNull Context context) {
        super(context, -1);
    }

}
