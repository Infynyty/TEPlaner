package com.github.infynyty.gui.mainview;

import com.github.infynyty.logic.lessons.Lesson;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.stage.Stage;

public class AddEventController {

    @FXML
    private RadioButton trainingButton;

    @FXML
    private RadioButton customEventButton;

    @FXML
    private TextArea descriptionBox;

    @FXML
    private TextField nameField;

    @FXML
    private Button cancelButton;

    @FXML
    private Button saveButton;

    @FXML
    private DatePicker datePicker;

    @FXML
    private ChoiceBox<String> lessonPicker = new ChoiceBox<>();

    @FXML
    public void initialize() {
        descriptionBox.setDisable(true);
        final ToggleGroup group = new ToggleGroup();

        customEventButton.setToggleGroup(group);
        trainingButton.setToggleGroup(group);
        trainingButton.setSelected(true);

        lessonPicker.setItems(FXCollections.observableArrayList(Lesson.getLessonByName().keySet()));
        lessonPicker.setValue(lessonPicker.getItems().get(0));


        group.selectedToggleProperty().addListener((ov, old_toggle, new_toggle) -> {
            if(group.getSelectedToggle().equals(trainingButton)) {
                descriptionBox.setDisable(true);
                lessonPicker.setDisable(false);
            }
            if(group.getSelectedToggle().equals(customEventButton)) {
                lessonPicker.setDisable(true);
                descriptionBox.setDisable(false);
            }
        });
    }

    @FXML
    void cancel(ActionEvent event) {
        Stage stage = (Stage) cancelButton.getScene().getWindow();
        stage.close();
    }

    @FXML
    void save(ActionEvent event) {

    }

}

