package com.wasp.landlordcommunication.views.payments;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.wasp.landlordcommunication.R;
import com.wasp.landlordcommunication.models.Payment;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.TimeZone;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;

public class PaymentsAdapter extends RecyclerView.Adapter<PaymentsAdapter.PaymentViewHolder> {
    private static final String DATE_REPRESENTATION = "dd.MM.yyyy  HH:mm:ss";
    private List<Payment> mPayments;
    private SimpleDateFormat mDateFormatter;

    @Inject
    public PaymentsAdapter() {
        mPayments = new ArrayList<>();
        mDateFormatter = new SimpleDateFormat(DATE_REPRESENTATION, Locale.UK);
        mDateFormatter.setTimeZone(TimeZone.getTimeZone("UTC"));
    }

    @NonNull
    @Override
    public PaymentViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int viewType) {
        View view = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.payment_item_layout, viewGroup, false);

        int height = viewGroup.getMeasuredHeight() / 5;

        ViewGroup.LayoutParams layoutParams = view.getLayoutParams();
        layoutParams.height = height;
        view.setLayoutParams(layoutParams);
        view.setMinimumHeight(height);

        return new PaymentViewHolder(view);

    }

    @Override
    public void onBindViewHolder(@NonNull PaymentViewHolder paymentViewHolder, int position) {
        paymentViewHolder.bind(mPayments.get(position));
    }

    @Override
    public int getItemCount() {
        return mPayments.size();
    }

    public Payment getPaymentItem(int position) {
        return mPayments.get(position);
    }

    public void clear() {
        mPayments.clear();
    }

    public void addAll(List<Payment> payments) {
        mPayments.addAll(payments);
    }


    public class PaymentViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.tv_payment_for_property_address)
        TextView mPaymentForPropertyAddressTextView;

        @BindView(R.id.tv_payment_date_paid)
        TextView mPaymentDateTextView;

        @BindView(R.id.tv_payment_amount)
        TextView mPaymentAmountTextView;

        @BindView(R.id.tv_payment_card_number)
        TextView mCardNumberTextView;


        private PaymentViewHolder(View view) {
            super(view);

            ButterKnife.bind(this, view);
        }

        private void bind(Payment payment) {
            String paymentDateAndTime = mDateFormatter.format(payment.getDatePaid());

            mPaymentForPropertyAddressTextView.setText(payment.getProperty().getPropertyAddress());
            mPaymentDateTextView.setText(paymentDateAndTime);
            mPaymentAmountTextView.setText(String.valueOf(payment.getPaymentAmount()));
            mCardNumberTextView.setText(payment.getCardNumber());
        }

    }
}
