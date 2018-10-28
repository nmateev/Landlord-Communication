package com.wasp.landlordcommunication.views.payments;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.wasp.landlordcommunication.R;
import com.wasp.landlordcommunication.models.Payment;
import com.wasp.landlordcommunication.utils.Constants;

import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;


public class PaymentsFragment extends Fragment implements PaymentsContracts.View {


    @BindView(R.id.prb_loading_view)
    ProgressBar mProgressBarView;

    @BindView(R.id.rv_payments_recycler_view)
    RecyclerView mPaymentsRecyclerView;

    @BindView(R.id.tv_no_payments_message)
    TextView mNoPaymentsAvailableTextView;

    @Inject
    PaymentsAdapter mPaymentsAdapter;

    private PaymentsContracts.Presenter mPresenter;
    private LinearLayoutManager mPaymentsViewLayoutManager;

    @Inject
    public PaymentsFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_payments, container, false);

        ButterKnife.bind(this, view);

        mPaymentsRecyclerView.setAdapter(mPaymentsAdapter);
        mPaymentsViewLayoutManager = new LinearLayoutManager(getContext());
        mPaymentsRecyclerView.setLayoutManager(mPaymentsViewLayoutManager);


        return view;
    }

    @Override
    public void onResume() {
        super.onResume();
        mPresenter.subscribe(this);
        mPresenter.showPaymentsList();
    }

    @Override
    public void onStop() {
        super.onStop();
        mPresenter.unsubscribe();
    }

    @Override
    public void setPresenter(PaymentsContracts.Presenter presenter) {
        this.mPresenter = presenter;
    }

    @Override
    public void showAllPayments(List<Payment> allPayments) {
        mNoPaymentsAvailableTextView.setVisibility(View.GONE);
        mPaymentsAdapter.clear();
        mPaymentsAdapter.addAll(allPayments);
        mPaymentsAdapter.notifyDataSetChanged();
    }

    @Override
    public void showProgressBarLoading() {
        mProgressBarView.setVisibility(View.VISIBLE);
        mPaymentsRecyclerView.setVisibility(View.GONE);
    }

    @Override
    public void hideProgressBarLoading() {
        mProgressBarView.setVisibility(View.GONE);
        mPaymentsRecyclerView.setVisibility(View.VISIBLE);
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

    @Override
    public void showNoPaymentsMessage(String message) {
        mNoPaymentsAvailableTextView.setVisibility(View.VISIBLE);
        mNoPaymentsAvailableTextView.setText(message);
    }
}
