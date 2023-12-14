package com.example.planties.core.response;

public interface ResponseCallback<T> {
    void onSuccess(BaseResultResponse<T> response);
    void onFailure(BaseResultResponse<T> response);
}