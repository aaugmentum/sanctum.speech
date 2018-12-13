package com.view;

import javafx.event.ActionEvent;
import javafx.scene.input.MouseEvent;

import java.io.File;
import java.util.LinkedList;
import java.util.Queue;

public class DavronsController extends ViewController {

    @Override
    public void voiceBtnOnMouseAction(ActionEvent actionEvent) {
        super.voiceBtnOnMouseAction(actionEvent);
        // Your code goes here
    }

    @Override
    public void voiceBtnOnMousePressed(MouseEvent mouseEvent) {
        super.voiceBtnOnMousePressed(mouseEvent);
        // Your code goes here
        // Start recording
    }

    @Override
    public void voiceBtnOnMouseReleased(MouseEvent mouseEvent) {
        super.voiceBtnOnMouseReleased(mouseEvent);
        // Your code goes here
        // Stop recording
        // Make API request
        // when request returned anything call setVoiceLabelIdle();
        afterRecognized();
    }

    @Override
    public void beforeVideo() {
        super.beforeVideo();
        // Your code goes here
    }

    @Override
    public void afterVideo() {
        super.afterVideo();
        try{
            playNext();
        }catch(java.lang.NullPointerException e){
            stopVideo();
        }
    }

    Queue<File> videoQueue = new LinkedList<>();

    public void playNext() {
        // These 5 lines add the file name to the listView.
        // You can Either leave them or delete and use addCell(recognized_text_string);
        String nextVideoName = videoQueue.peek().getName();
        if (nextVideoName.indexOf(".") > 0) {
            nextVideoName = nextVideoName.substring(0, nextVideoName.lastIndexOf("."));
        }
        addCell(nextVideoName);
        playVideo(videoQueue.poll());
    }

    public void afterRecognized() {
        // Your code goes here
        // assume you have to play 3 videos
        File[] videos = {
                new File("assets/small.mp4"),
                new File("assets/small.mp4"),
                new File("assets/small.mp4"),
        };
        // Add all videos to queue
        for(File video : videos) videoQueue.add(video);
        // Call this function once, then it will be called autimatically in "afterVideo()"
        playNext();
    }
}
