package com.github.infynyty.gui.addLesson;

import com.github.infynyty.logic.AllLessons;
import com.github.infynyty.logic.Lesson;
import com.github.infynyty.logic.LessonStep;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.ArrayList;

public class AddLessonController {

    public static boolean addedLesson;
    private ArrayList<LessonStep> lessonSteps = new ArrayList<>();
    private static Lesson lesson;

    @FXML
    private Label errorLabel;

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
    private VBox stepListVBox;

    @FXML
    void closeWindow(ActionEvent event) {

    }

    @FXML
    void saveLesson(ActionEvent event) {
        if(lessonName.getText().isBlank()) {
            errorLabel.setText("The name is missing!");
            errorLabel.setStyle("-fx-text-fill: red;");
            return;
        }

        if(lessonSteps.isEmpty()) {
            errorLabel.setText("There are no lesson steps!");
            errorLabel.setStyle("-fx-text-fill: red;");
            return;
        }

        Lesson lesson = new Lesson();
        lesson.setName(lessonName.getText());
        lesson.setLessonSteps(lessonSteps);
        int totalTime = 0;
        for(LessonStep lessonStep : lessonSteps) {
            totalTime += lessonStep.getEstimatedTime();
        }
        lesson.setEstimatedTime(totalTime);
        lesson.setComment(commentField.getText());
        AllLessons.getInstance().getAllLessonsList().add(lesson);
        addedLesson = true;
        Stage stage = (Stage) saveLessonButton.getScene().getWindow();
        stage.close();
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
            lessonSteps.add(AddLessonStepController.getLessonStep());
            AddLessonStepController.setLessonStep(null);

            Button button = new Button("Name: " + lessonStep.getName() + " // Length: " + lessonStep.getEstimatedTime() +
                    " // Groups needed: " + lessonStep.isGroupNeeded() + " // Materials: " + lessonStep.getMaterials().toArray().toString());
            button.setPrefWidth(stepListVBox.getWidth());
            stepListVBox.getChildren().add(button);


            addStep(event);
        }
    }

    public static Lesson getLesson() {
        return lesson;
    }

    public static void setLesson(Lesson lesson) {
        AddLessonController.lesson = lesson;
    }
}
