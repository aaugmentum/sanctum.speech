package com.api;

public interface ApiInterface {
    void onSuccess(String transcript);
    void onError(ApiError error);
}
