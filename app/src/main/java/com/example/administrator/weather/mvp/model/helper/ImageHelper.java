package com.example.administrator.weather.mvp.model.helper;

import android.content.Context;
import android.support.annotation.DrawableRes;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.example.administrator.weather.R;

/**
 * Created by Administrator on 2017-3-31.
 */

public class ImageHelper {

    public static void loadImg(Context context, ImageView imageView, @DrawableRes int resourceId) {
        Glide.with(context)
                .load(resourceId)
                .placeholder(R.drawable.bg)
                .centerCrop()
                .into(imageView);
    }

    public static void loadImg(Context context, ImageView imageView, @DrawableRes int resourceId, @DrawableRes int placeHolder) {
        Glide.with(context)
                .load(resourceId)
                .placeholder(placeHolder)
                .centerCrop()
                .into(imageView);
    }

    public static void loadImg(Context context, ImageView imageView, String resourceUrl, @DrawableRes int placeHolder) {
        Glide.with(context)
                .load(resourceUrl)
                .placeholder(placeHolder)
                .centerCrop()
                .into(imageView);
    }
}
