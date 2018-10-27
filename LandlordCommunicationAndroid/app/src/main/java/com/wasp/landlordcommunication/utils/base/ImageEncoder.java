package com.wasp.landlordcommunication.utils.base;

import android.graphics.Bitmap;

public interface ImageEncoder {

    String encodeBitmapToString(Bitmap bitmap);

    Bitmap decodeStringToBitmap(String imageString);
}
