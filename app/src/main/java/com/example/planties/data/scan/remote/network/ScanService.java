package com.example.planties.data.scan.remote.network;

import com.example.planties.core.Constant;
import com.example.planties.data.scan.remote.dto.ScanRes;

import okhttp3.MultipartBody;
import retrofit2.Call;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;
import retrofit2.http.Path;
import retrofit2.http.Url;

public interface ScanService {
    @Multipart
    @POST(Constant.PREDICT)
    Call<ScanRes> scan(@Part MultipartBody.Part filePart);
}
