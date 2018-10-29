package com.wasp.landlordcommunication.views.landlordspropertieslist;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

import com.wasp.landlordcommunication.R;
import com.wasp.landlordcommunication.models.Property;
import com.wasp.landlordcommunication.utils.Constants;

import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;


public class LandlordsPropertiesListFragment extends Fragment implements LandlordsPropertiesListContracts.View, LandlordsPropertiesAdapter.OnPropertyItemClickListener {
    private static final int SPAN_COUNT_ITEMS = 2;

    @BindView(R.id.prb_loading_view)
    ProgressBar mProgressBarView;

    @BindView(R.id.tv_no_properties_available_message)
    TextView mNoPropertiesAvailableTextView;

    @BindView(R.id.tv_landlord_full_name)
    TextView mLandlordFullNameTextView;

    @BindView(R.id.rb_landlords_rating)
    RatingBar mLandlordsRatingRatingBar;

    @BindView(R.id.rv_landlords_properties_recycler_view)
    RecyclerView mLandlordsPropertiesRecyclerView;

    @Inject
    LandlordsPropertiesAdapter mLandlordsPropertiesAdapter;

    private GridLayoutManager mPropertiesGridLayoutManager;
    private LandlordsPropertiesListContracts.Navigator mNavigator;
    private LandlordsPropertiesListContracts.Presenter mPresenter;


    @Inject
    public LandlordsPropertiesListFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_landlords_properties_list, container, false);
        ButterKnife.bind(this, view);

        mLandlordsPropertiesAdapter.setOnPropertyItemClickListener(this);

        mLandlordsPropertiesRecyclerView.setAdapter(mLandlordsPropertiesAdapter);
        mPropertiesGridLayoutManager = new GridLayoutManager(getContext(), SPAN_COUNT_ITEMS);
        mLandlordsPropertiesRecyclerView.setLayoutManager(mPropertiesGridLayoutManager);


        return view;
    }


    @Override
    public void onResume() {
        super.onResume();
        mPresenter.subscribe(this);
        mPresenter.loadLandlordsData();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        mPresenter.unsubscribe();
    }

    public void setNavigator(LandlordsPropertiesListContracts.Navigator navigator) {
        mNavigator = navigator;
    }

    @Override
    public void setPresenter(LandlordsPropertiesListContracts.Presenter presenter) {
        mPresenter = presenter;
    }

    @Override
    public void showProgressBar() {
        mProgressBarView.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideProgressBar() {
        mProgressBarView.setVisibility(View.GONE);
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
    public void showLandlordsName(String name) {
        mLandlordFullNameTextView.setText(name);
    }

    @Override
    public void showPropertyDetails(int propertyId) {
        mLandlordsPropertiesRecyclerView.setVisibility(View.GONE);
        mNavigator.navigateToPropertyDetails(propertyId);
    }

    @Override
    public void showLandlordsRating(double rating) {
        mLandlordsRatingRatingBar.setRating((float) rating);
    }

    @Override
    public void showNoPropertiesText(String message) {
        mNoPropertiesAvailableTextView.setVisibility(View.VISIBLE);
        mNoPropertiesAvailableTextView.setText(message);
    }

    @Override
    public void showLandlordsProperties(List<Property> landlordsPropertiesList) {
        mNoPropertiesAvailableTextView.setVisibility(View.GONE);
        mLandlordsPropertiesRecyclerView.setVisibility(View.VISIBLE);

        mLandlordsPropertiesAdapter.clear();
        mLandlordsPropertiesAdapter.addAll(landlordsPropertiesList);
        mLandlordsPropertiesAdapter.notifyDataSetChanged();
    }

    @Override
    public void onClick(Property property) {
        mPresenter.propertyIsSelected(property);
    }
}
