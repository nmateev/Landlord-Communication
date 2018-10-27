package com.wasp.landlordcommunication.utils;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Base64;

import java.io.ByteArrayOutputStream;

public class ImageEncoder implements com.wasp.landlordcommunication.utils.base.ImageEncoder {

    @Override
    public String encodeBitmapToString(Bitmap bitmap) {

        String imageString;
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        try {
            bitmap.compress(Bitmap.CompressFormat.PNG, Constants.IMAGE_QUALITY, byteArrayOutputStream);
            byte[] imageArray = byteArrayOutputStream.toByteArray();

            imageString = Base64.encodeToString(imageArray, Base64.DEFAULT);
            return imageString;

        } catch (Exception e) {
            return null;
        }
    }

    @Override
    public Bitmap decodeStringToBitmap(String imageString) {
        Bitmap decodedImage;
        try {
            byte[] encodeByte = Base64.decode(imageString, Base64.DEFAULT);
            decodedImage = BitmapFactory.decodeByteArray(encodeByte, 0, encodeByte.length);

            return decodedImage;

        } catch (Exception e) {
            return null;
        }
    }
}
