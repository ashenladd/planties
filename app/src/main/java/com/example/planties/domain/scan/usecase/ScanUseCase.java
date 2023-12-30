package com.example.planties.domain.scan.usecase;

import com.example.planties.core.response.ResponseCallback;
import com.example.planties.data.scan.remote.dto.ScanRes;
import com.example.planties.domain.scan.repository.ScanRepository;

import javax.inject.Inject;

import okhttp3.MultipartBody;
import retrofit2.http.Path;

public class ScanUseCase {
    private final ScanRepository scanRepository;

    @Inject
    public ScanUseCase(ScanRepository scanRepository) {
        this.scanRepository = scanRepository;
    }

    public void scan(@Path("file") MultipartBody.Part file, ResponseCallback<ScanRes> responseCallback) {
        scanRepository.scan(file, responseCallback);
    }

}
