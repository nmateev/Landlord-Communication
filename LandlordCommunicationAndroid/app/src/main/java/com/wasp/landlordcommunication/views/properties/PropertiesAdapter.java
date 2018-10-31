package com.wasp.landlordcommunication.views.properties;

import android.graphics.Bitmap;
import android.graphics.Color;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.wasp.landlordcommunication.R;
import com.wasp.landlordcommunication.models.Property;
import com.wasp.landlordcommunication.utils.Constants;
import com.wasp.landlordcommunication.utils.base.ImageEncoder;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

import static com.wasp.landlordcommunication.utils.Constants.COLOUR_GREEN_STRING;
import static com.wasp.landlordcommunication.utils.Constants.COLOUR_RED_STRING;
import static com.wasp.landlordcommunication.utils.Constants.RENT_IS_DUE_AT;

public class PropertiesAdapter extends RecyclerView.Adapter<PropertiesAdapter.PropertyViewHolder> {
    private static final int HEIGHT_DIVIDER = 4;
    private final ImageEncoder mImageEncoder;
    private OnPropertyItemClickListener mOnPropertyItemClickListener;
    private List<Property> mPropertiesList;


    @Inject
    public PropertiesAdapter(ImageEncoder imageEncoder) {
        mImageEncoder = imageEncoder;
        mPropertiesList = new ArrayList<>();

    }

    @NonNull
    @Override
    public PropertiesAdapter.PropertyViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int viewType) {
        View view = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.property_item_recycler_view, viewGroup, false);


        int height = viewGroup.getMeasuredHeight() / HEIGHT_DIVIDER;


        ViewGroup.LayoutParams layoutParams = view.getLayoutParams();
        layoutParams.height = height;
        view.setLayoutParams(layoutParams);
        view.setMinimumHeight(height);

        return new PropertyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull PropertiesAdapter.PropertyViewHolder propertyViewHolder, int position) {
        propertyViewHolder.setOnClickListener(mOnPropertyItemClickListener);
        propertyViewHolder.bind(mPropertiesList.get(position));
    }

    @Override
    public int getItemCount() {
        return mPropertiesList.size();
    }

    public Property getPropertyItem(int position) {
        return mPropertiesList.get(position);
    }

    public void clear() {
        mPropertiesList.clear();
    }

    public void addAll(List<Property> properties) {
        mPropertiesList.addAll(properties);
    }

    public void setOnPropertyItemClickListener(PropertiesAdapter.OnPropertyItemClickListener onPropertyItemClickListener) {
        this.mOnPropertyItemClickListener = onPropertyItemClickListener;
    }


    public class PropertyViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.iv_property_picture)
        ImageView mPropertyImageView;

        @BindView(R.id.tv_property_address)
        TextView mPropertyAddressTextView;

        @BindView(R.id.tv_property_rent_price)
        TextView mPropertyRentPriceTextView;

        @BindView(R.id.tv_property_rent_due_date)
        TextView mPropertyRentDueDateTextView;

        @BindView(R.id.tv_is_rent_paid)
        TextView mPropertyIsRentPaidTextView;

        private OnPropertyItemClickListener mOnPropertyItemClickListener;
        private Property mProperty;


        private PropertyViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);
        }

        private void bind(Property property) {

            if (Objects.equals(property.getPropertyPicture(), null)) {
                mPropertyImageView.setImageResource(R.drawable.defaultpropertypicture);
            } else {
                Bitmap propertyImage = mImageEncoder.decodeStringToBitmap(property.getPropertyPicture());
                if (Objects.equals(propertyImage, null)) {
                    mPropertyImageView.setImageResource(R.drawable.defaultpropertypicture);
                } else {
                    mPropertyImageView.setImageBitmap(propertyImage);
                    propertyImage = null;
                }
            }

            mPropertyAddressTextView.setText(property.getPropertyAddress());
            mPropertyRentPriceTextView.setText(String.valueOf(property.getRentPrice()));
            mPropertyRentDueDateTextView.setText(new StringBuilder()
                    .append(RENT_IS_DUE_AT)
                    .append(property.getDueDate())
                    .toString());

            if (property.getRentPaid()) {
                mPropertyIsRentPaidTextView.setText(Constants.PAID);
                mPropertyIsRentPaidTextView.setTextColor(Color.parseColor(COLOUR_GREEN_STRING));

            } else {
                mPropertyIsRentPaidTextView.setText(Constants.NOT_PAID);
                mPropertyIsRentPaidTextView.setTextColor(Color.parseColor(COLOUR_RED_STRING));
            }

            mProperty = property;

        }

        @OnClick
        public void onClick() {
            mOnPropertyItemClickListener.onClick(mProperty);
        }

        private void setOnClickListener(OnPropertyItemClickListener onPropertyItemClickListener) {
            mOnPropertyItemClickListener = onPropertyItemClickListener;
        }

    }

    interface OnPropertyItemClickListener {
        void onClick(Property property);
    }
}
