package com.wasp.landlordcommunication.repositories;

import android.graphics.Bitmap;
import android.util.LruCache;

import com.wasp.landlordcommunication.repositories.base.BitmapCacheRepository;

public class LruBitmapCacheRepository implements BitmapCacheRepository {
    private final LruCache<String, Bitmap> mLruCache;

    public LruBitmapCacheRepository() {
        mLruCache = new LruCache<String, Bitmap>(1024);
    }

    @Override
    public void addBitmapToBitmapCache(Bitmap bitmap, String key) {

        mLruCache.put(key, bitmap);

    }

    @Override
    public Bitmap getBitmapFromCache(String key) {
        return mLruCache.get(key);
    }
}

