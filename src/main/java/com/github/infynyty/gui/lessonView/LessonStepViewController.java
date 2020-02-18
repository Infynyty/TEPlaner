package com.github.infynyty.gui.lessonView;

import com.github.infynyty.logic.lessons.Lesson;
import com.github.infynyty.logic.lessons.LessonStep;
import com.github.infynyty.logic.lessons.Material;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.ArrayList;

public class LessonStepViewController {

    private static LessonStep lessonStep;
    private static Lesson lesson;

    @FXML
    private AnchorPane anchorPane;

    @FXML
    private Label nameLabel;

    @FXML
    private Label materialsLabel;

    @FXML
    private Label lengthLabel;

    @FXML
    private Label errorLabel;

    @FXML
    private CheckBox groupsNeededCheckBox;

    @FXML
    private Button editButton;

    @FXML
    private Button closeButton;

    @FXML
    private Button deleteButton;



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


        TextField nameField = new TextField(nameLabel.getText());
        anchorPane.getChildren().add(nameField);
        nameField.relocate(nameLabel.getLayoutX(), nameLabel.getLayoutY());
        nameLabel.setVisible(false);

        TextField lengthField = new TextField(lengthLabel.getText());
        anchorPane.getChildren().add(lengthField);
        lengthField.relocate(lengthLabel.getLayoutX(), lengthLabel.getLayoutY());
        lengthLabel.setVisible(false);

        MenuButton materialCheckBox = new MenuButton();
        materialCheckBox.relocate(materialsLabel.getLayoutX(), materialsLabel.getLayoutY());
        anchorPane.getChildren().add(materialCheckBox);
        materialsLabel.setVisible(false);
        //TODO: Implement materials

        groupsNeededCheckBox.setDisable(false);

        editButton.setText("Save");
        editButton.setOnAction(actionEvent -> {
            if(nameField.getText().isBlank()) {
                errorLabel.setText("Name is missing!");
                errorLabel.setStyle("-fx-text-fill: red;");
                return;
            }
            if(lengthField.getText().isBlank()) {
                errorLabel.setText("Time is missing!");
                errorLabel.setStyle("-fx-text-fill: red;");
                return;
            }
            int time;
            try {
                time = Integer.parseInt(lengthField.getText());
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

            lesson.getLessonSteps().remove(lessonStep.getName());

            lessonStep.setName(nameField.getText());
            lessonStep.setEstimatedTime(Integer.parseInt(lengthField.getText()));
            lessonStep.setMaterials(materials);
            lessonStep.setGroupNeeded(groupsNeededCheckBox.isSelected());

            lesson.getLessonSteps().put(lessonStep.getName(), lessonStep);

            Stage stage = (Stage) editButton.getScene().getWindow();
            stage.close();

            LessonStepViewController.setLessonStep(lessonStep);
            LessonStepViewController.setLesson(lesson);
            Stage stageNew = new Stage();
            Parent root = null;
            try {
                root = FXMLLoader.load(getClass().getResource("/fxml/LessonStepView.fxml"));
            } catch (IOException e) {
                e.printStackTrace();
            }

            Scene scene = new Scene(root);
            stageNew.setScene(scene);
            stageNew.initModality(Modality.APPLICATION_MODAL);
            stageNew.show();
        });

    }

    @FXML
    void delete(ActionEvent event) {
        lesson.getLessonSteps().remove(lessonStep.getName());
        Stage stage = (Stage) deleteButton.getScene().getWindow();
        stage.close();
    }

    public static void setLessonStep(LessonStep lessonStep) {
        LessonStepViewController.lessonStep = lessonStep;
    }

    public static void setLesson(Lesson lesson) {
        LessonStepViewController.lesson = lesson;
    }
}

