package com.view;

import javafx.event.ActionEvent;
import javafx.scene.input.MouseEvent;

import java.io.File;

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
    }

    @Override
    public void voiceBtnOnMouseReleased(MouseEvent mouseEvent) {
        super.voiceBtnOnMouseReleased(mouseEvent);
        // Stop recording
        // Make API request
        // when request returned anything call setVoiceLabelIdle();
        // then in order to add recognized text use addCell(String message);
        // then after recognized use playVideo();
        addCell("Recognized text");
        playVideo(new File("assets/small.mp4"));
    }
}
