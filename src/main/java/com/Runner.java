package com;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Runner extends Application {
    @Override
    public void start(Stage primaryStage) throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("sample.fxml"));
        primaryStage.setTitle("Sanctum");
        Scene scene = new Scene(root, 1200, 900);
        scene.getStylesheets().add(getClass().getResource("style.css").toExternalForm());
//        primaryStage.initStyle(StageStyle.TRANSPARENT);
        primaryStage.setOpacity(0.7);


//        scene.setFill(Color.CRIMSON);
        primaryStage.setScene(scene);
        primaryStage.show();
    }


    public static void main(String[] args) {
        launch(args);
    }
}
