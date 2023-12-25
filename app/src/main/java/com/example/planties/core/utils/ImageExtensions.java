package com.example.planties.core.utils;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Matrix;
import android.media.ExifInterface;
import android.net.Uri;
import android.provider.MediaStore;
import android.util.Log;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.example.planties.R;

import java.io.IOException;

public class ImageExtensions {
    public static void loadProfileImage(ImageView imageView, Context context, String url) {
        Glide.with(context.getApplicationContext())
                .load(url)
                .fitCenter()
                .circleCrop()
                .placeholder(R.drawable.img_profile)
                .error(R.drawable.img_profile)
                .into(imageView);
    }

    public static void loadPlantImage(ImageView imageView, Context context, String url) {
        Glide.with(context.getApplicationContext())
                .load(url)
                .fitCenter()
                .placeholder(R.drawable.img_plants)
                .error(R.drawable.img_plants)
                .into(imageView);
    }
}

