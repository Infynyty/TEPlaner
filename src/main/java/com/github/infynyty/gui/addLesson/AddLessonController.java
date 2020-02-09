package com.github.infynyty.gui.addLesson;

import com.github.infynyty.logic.LessonStep;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;

public class AddLessonController {
    @FXML
    private TextField lessonName;

    @FXML
    private Button addStepButton;

    @FXML
    private Button saveLessonButton;

    @FXML
    private Button closeWindowButton;

    @FXML
    private TextArea commentField;

    @FXML
    void closeWindow(ActionEvent event) {

    }

    @FXML
    void saveLesson(ActionEvent event) {

    }
    @FXML
    void addStep(ActionEvent event) throws IOException {
        Stage stage = new Stage();
        Parent root = FXMLLoader.load(getClass().getResource("/fxml/AddLessonStep.fxml"));
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.setTitle("Add Lesson Step");
        stage.showAndWait();
        if(AddLessonStepController.addedStage) {
            AddLessonStepController.addedStage = false;
            LessonStep lessonStep = AddLessonStepController.getLessonStep();
            AddLessonStepController.setLessonStep(null);
            System.out.println(lessonStep.getName());
            addStep(event);
        }
    }
}
