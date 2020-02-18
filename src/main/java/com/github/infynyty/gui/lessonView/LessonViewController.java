package com.github.infynyty.gui.lessonView;

import com.github.infynyty.logic.lessons.AllLessons;
import com.github.infynyty.logic.lessons.Lesson;
import com.github.infynyty.logic.lessons.LessonStep;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextArea;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.io.IOException;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;

public class LessonViewController {

    private static Lesson lesson;

    @FXML
    private Label nameLabel;

    @FXML
    private Label lengthLabel;

    @FXML
    private VBox stepsListVBox;

    @FXML
    private TextArea commentTextBox;

    @FXML
    private Button closeButton;

    @FXML
    private Button editButton;

    @FXML
    private ScrollPane scrollPane;

    @FXML
    private Button deleteButton;


    @FXML
    public void initialize() {
        nameLabel.setText(lesson.getName());
        lengthLabel.setText(lesson.getEstimatedTime() + "");
        commentTextBox.setDisable(true);
        commentTextBox.setText(lesson.getComment());

        Collection<LessonStep> allSteps = lesson.getLessonSteps().values();
        LessonStep[] lessonSteps = allSteps.toArray(new LessonStep[allSteps.size()]);
        Arrays.sort(lessonSteps);

        for(LessonStep lessonStep : lessonSteps) {
            Button button = new Button(lessonStep.getName());
            stepsListVBox.getChildren().add(button);
            button.setPrefWidth(600);
            button.setUserData(lessonStep);

            button.setOnAction(actionEvent -> {
                LessonStepViewController.setLessonStep(lessonStep);
                LessonStepViewController.setLesson(lesson);
                Stage stage = new Stage();
                Parent root = null;
                try {
                    root = FXMLLoader.load(getClass().getResource("/fxml/LessonStepView.fxml"));
                } catch (IOException e) {
                    e.printStackTrace();
                }

                Scene scene = new Scene(root);
                stage.setScene(scene);
                stage.showAndWait();

            });
        }
    }

    @FXML
    void close(ActionEvent event) {
        System.out.println(lesson.getName());
        Stage stage = (Stage) closeButton.getScene().getWindow();
        stage.close();
    }

    //TODO: Implement edit mode
    @FXML
    void edit(ActionEvent event) {

    }

    @FXML
    void delete(ActionEvent event) {
        Lesson.getLessonByName().remove(lesson.getName());
        AllLessons.getInstance().getAllLessonsList().remove(lesson);
        Stage stage = (Stage) deleteButton.getScene().getWindow();
        stage.close();
    }

    public static void setLesson(Lesson lesson) {
        LessonViewController.lesson = lesson;
    }
}

