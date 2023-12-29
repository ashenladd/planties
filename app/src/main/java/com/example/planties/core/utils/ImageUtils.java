package com.example.planties.core.utils;

import android.content.ContentResolver;
import android.content.ContentUris;
import android.content.Context;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Matrix;
import android.media.ExifInterface;
import android.net.Uri;
import android.os.Environment;
import android.provider.MediaStore;
import android.util.Base64;
import android.util.Log;
import android.widget.ImageView;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.file.Files;

public class ImageUtils {

    private static final String TAG = "ImageUtils";

    public static void displayImage(Context context, Uri imageUri, ImageView imageView) {
        try {
            Bitmap bitmap = MediaStore.Images.Media.getBitmap(context.getContentResolver(), imageUri);
            int rotation = getRotationFromExif(context, imageUri);
            Bitmap rotatedBitmap = rotateBitmap(bitmap, rotation);
            imageView.setImageBitmap(rotatedBitmap);
        } catch (IOException e) {
            Log.e(TAG, "Error displaying image: " + e.getMessage(), e);
        }
    }

    private static int getRotationFromExif(Context context, Uri imageUri) {
        try {
            ExifInterface exif = new ExifInterface(context.getContentResolver().openInputStream(imageUri));
            int orientation = exif.getAttributeInt(ExifInterface.TAG_ORIENTATION, ExifInterface.ORIENTATION_NORMAL);
            switch (orientation) {
                case ExifInterface.ORIENTATION_ROTATE_90:
                    return 90;
                case ExifInterface.ORIENTATION_ROTATE_180:
                    return 180;
                case ExifInterface.ORIENTATION_ROTATE_270:
                    return 270;
                default:
                    return 0;
            }
        } catch (IOException e) {
            Log.e(TAG, "Error reading Exif data: " + e.getMessage(), e);
            return 0;
        }
    }

    private static Bitmap rotateBitmap(Bitmap source, int rotation) {
        Matrix matrix = new Matrix();
        matrix.postRotate(rotation);
        return Bitmap.createBitmap(source, 0, 0, source.getWidth(), source.getHeight(), matrix, true);
    }

    public static void deleteImage(Context context, long imageId) {
        // Query the MediaStore to get the file path of the image
        String[] projection = {MediaStore.Images.Media.DATA};
        Uri imageUri = ContentUris.withAppendedId(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, imageId);

        ContentResolver contentResolver = context.getContentResolver();
        Cursor cursor = contentResolver.query(imageUri, projection, null, null, null);

        if (cursor != null && cursor.moveToFirst()) {
            int columnIndex = cursor.getColumnIndexOrThrow(MediaStore.Images.Media.DATA);
            String filePath = cursor.getString(columnIndex);

            // Delete the file using File class
            File file = new File(filePath);
            boolean deleted = file.delete();
            if (deleted) {
                Log.d("ImageUtils", "Image deleted successfully");
                // You may also want to notify the MediaStore that the image has been deleted
                contentResolver.delete(imageUri, null, null);
            } else {
                Log.e("ImageUtils", "Failed to delete image");
            }

            cursor.close();
        }
    }

    public static String convertImageUriToBase64(Context context, Uri imageUri) {
        try {
            InputStream inputStream = context.getContentResolver().openInputStream(imageUri);
            Bitmap bitmap = BitmapFactory.decodeStream(inputStream);
            int rotation = getRotationFromExif(context, imageUri);
            Bitmap rotatedBitmap = rotateBitmap(bitmap, rotation);
            ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
            rotatedBitmap.compress(Bitmap.CompressFormat.JPEG, 100, byteArrayOutputStream);

            byte[] imageBytes = byteArrayOutputStream.toByteArray();
            return java.util.Base64.getEncoder().encodeToString(imageBytes);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            return null;
        }
    }

    public static Bitmap convertBase64ToImage(String base64String) {
        try {
            byte[] imageBytes = Base64.decode(base64String, Base64.DEFAULT);
            return BitmapFactory.decodeByteArray(imageBytes, 0, imageBytes.length);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    private static int fileCounter = 0;

    public static void convertUriToJpg(Context context, Uri contentUri) {
        try {
            // Get the InputStream from the ContentResolver
            ContentResolver resolver = context.getContentResolver();
            InputStream inputStream = resolver.openInputStream(contentUri);

            // Decode the InputStream into a Bitmap
            Bitmap bitmap = BitmapFactory.decodeStream(inputStream);

            // Create a File to save the JPG image
            File jpgFile = getOutputMediaFile();

            // Write the Bitmap data to the JPG file
            OutputStream outputStream = Files.newOutputStream(jpgFile.toPath());
            bitmap.compress(Bitmap.CompressFormat.JPEG, 100, outputStream);
            outputStream.close();

            // Optionally, you can use the jpgFile path or URI as needed
            String jpgFilePath = jpgFile.getAbsolutePath();
            Uri jpgFileUri = Uri.fromFile(jpgFile);

            // Now you have the image saved as a JPG file
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static File getOutputMediaFile() {
        // Get the directory for saving media files (you may want to customize this)
        File mediaStorageDir = new File(Environment.getExternalStoragePublicDirectory(
                Environment.DIRECTORY_PICTURES), "YourAppFolder");

        // Create the storage directory if it does not exist
        if (!mediaStorageDir.exists()) {
            if (!mediaStorageDir.mkdirs()) {
                return null;
            }
        }

        // Create a media file name with a counter to make it unique
        String timeStamp = String.valueOf(System.currentTimeMillis());
        File mediaFile = new File(mediaStorageDir.getPath() + File.separator +
                "IMG_" + timeStamp + "_" + fileCounter + ".jpg");

        // Increment the counter for the next image
        fileCounter++;

        return mediaFile;
    }
}
