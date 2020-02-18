package com.github.infynyty.gui.mainview;

import com.github.infynyty.gui.addLesson.AddLessonController;
import com.github.infynyty.gui.lessonView.LessonViewController;
import com.github.infynyty.logic.calendar.Calendar;
import com.github.infynyty.logic.calendar.CalendarEvent;
import com.github.infynyty.logic.calendar.TrainingEvent;
import com.github.infynyty.logic.lessons.AllLessons;
import com.github.infynyty.logic.lessons.Lesson;
import com.github.infynyty.logic.lessons.LessonStep;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Tooltip;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Arrays;
import java.util.Collection;

public class MainViewController {

    private boolean isCalendar = false;

    @FXML
    private Button addLessonButton;

    @FXML
    private VBox calendar;

    @FXML
    private AnchorPane scrollAnchorPane;

    @FXML
    private Button switchButton;

    @FXML
    public void initialize() {
        createAllLessons();
    }

    @FXML
    private void addLesson(ActionEvent event) throws IOException {
        if(!isCalendar) {
            Stage stage = new Stage();
            Parent root = FXMLLoader.load(getClass().getResource("/fxml/AddLesson.fxml"));
            Scene scene = new Scene(root);
            stage.setScene(scene);


            stage.initModality(Modality.APPLICATION_MODAL);
            stage.setTitle("Add Lesson");
            stage.showAndWait();
            if(AddLessonController.addedLesson) {
                AddLessonController.addedLesson = false;
                Lesson lesson = AddLessonController.getLesson();
                Button button = new Button(lesson.getName());
                button.setTooltip(new Tooltip("Name: " + lesson.getName() + "\nLength: " + lesson.getEstimatedTime()));
                System.out.println(calendar.getWidth());
                button.setPrefWidth(1200);
                button.setPrefHeight(50);
                calendar.getChildren().add(button);

                button.setOnAction(actionEvent -> {
                    LessonViewController.setLesson(Lesson.getLessonByName().get(button.getText()));
                    Stage lessonStage = new Stage();
                    Parent lessonRoot = null;
                    try {
                        lessonRoot = FXMLLoader.load(getClass().getResource("/fxml/LessonView.fxml"));
                    } catch (IOException e) {
                        e.printStackTrace();
                    }

                    Scene lessonScene = new Scene(lessonRoot);
                    lessonStage.setScene(lessonScene);
                    lessonStage.showAndWait();

                });

                AddLessonController.setLesson(null);
            }
        } else {
            Stage stage = new Stage();
            Parent root = FXMLLoader.load(getClass().getResource("/fxml/AddEvent.fxml"));
            Scene scene = new Scene(root);
            stage.setScene(scene);


            stage.initModality(Modality.APPLICATION_MODAL);
            stage.setTitle("Add Event");
            stage.showAndWait();
        }

    }

    @FXML
    void switchViews(ActionEvent event) {
        if(!isCalendar) {
            isCalendar = true;
            switchButton.setText("Show lessons");
            addLessonButton.setText("Add events");
            calendar.getChildren().clear();
            Collection<CalendarEvent> allCalendarEvents = Calendar.getInstance().getCalendarEventByName().values();
            CalendarEvent[] calendarEvents = allCalendarEvents.toArray(new CalendarEvent[allCalendarEvents.size()]);
            Arrays.sort(calendarEvents);
            for(CalendarEvent calendarEvent : calendarEvents) {
                Button button = new Button(calendarEvent.getName());
                button.setUserData(calendarEvent);
                button.setPrefWidth(1200);
                button.setPrefHeight(50);
                calendar.getChildren().add(button);
                button.setOnAction(actionEvent -> {
                    //TODO: Add

                });
            }
        } else {
            isCalendar = false;
            switchButton.setText("Show calendar");
            addLessonButton.setText("Add lessons");
            calendar.getChildren().clear();
            createAllLessons();
        }

    }

    private void createAllLessons() {
        for(Lesson lesson : AllLessons.getInstance().getAllLessonsList()) {
            Button button = new Button(lesson.getName());
            button.setTooltip(new Tooltip("Name: " + lesson.getName() + "\nLength: " + lesson.getEstimatedTime()));
            button.setPrefWidth(1200);
            button.setPrefHeight(50);
            calendar.getChildren().add(button);

            button.setOnAction(actionEvent -> {
                LessonViewController.setLesson(Lesson.getLessonByName().get(button.getText()));
                Stage stage = new Stage();
                Parent root = null;
                try {
                    root = FXMLLoader.load(getClass().getResource("/fxml/LessonView.fxml"));
                } catch (IOException e) {
                    e.printStackTrace();
                }

                Scene scene = new Scene(root);
                stage.setScene(scene);
                stage.showAndWait();

            });
        }
    }

}
