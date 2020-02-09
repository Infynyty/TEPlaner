package com.github.infynyty.gui.mainview;

import com.github.infynyty.gui.addLesson.AddLessonController;
import com.github.infynyty.logic.AllLessons;
import com.github.infynyty.logic.Lesson;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;

public class MainViewController {

    @FXML
    private Button addLessonButton;

    @FXML
    private VBox calendar;

    @FXML
    private AnchorPane scrollAnchorPane;

    @FXML
    public void initialize() {
        for(Lesson lesson : AllLessons.getInstance().getAllLessonsList()) {
            Button button = new Button("Name: " + lesson.getName() + " // Length: " + lesson.getEstimatedTime());
            System.out.println(calendar.getWidth());
            button.setPrefWidth(1200);
            button.setPrefHeight(50);
            calendar.getChildren().add(button);
        }
    }

    @FXML
    private void addLesson(ActionEvent event) throws IOException {
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

            AddLessonController.setLesson(null);
        }
    }
}
