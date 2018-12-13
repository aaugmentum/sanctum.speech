package com.api.helper;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Header;
import retrofit2.http.Headers;
import retrofit2.http.POST;
import com.api.model.reponse.ResponseResult;
import com.api.model.request.RequestBody;

public interface GoogleSpeechAPI {
    @Headers("Content-type: application/json")
    @POST(ApiConstants.VERSION + ApiConstants.REQUEST_RECOGNIZE)
    Call<ResponseResult> recognize(@Body RequestBody body, @Header("Authorization") String authHeader);
}
