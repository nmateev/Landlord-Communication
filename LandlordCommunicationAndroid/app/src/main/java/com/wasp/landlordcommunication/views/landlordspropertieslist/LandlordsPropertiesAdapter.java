package com.wasp.landlordcommunication.views.landlordspropertieslist;

import android.graphics.Bitmap;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.wasp.landlordcommunication.R;
import com.wasp.landlordcommunication.models.Property;
import com.wasp.landlordcommunication.utils.base.ImageEncoder;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

import static com.wasp.landlordcommunication.utils.Constants.PRICE;

public class LandlordsPropertiesAdapter extends RecyclerView.Adapter<LandlordsPropertiesAdapter.PropertyViewHolder> {
    private static final int HEIGHT_DIVIDER = 3;
    private final ImageEncoder mImageEncoder;
    private OnPropertyItemClickListener mOnPropertyItemClickListener;
    private List<Property> mPropertiesList;


    @Inject
    public LandlordsPropertiesAdapter(ImageEncoder imageEncoder) {
        mImageEncoder = imageEncoder;
        mPropertiesList = new ArrayList<>();

    }

    @NonNull
    @Override
    public LandlordsPropertiesAdapter.PropertyViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int viewType) {
        View view = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.property_landlord_item_layout, viewGroup, false);

        int height = viewGroup.getMeasuredHeight() / HEIGHT_DIVIDER;

        ViewGroup.LayoutParams layoutParams = view.getLayoutParams();
        layoutParams.height = height;
        view.setLayoutParams(layoutParams);
        view.setMinimumHeight(height);

        return new PropertyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull LandlordsPropertiesAdapter.PropertyViewHolder propertyViewHolder, int position) {
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

    public void setOnPropertyItemClickListener(OnPropertyItemClickListener onPropertyItemClickListener) {
        this.mOnPropertyItemClickListener = onPropertyItemClickListener;
    }


    public class PropertyViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.tv_property_address)
        TextView mPropertyAddressTextView;

        @BindView(R.id.iv_property_picture)
        ImageView mPropertyImageView;

        @BindView(R.id.tv_property_rent_price)
        TextView mPropertyRentPriceTextView;


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
            String price = PRICE + String.valueOf(property.getRentPrice());
            mPropertyRentPriceTextView.setText(price);

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
