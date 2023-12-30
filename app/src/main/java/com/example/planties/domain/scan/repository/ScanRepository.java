package com.example.planties.domain.scan.repository;

import com.example.planties.core.response.ResponseCallback;
import com.example.planties.data.scan.remote.dto.ScanRes;

import okhttp3.MultipartBody;
import retrofit2.http.Path;

public interface ScanRepository {
    void scan(@Path("'file'") MultipartBody.Part file, ResponseCallback<ScanRes> responseCallback);
}
