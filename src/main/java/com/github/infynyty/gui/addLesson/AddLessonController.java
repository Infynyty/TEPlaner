package com.github.infynyty.gui.addLesson;

import com.github.infynyty.gui.lessonView.LessonStepViewController;
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
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;

public class AddLessonController {

    public static boolean addedLesson;
    //private ArrayList<LessonStep> lessonSteps = new ArrayList<>();
    private static Lesson lesson = new Lesson();

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
        Stage stage = (Stage) closeWindowButton.getScene().getWindow();
        stage.close();
    }

    @FXML
    void saveLesson(ActionEvent event) {
        if(lessonName.getText().isBlank()) {
            errorLabel.setText("The name is missing!");
            errorLabel.setStyle("-fx-text-fill: red;");
            return;
        }

        if(Lesson.getLessonByName().containsKey(lessonName.getText())) {
            errorLabel.setText("There is already a lessons with the name \"" + lessonName.getText() + "\"!");
            errorLabel.setStyle("-fx-text-fill: red;");
            return;
        }

        if(lesson.getLessonSteps().isEmpty()) {
            errorLabel.setText("There are no lesson steps!");
            errorLabel.setStyle("-fx-text-fill: red;");
            return;
        }

        lesson.setName(lessonName.getText());
        int totalTime = 0;
        for(LessonStep lessonStep : lesson.getLessonSteps().values()) {
            totalTime += lessonStep.getEstimatedTime();
        }
        lesson.setEstimatedTime(totalTime);
        lesson.setComment(commentField.getText());

        Lesson.getLessonByName().putIfAbsent(lesson.getName(), lesson);
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

            //All lessonsteps are numbered from 0 to n
            lessonStep.setIndex(lesson.getLessonSteps().size());
            lesson.getLessonSteps().put(lessonStep.getName(), lessonStep);
            AddLessonStepController.setLessonStep(null);

            Button button = new Button(lessonStep.getName());
            stepListVBox.getChildren().add(button);
            button.setPrefWidth(600);

            button.setOnAction(actionEvent -> {
                LessonStepViewController.setLessonStep(lesson.getLessonStepsByName().get(button.getText()));
                Stage stepStage = new Stage();
                Parent stepRoot = null;
                try {
                    stepRoot = FXMLLoader.load(getClass().getResource("/fxml/LessonStepView.fxml"));
                } catch (IOException e) {
                    e.printStackTrace();
                }

                Scene stepScene = new Scene(stepRoot);
                stepStage.setScene(stepScene);
                stepStage.showAndWait();
            });


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
