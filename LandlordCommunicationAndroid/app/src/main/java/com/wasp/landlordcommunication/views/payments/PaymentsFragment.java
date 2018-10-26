package com.wasp.landlordcommunication.views.payments;


import android.os.Bundle;
import android.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.wasp.landlordcommunication.R;
import com.wasp.landlordcommunication.models.Payment;
import com.wasp.landlordcommunication.utils.Constants;

import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;


public class PaymentsFragment extends Fragment implements PaymentsContracts.View {

    @BindView(R.id.lv_payments_list_view)
    ListView mPaymentsListView;

    @BindView(R.id.prb_loading_view)
    ProgressBar mProgressBarView;

    @Inject
    PaymentsArrayAdapter mPaymentsArrayAdapter;

    private PaymentsContracts.Presenter mPaymentsPresenter;

    public PaymentsFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
         View view=inflater.inflate(R.layout.fragment_payments, container, false);

        ButterKnife.bind(this,view);
        mPaymentsListView.setAdapter(mPaymentsArrayAdapter);

         return view;
    }

    @Override
    public void setPresenter(PaymentsContracts.Presenter presenter) {
        mPaymentsPresenter=presenter;
    }

    @Override
    public void showAllPayments(List<Payment> allPayments) {
        mPaymentsArrayAdapter.clear();
        mPaymentsArrayAdapter.addAll(allPayments);
        mPaymentsArrayAdapter.notifyDataSetChanged();
    }

    @Override
    public void showProgressBarLoading() {
        mProgressBarView.setVisibility(View.VISIBLE);
        mPaymentsListView.setVisibility(View.GONE);
    }

    @Override
    public void hideProgressBarLoading() {

        mProgressBarView.setVisibility(View.GONE);
        mPaymentsListView.setVisibility(View.VISIBLE);
    }

    @Override
    public void showError(Throwable error) {
        String errorMessage = Constants.ERROR_MESSAGE + error.getMessage();
        Toast
                .makeText(getContext(), errorMessage, Toast.LENGTH_LONG)
                .show();
    }

    @Override
    public void showMessage(String message) {
        Toast
                .makeText(getContext(), message, Toast.LENGTH_LONG)
                .show();
    }
}
