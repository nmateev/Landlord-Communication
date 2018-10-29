package com.wasp.landlordcommunication.views.landlordslist;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.github.florent37.materialtextfield.MaterialTextField;
import com.wasp.landlordcommunication.R;
import com.wasp.landlordcommunication.models.User;
import com.wasp.landlordcommunication.utils.Constants;

import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnTextChanged;

public class LandlordsListFragment extends Fragment implements LandlordsListContracts.View, UsersAdapter.OnUserItemClickListener {
    private static final int SPAN_COUNT_ITEMS = 2;
    @BindView(R.id.prb_loading_view)
    ProgressBar mProgressBarView;

    @BindView(R.id.tv_no_landlords_available_message)
    TextView mNoLandlordsTextView;

    @BindView(R.id.rv_users_recycler_view)
    RecyclerView mUsersRecyclerView;

    @BindView(R.id.met_search_landlords)
    MaterialTextField mSearchBarEditText;

    @BindView(R.id.et_search_landlords_inner)
    EditText mSearchBarInnerEditText;

    @Inject
    UsersAdapter mUsersAdapter;

    private LandlordsListContracts.Presenter mPresenter;
    private LandlordsListContracts.Navigator mNavigator;
    private GridLayoutManager mUsersGridLayoutManager;

    @Inject
    public LandlordsListFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_landlords_list, container, false);
        ButterKnife.bind(this, view);

        mUsersAdapter.setOnUserItemClickListener(this);

        mUsersRecyclerView.setAdapter(mUsersAdapter);
        mUsersGridLayoutManager = new GridLayoutManager(getContext(), SPAN_COUNT_ITEMS);
        mUsersRecyclerView.setLayoutManager(mUsersGridLayoutManager);

        return view;
    }


    @Override
    public void onResume() {
        super.onResume();
        mPresenter.subscribe(this);
        mPresenter.loadAllLandlords();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        mPresenter.unsubscribe();
    }

    @Override
    public void setPresenter(LandlordsListContracts.Presenter presenter) {
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
    public void showNoLandlordsAvailableMessage(String message) {
        mSearchBarEditText.setVisibility(View.GONE);
        mSearchBarEditText.setHasFocus(false);
        mUsersRecyclerView.setVisibility(View.GONE);
        mNoLandlordsTextView.setVisibility(View.VISIBLE);
        mNoLandlordsTextView.setText(message);
    }

    @Override
    public void showLandlords(List<User> landlordsList) {
        mSearchBarEditText.setVisibility(View.VISIBLE);
        mSearchBarEditText.setHasFocus(true);
        mNoLandlordsTextView.setVisibility(View.GONE);
        mUsersRecyclerView.setVisibility(View.VISIBLE);
        mUsersAdapter.clear();
        mUsersAdapter.addAll(landlordsList);
        mUsersAdapter.notifyDataSetChanged();

    }

    @Override
    public void showPropertiesForUser(User user) {
        mNavigator.navigateToUsersProperties(user);
    }

    public void setNavigator(LandlordsListContracts.Navigator navigator) {
        mNavigator = navigator;
    }

    @Override
    public void onClick(User user) {
        mPresenter.landlordIsSelected(user);
    }

    @OnTextChanged(R.id.et_search_landlords_inner)
    public void onTextChanged() {
        String pattern = mSearchBarInnerEditText.getText().toString();
        mPresenter.filterLandlordsWithPattern(pattern);
    }
}
