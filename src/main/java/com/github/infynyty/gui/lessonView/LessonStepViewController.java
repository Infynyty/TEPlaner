package com.github.infynyty.gui.lessonView;

import com.github.infynyty.logic.Lesson;
import com.github.infynyty.logic.LessonStep;
import com.github.infynyty.logic.Material;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.stage.Stage;

public class LessonStepViewController {

    private static LessonStep lessonStep;

    @FXML
    private Label nameLabel;

    @FXML
    private Label materialsLabel;

    @FXML
    private Label lengthLabel;

    @FXML
    private CheckBox groupsNeededCheckBox;

    @FXML
    private Button editButton;

    @FXML
    private Button closeButton;


    @FXML
    public void initialize() {
        nameLabel.setText(lessonStep.getName());
        boolean first = true;
        for(Material m : lessonStep.getMaterials()) {
            if(!first) {
                nameLabel.setText(nameLabel.getText().concat(", " + m.getName() + "(" + m.getAmount() + ")"));
            } else {
                first = false;
                nameLabel.setText(m.getName() + "(" + m.getAmount() + ")");
            }
        }
        System.out.println("All materials added to List!");
        lengthLabel.setText(lessonStep.getEstimatedTime() + "");
        groupsNeededCheckBox.setSelected(lessonStep.isGroupNeeded());

        groupsNeededCheckBox.setDisable(true);
    }


    @FXML
    void close(ActionEvent event) {
        Stage stage = (Stage) closeButton.getScene().getWindow();
        stage.close();
    }

    @FXML
    void edit(ActionEvent event) {
        //TODO: Implement
    }

    public static void setLessonStep(LessonStep lessonStep) {
        LessonStepViewController.lessonStep = lessonStep;
    }
}

