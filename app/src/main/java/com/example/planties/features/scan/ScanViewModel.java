package com.example.planties.features.scan;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.net.Uri;
import android.os.Build;
import android.os.Environment;
import android.provider.MediaStore;
import android.util.Log;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.camera.core.ImageCapture;
import androidx.camera.core.ImageCaptureException;
import androidx.core.content.ContextCompat;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.planties.core.response.BaseResultResponse;
import com.example.planties.core.response.ResponseCallback;
import com.example.planties.data.scan.remote.dto.ScanRes;
import com.example.planties.domain.scan.usecase.ScanUseCase;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Locale;

import javax.inject.Inject;

import dagger.hilt.android.lifecycle.HiltViewModel;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;

@HiltViewModel
public class ScanViewModel extends ViewModel {
    private static final String TAG = "CameraXApp";
    private static final String FILENAME_FORMAT = "yyyy-MM-dd-HH-mm-ss-SSS";

    private final MutableLiveData<Uri> capturedImagePath = new MutableLiveData<>();

    public LiveData<Uri> getCapturedImagePath() {
        return capturedImagePath;
    }

    private final MutableLiveData<ScanRes> scanResult = new MutableLiveData<>();

    public LiveData<ScanRes> getScanResult() {
        return scanResult;
    }

    private final ScanUseCase scanUseCase;

    @Inject
    public ScanViewModel(ScanUseCase scanUseCase) {
        this.scanUseCase = scanUseCase;
    }

    public void processEvent(ScanViewEvent event) {
        if (event instanceof ScanViewEvent.OnTakePhoto) {
            takePhoto(((ScanViewEvent.OnTakePhoto) event).getImageCapture(), ((ScanViewEvent.OnTakePhoto) event).getContext());
        }else if(event instanceof ScanViewEvent.OnDiagnose){
            scan(((ScanViewEvent.OnDiagnose) event).getUri(), ((ScanViewEvent.OnDiagnose) event).getContext());
        }
    }

    private void takePhoto(ImageCapture imageCapture, Context context) {
        if (imageCapture == null) {
            return;
        }

        String name = new SimpleDateFormat(FILENAME_FORMAT, Locale.getDefault())
                .format(System.currentTimeMillis());

        ContentValues contentValues = new ContentValues();
        contentValues.put(MediaStore.MediaColumns.DISPLAY_NAME, name);
        contentValues.put(MediaStore.MediaColumns.MIME_TYPE, "image/jpeg");

        if (Build.VERSION.SDK_INT > Build.VERSION_CODES.P) {
            contentValues.put(MediaStore.Images.Media.RELATIVE_PATH, Environment.DIRECTORY_PICTURES + "/CameraX-Image");
        }

        ImageCapture.OutputFileOptions outputOptions = new ImageCapture.OutputFileOptions.Builder(
                context.getContentResolver(),
                MediaStore.Images.Media.EXTERNAL_CONTENT_URI,
                contentValues)
                .build();

        imageCapture.takePicture(
                outputOptions,
                ContextCompat.getMainExecutor(context),
                new ImageCapture.OnImageSavedCallback() {
                    @Override
                    public void onError(@NonNull ImageCaptureException exc) {
                        Log.e(TAG, "Photo capture failed: " + exc.getMessage(), exc);
                    }

                    @Override
                    public void onImageSaved(@NonNull ImageCapture.OutputFileResults output) {
                        String msg = "Photo capture succeeded: " + output.getSavedUri();
                        Toast.makeText(context, msg, Toast.LENGTH_SHORT).show();
                        capturedImagePath.setValue(output.getSavedUri());
                    }
                }
        );
    }

    // Utility method to get the real path of a file from its Uri
    public String getRealPathFromUri(Context context, Uri uri) {
        String filePath = "";
        String[] filePathColumn = {MediaStore.Images.Media.DATA};
        Cursor cursor = null;

        try {
            cursor = context.getContentResolver().query(uri, filePathColumn, null, null, null);
            if (cursor != null && cursor.moveToFirst()) {
                int columnIndex = cursor.getColumnIndex(filePathColumn[0]);
                filePath = cursor.getString(columnIndex);
            }
        } finally {
            if (cursor != null) {
                cursor.close();
            }
        }

        return filePath;
    }

    private void scan (Uri uri, Context context) {
        String filePath = getRealPathFromUri(context, uri);
        RequestBody requestFile = RequestBody.create(MediaType.parse("multipart/form-data"), new File(filePath));
        MultipartBody.Part filePart = MultipartBody.Part.createFormData("file", uri.getLastPathSegment(), requestFile);
        scanUseCase.scan(filePart, new ResponseCallback<ScanRes>() {
            @Override
            public void onSuccess(BaseResultResponse<ScanRes> response) {
                scanResult.setValue(response.getData());
            }

            @Override
            public void onFailure(BaseResultResponse<ScanRes> response) {

            }
        });
    }
}
