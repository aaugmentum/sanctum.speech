package com.view;

import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.MediaView;

import java.io.File;
import java.net.URL;
import java.util.ResourceBundle;

public class ViewController implements Initializable {
    public ListView listBox;
    public ImageView voiceImage;
    public MediaView videoBox;
    public Label voiceLabel;

    public MediaPlayer mediaPlayer;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        Image i = new Image(new File("assets/micb.png").toURI().toString());
        voiceImage.setImage(i);
        voiceImage.setStyle("-fx-background-color: BLACK");
    }

    public void voiceBtnOnMouseAction(ActionEvent actionEvent) {

    }

    public void voiceBtnOnMousePressed(MouseEvent mouseEvent) {
        setVoiceLabelListening();
        // Start recording
    }

    public void voiceBtnOnMouseReleased(MouseEvent mouseEvent) {
        setVoiceLabelProceeding();
    }

    public void beforeVideo() {
        System.out.println("begin of media");
    }

    public void afterVideo() {
        System.out.println("end of media");
    }

    public final void setVoiceLabelIdle() {
        voiceLabel.textProperty().set("Push");
    }

    public final void setVoiceLabelListening() {
        voiceLabel.textProperty().set("Listening...");
    }

    public final void setVoiceLabelProceeding() {
        voiceLabel.textProperty().set("Proceeding...");
    }

    public final void addCell(String message) {
        listBox.getItems().add(0, message);
        listBox.setStyle("-fx-control-inner-background: rgba(56,176,209);");
    }

    public final void playVideo(File file) {
        if(file != null){
            initPlayer(file.toURI().toString());
        }
    }

    public final void stopVideo() {
        mediaPlayer.stop();
        videoBox.setMediaPlayer(null);
    }

    private final void initPlayer (String uri) {
        if (uri == null)
            return;
        if (mediaPlayer != null) {
            mediaPlayer.stop();
            mediaPlayer = null;
        }

        Media media = new Media(uri);
        mediaPlayer = new MediaPlayer(media);
        mediaPlayer.setAutoPlay(true);
        videoBox.setMediaPlayer(mediaPlayer);
        mediaPlayer.setOnReady(() -> beforeVideo());
        mediaPlayer.setOnEndOfMedia(() -> afterVideo());
    }
}
