package com.github.infynyty;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

import java.io.File;
import java.io.IOException;

public class TEPlaner extends Application {

    /*
    Goals:

    Lessons:
        Individual steps
            Estimated time
            Needed material
            Group?
            Which goal is targeted?
        Comments
        To which series does it belong?
     Calendar with all trainings and their lessons
        Add other events
     Overview of all lessons, sorted by series
     Training goals

     Implementation:

     Save all lessons to json?
     MySQL?
     Account system
     Website?
     */

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/fxml/MainView.fxml"));
        Scene scene = new Scene(root);
        primaryStage.setScene(scene);
        primaryStage.setTitle("TEPlaner");
        primaryStage.show();
    }
}
