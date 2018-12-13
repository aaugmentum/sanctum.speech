package com.api;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import com.api.helper.ApiConstants;
import com.api.helper.GoogleSpeechAPI;
import com.api.helper.RetrofitClient;
import com.api.model.reponse.ResponseResult;
import com.api.model.request.RecognitionAudio;
import com.api.model.request.RecognitionConfig;
import com.api.model.request.RequestBody;

import java.io.File;

import static com.api.helper.AudioToBase64.encode;

public class Api implements Callback<ResponseResult> {
    private ApiInterface apiInterface;

    public Api(ApiInterface apiInterface) {
        this.apiInterface = apiInterface;
    }

    public void startRequest(File file) {
        Retrofit retrofit = RetrofitClient.getClient();

        GoogleSpeechAPI api = retrofit.create(GoogleSpeechAPI.class);

        RequestBody body = new RequestBody();
        RecognitionConfig config = new RecognitionConfig();
        config.setLanguageCode("en-US");

        RecognitionAudio audio = new RecognitionAudio();
        audio.setContent(encode(file));

        body.setConfig(config);
        body.setAudio(audio);

        api.recognize(body, "Bearer " + ApiConstants.KEY).enqueue(this);
    }

    @Override
    public void onResponse(Call<ResponseResult> call, Response<ResponseResult> response) {
        if (response.body() != null && response.isSuccessful()) {
            success(response.body());
        } else {
            int code  = response.code();

            switch (code) {
                case 401:
                    System.out.println(ApiError.UNAUTHORIZED);
                    apiInterface.onError(ApiError.UNAUTHORIZED);
                    break;
                default:
                    apiInterface.onError(ApiError.OTHER);
                    break;
            }
        }

    }

    private void success(ResponseResult result) {
        if (result.getResults() != null) {
            String transcript = result.getResults().get(0).getAlternatives().get(0).getTranscript();
            apiInterface.onSuccess(transcript);
        } else {
            System.out.print(ApiError.NOTHINIG_RECOGNIZED);
            apiInterface.onError(ApiError.NOTHINIG_RECOGNIZED);
        }
    }

    @Override
    public void onFailure(Call<ResponseResult> call, Throwable t) {
        System.out.print(ApiError.INTERNET_ERROR);
        apiInterface.onError(ApiError.INTERNET_ERROR);
    }
}
