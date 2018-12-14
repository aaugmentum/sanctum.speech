package com.controller;

import com.api.Api;
import com.api.ApiError;
import com.api.ApiInterface;
import com.record.AudioRecorder;
import com.view.ViewController;
import javafx.application.Platform;
import javafx.concurrent.Task;
import javafx.event.ActionEvent;
import javafx.scene.input.MouseEvent;

import java.io.File;
import java.util.Vector;

public class Controller extends ViewController implements ApiInterface {
    private Api api;
    private RecordThread recorderThread;
    private Vector<File> videoQueue;
    private boolean isActive;

    public Controller() {
        api = new Api(this);
        videoQueue = new Vector<>();
        isActive = true;
    }

    public void voiceBtnOnMouseAction(ActionEvent actionEvent) {
        super.voiceBtnOnMouseAction(actionEvent);
    }

    @Override
    public void voiceBtnOnMousePressed(MouseEvent mouseEvent) {
        if(isActive){
            super.voiceBtnOnMousePressed(mouseEvent);
            recorderThread = new RecordThread();
            recorderThread.start();
            System.out.println("The button became inactive");
        }
    }

    @Override
    public void voiceBtnOnMouseReleased(MouseEvent mouseEvent) {
        if(isActive){
            super.voiceBtnOnMouseReleased(mouseEvent);
            System.out.println("Start request...");
            api.startRequest(recorderThread.stopRecording());
            isActive = false;
        }

    }

    @Override
    public void onSuccess(String transcript) {
        System.out.println(transcript);
        Platform.runLater(() -> {
            setVoiceLabelIdle();
            addCell(transcript);
            String lowerCase = transcript.toLowerCase();
            for(int i = 0; i<transcript.length(); i++){
                if(lowerCase.charAt(i) != ' '){
                    String fileName = "assets/" + lowerCase.charAt(i) + ".mp4";
                    File file = new File(fileName);
                    videoQueue.add(file);
                }
            }
            playVideo(videoQueue.firstElement());
            videoQueue.removeElementAt(0);
        });
    }

    @Override
    public void onError(ApiError error) {
        Platform.runLater(this::setVoiceLabelIdle);
        isActive = true;
    }

    private class RecordThread extends Thread {
        private AudioRecorder recorder;
        @Override
        public void run() {
            super.run();
            System.out.println("Start recording...");
            recorder = new AudioRecorder();
            recorder.start();
        }

        File stopRecording() {
            interrupt();
            return recorder.stop();
        }
    }

    @Override
    public void afterVideo() {
        super.afterVideo();
        if (!videoQueue.isEmpty()) {
            playVideo(videoQueue.firstElement());
            videoQueue.removeElementAt(0);
        }else {
            isActive = true;
        }
    }
}
