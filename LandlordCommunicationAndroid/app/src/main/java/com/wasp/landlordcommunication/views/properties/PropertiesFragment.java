package com.wasp.landlordcommunication.views.properties;


import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.github.florent37.materialtextfield.MaterialTextField;
import com.wasp.landlordcommunication.R;
import com.wasp.landlordcommunication.models.Property;
import com.wasp.landlordcommunication.utils.Constants;

import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.OnItemClick;


public class PropertiesFragment extends Fragment implements PropertiesContracts.View, PropertiesAdapter.OnPropertyItemClickListener {

    @BindView(R.id.tv_no_properties)
    TextView mNoPropertiesTextView;

    @BindView(R.id.prb_loading_view)
    ProgressBar mProgressBarView;

    @BindView(R.id.sp_search_options)
    Spinner mSearchOptionsSpinner;

    @BindView(R.id.met_search_properties)
    MaterialTextField mSearchBarEditText;

    @BindView(R.id.lv_properties_list_view)
    ListView mPropertiesListView;

    @BindView(R.id.rv_properties_recycler_view)
    RecyclerView mPropertiesRecyclerView;

    @Inject
    PropertiesArrayAdapter mPropertiesArrayAdapter;

    @Inject
    PropertiesAdapter mPropertiesAdapter;

    private PropertiesContracts.Navigator mNavigator;
    private PropertiesContracts.Presenter mPresenter;
    private LinearLayoutManager mPropertiesGridLayoutManager;
    private SharedPreferences mPreferences;


    @Inject
    public PropertiesFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_properties, container, false);
        ButterKnife.bind(this, view);

        mPropertiesListView.setAdapter(mPropertiesArrayAdapter);

        mPropertiesAdapter.setOnPropertyItemClickListener(this);
        mPropertiesRecyclerView.setAdapter(mPropertiesAdapter);
        mPropertiesGridLayoutManager = new LinearLayoutManager(getContext());
        mPropertiesRecyclerView.setLayoutManager(mPropertiesGridLayoutManager);
        mPreferences = PreferenceManager.getDefaultSharedPreferences(getContext());
       
        return view;
    }

    @Override
    public void onResume() {
        super.onResume();
        mPresenter.subscribe(this);
        String preference = mPreferences.getString(Constants.PREFERENCES_PROPERTY_LISTING_TYPE_KEY, Constants.EMPTY_STRING);
        mPresenter.loadUserProperties(preference);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        mPresenter.unsubscribe();
    }

    @Override
    public void setPresenter(PropertiesContracts.Presenter presenter) {
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
    public void showTextMessageOnScreen(String message) {
        mNoPropertiesTextView.setVisibility(View.VISIBLE);
        mNoPropertiesTextView.setText(message);
    }

    @Override
    public void showCompactPropertiesView(List<Property> propertiesResult) {
        mNoPropertiesTextView.setVisibility(View.GONE);
        mSearchBarEditText.setVisibility(View.VISIBLE);
        mSearchBarEditText.setHasFocus(true);
        mSearchOptionsSpinner.setVisibility(View.VISIBLE);
        mPropertiesListView.setVisibility(View.VISIBLE);

        mPropertiesArrayAdapter.clear();
        mPropertiesArrayAdapter.addAll(propertiesResult);
        mPropertiesArrayAdapter.notifyDataSetChanged();
    }

    @Override
    public void showDetailedPropertiesView(List<Property> propertiesResult) {
        mNoPropertiesTextView.setVisibility(View.GONE);
        mSearchBarEditText.setVisibility(View.VISIBLE);
        mSearchBarEditText.setHasFocus(true);
        mSearchOptionsSpinner.setVisibility(View.VISIBLE);
        mPropertiesRecyclerView.setVisibility(View.VISIBLE);

        mPropertiesAdapter.clear();
        mPropertiesAdapter.addAll(propertiesResult);
        mPropertiesAdapter.notifyDataSetChanged();
    }

    public void setNavigator(PropertiesContracts.Navigator navigator) {
        mNavigator = navigator;
    }

    @Override
    public void onClick(Property property) {
        mPresenter.propertyIsSelected(property);
    }

    @OnItemClick(R.id.lv_properties_list_view)
    public void onListViewClick(int position) {
        Property property = mPropertiesArrayAdapter.getItem(position);
        mPresenter.propertyIsSelected(property);
    }
}
