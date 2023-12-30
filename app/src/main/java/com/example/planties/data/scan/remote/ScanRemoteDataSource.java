package com.example.planties.data.scan.remote;

import com.example.planties.data.scan.remote.dto.ScanRes;

import okhttp3.MultipartBody;
import retrofit2.Call;
import retrofit2.http.Part;
import retrofit2.http.Path;

public interface ScanRemoteDataSource {
    Call<ScanRes> scan(@Part MultipartBody.Part file);
}
