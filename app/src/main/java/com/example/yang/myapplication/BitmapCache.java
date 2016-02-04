package com.example.yang.myapplication;

import android.graphics.Bitmap;
import android.util.Log;
import android.util.LruCache;

import com.android.volley.toolbox.ImageLoader;

/**
 * Created by Yang on 2016/1/27.
 */
public class BitmapCache implements ImageLoader.ImageCache {

    private static final String TAG = "BitmapCache";
    public LruCache<String,Bitmap> cache;
    public int max = 10*1024*1024;

    public BitmapCache(){
        cache = new LruCache<String,Bitmap>(max){
            @Override
            protected  int sizeOf(String key,Bitmap value){
                return value.getRowBytes()*value.getHeight();
            }
        };


    }
    @Override
    public Bitmap getBitmap(String s) {
        Log.i(TAG,s);
        return cache.get(s);
    }

    @Override
    public void putBitmap(String s, Bitmap bitmap) {
        Log.i(TAG,s);

        if (bitmap != null) {
            cache.put(s, bitmap);
        }
    }
}
