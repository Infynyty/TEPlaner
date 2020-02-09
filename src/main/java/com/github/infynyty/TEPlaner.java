package com.github.infynyty;

import com.github.infynyty.logic.AllLessons;
import com.github.infynyty.logic.Lesson;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import org.simpleframework.xml.Serializer;
import org.simpleframework.xml.core.Persister;

import java.beans.XMLEncoder;
import java.io.*;
import java.util.ArrayList;

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

    public static final String LESSONS_FILE = "lessons.xml";
    public static void main(String[] args) throws Exception {
        Serializer serializer = new Persister();
        File file = new File(LESSONS_FILE);
        AllLessons allLessons = serializer.read(AllLessons.class, file);
        AllLessons.setInstance(allLessons);
        System.out.println(allLessons.getAllLessonsList());
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

    @Override
    public void stop() throws Exception {
        XMLEncoder encoder = null;
        try {
            Serializer serializer = new Persister();
            File file = new File(LESSONS_FILE);
            serializer.write(AllLessons.getInstance(), file);
        } catch (Exception e) {
            e.printStackTrace();
        }
        super.stop();
    }
}
