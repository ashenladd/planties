package com.example.planties.features.scan;

import android.content.Context;

import androidx.camera.core.ImageCapture;

public abstract class ScanViewEvent {
    public static class OnTakePhoto extends ScanViewEvent {
        private Context context;
        private ImageCapture imageCapture;
        public OnTakePhoto(Context context, ImageCapture imageCapture) {
            this.context = context;
            this.imageCapture = imageCapture;
        }
        public Context getContext() {
            return context;
        }

        public void setContext(Context context) {
            this.context = context;
        }

        public ImageCapture getImageCapture() {
            return imageCapture;
        }

        public void setImageCapture(ImageCapture imageCapture) {
            this.imageCapture = imageCapture;
        }
    }
}
