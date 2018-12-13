package com.controller;

import com.api.Api;
import com.api.ApiError;
import com.api.ApiInterface;
import com.record.AudioRecorder;

public class Controller implements ApiInterface {
    private AudioRecorder recorder;
    private Api api;

    public Controller() {
        api = new Api(this);
        recorder = new AudioRecorder();
    }

    public void start(){
        Thread t = new Thread(() -> {
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            System.out.println("Start request...");
            api.startRequest(recorder.stop());
        });

        t.start();
        System.out.println("Start recording...");
        recorder.start();
    }



    @Override
    public void onSuccess(String transcript) {
        System.out.println(transcript);
    }

    @Override
    public void onError(ApiError error) {

    }
}
