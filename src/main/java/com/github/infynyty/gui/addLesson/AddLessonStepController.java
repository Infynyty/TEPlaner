package com.github.infynyty.gui.addLesson;

import com.github.infynyty.logic.LessonStep;
import com.github.infynyty.logic.Material;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;
import java.text.ParseException;
import java.util.ArrayList;

public class AddLessonStepController {
    public static boolean addedStage = false;
    private static LessonStep lessonStep;

    @FXML
    private Label errorLabel;

    @FXML
    private CheckBox groupsNeededCheckBox;

    @FXML
    private TextField nameField;

    @FXML
    private TextField timeField;

    @FXML
    private MenuButton materialCheckBox;

    @FXML
    private Button addMaterialsButton;

    @FXML
    private Button addStepButton;

    @FXML
    private Button closeButton;

    @FXML
    void addMaterial(ActionEvent event) {

    }

    @FXML
    void addStep(ActionEvent event) throws IOException {
        //Checks if all fields are filled in correctly
        if(nameField.getText().isBlank()) {
            errorLabel.setText("Name is missing!");
            errorLabel.setStyle("-fx-text-fill: red;");
            return;
        }
        if(timeField.getText().isBlank()) {
            errorLabel.setText("Time is missing!");
            errorLabel.setStyle("-fx-text-fill: red;");
            return;
        }
        int time;
        try {
            time = Integer.parseInt(timeField.getText());
        } catch (NumberFormatException ex) {
            errorLabel.setText("Wrong time format: please use minutes!");
            errorLabel.setStyle("-fx-text-fill: red;");
            return;
        }


        ArrayList<Material> materials = new ArrayList<>();
        //If the materialCheckBox isn't empty all selected fields will be added to an ArrayList
        if(materialCheckBox.getItems() != null) {
            for(MenuItem checkMenuItem : materialCheckBox.getItems()) {
                if(!(checkMenuItem instanceof CheckMenuItem)) continue;
                if(((CheckMenuItem) checkMenuItem).isSelected()) {
                    materials.add(Material.getMaterialByName().get(checkMenuItem.getText()));
                }
            }
        }
        //Lesson Step is created
        lessonStep = new LessonStep();
        lessonStep.setName(nameField.getText());
        lessonStep.setEstimatedTime(time);
        lessonStep.setGroupNeeded(groupsNeededCheckBox.isSelected());
        lessonStep.setMaterials(materials);
        Stage oldStage = (Stage) addStepButton.getScene().getWindow();
        oldStage.close();
        //Window will be reopened again
        addedStage = true;
    }

    @FXML
    void closeWindow(ActionEvent event) {
        //Window won't be reopened again
        Stage stage = (Stage) closeButton.getScene().getWindow();
        stage.close();
    }

    public static LessonStep getLessonStep() {
        return lessonStep;
    }

    public static void setLessonStep(LessonStep lessonStep) {
        AddLessonStepController.lessonStep = lessonStep;
    }
}
