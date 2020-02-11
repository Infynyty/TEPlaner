package com.github.infynyty.gui.lessonView;

import com.github.infynyty.logic.Lesson;
import com.github.infynyty.logic.LessonStep;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextArea;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.io.IOException;

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
    public void initialize() {
        nameLabel.setText(lesson.getName());
        lengthLabel.setText(lesson.getEstimatedTime() + "");
        commentTextBox.setDisable(true);
        commentTextBox.setText(lesson.getComment());
        for(LessonStep lessonStep : lesson.getLessonSteps().values()) {
            Button button = new Button(lessonStep.getName());
            stepsListVBox.getChildren().add(button);
            button.setPrefWidth(600);

            button.setOnAction(actionEvent -> {
                LessonStepViewController.setLessonStep(lesson.getLessonStepsByName().get(button.getText()));
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

    public static void setLesson(Lesson lesson) {
        LessonViewController.lesson = lesson;
    }
}

