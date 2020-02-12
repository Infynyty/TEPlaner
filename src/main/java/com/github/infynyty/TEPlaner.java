package com.github.infynyty;

import com.github.infynyty.logic.calendar.Calendar;
import com.github.infynyty.logic.lessons.AllLessons;
import com.github.infynyty.logic.lessons.Lesson;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import org.simpleframework.xml.Serializer;
import org.simpleframework.xml.core.Persister;

import java.io.File;
import java.io.FileNotFoundException;
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

     Save all lessons to xml
     MySQL?
     Account system
     Website?
     */

    public static final String LESSONS_FILE = "lessons.xml";
    public static final String CALENDAR_FILE = "calendar.xml";
    public static void main(String[] args) throws Exception {
        Serializer serializer = new Persister();
        File file = new File(LESSONS_FILE);
        try {
            AllLessons allLessons = serializer.read(AllLessons.class, file);
            AllLessons.setInstance(allLessons);
            for(Lesson lesson : allLessons.getAllLessonsList()) {
                Lesson.getLessonByName().putIfAbsent(lesson.getName(), lesson);
            }
        } catch (FileNotFoundException e) {

        }

        Serializer calendarSer = new Persister();
        File calendarFile = new File(CALENDAR_FILE);
        try {
            Calendar calendar = calendarSer.read(Calendar.class, calendarFile);
            Calendar.setInstance(calendar);
        } catch (FileNotFoundException e) {

        }

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
        try {
            Serializer serializer = new Persister();
            File file = new File(LESSONS_FILE);
            serializer.write(AllLessons.getInstance(), file);
        } catch (Exception e) {
            e.printStackTrace();
        }
        try {
            Serializer serializer = new Persister();
            File file = new File(CALENDAR_FILE);
            serializer.write(Calendar.getInstance(), file);
        } catch (Exception e) {
            e.printStackTrace();
        }
        super.stop();
    }
}
