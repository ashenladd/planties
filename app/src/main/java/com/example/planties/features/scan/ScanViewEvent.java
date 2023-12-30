package com.example.planties.features.scan;

import android.content.Context;
import android.net.Uri;

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

    public static class OnDiagnose extends ScanViewEvent {
        private Uri uri;
        private Context context;

        public OnDiagnose(Uri uri, Context context) {
            this.uri = uri;
            this.context = context;
        }

        public Uri getUri() {
            return uri;
        }

        public void setUri(Uri uri) {
            this.uri = uri;
        }

        public Context getContext() {
            return context;
        }

        public void setContext(Context context) {
            this.context = context;
        }
    }
}
