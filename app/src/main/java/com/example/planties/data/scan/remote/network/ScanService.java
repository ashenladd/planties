package com.example.planties.data.scan.remote.network;

import com.example.planties.data.scan.remote.dto.ScanRes;

import okhttp3.MultipartBody;
import retrofit2.Call;
import retrofit2.http.Path;
import retrofit2.http.Url;

public interface ScanService {
    Call<ScanRes> scan(@Url String url, @Path("file") MultipartBody.Part filePart);
}
