package com.github.infynyty.gui.mainview;

import com.github.infynyty.logic.calendar.Calendar;
import com.github.infynyty.logic.calendar.CalendarEvent;
import com.github.infynyty.logic.calendar.CustomEvent;
import com.github.infynyty.logic.calendar.TrainingEvent;
import com.github.infynyty.logic.lessons.Lesson;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;

import java.time.ZoneId;
import java.util.Date;

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
    private Label errorLabel;

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
        if(nameField.getText().isBlank()) {
            errorLabel.setText("Please enter a name!");
            errorLabel.setStyle("-fx-text-fill: red;");
            return;
        }
        if(datePicker.getValue() == null) {
            errorLabel.setText("Please enter a date!");
            errorLabel.setStyle("-fx-text-fill: red;");
            return;
        }
        ZoneId zoneId = ZoneId.systemDefault();
        if(trainingButton.isSelected()) {
            if(lessonPicker.getValue() == null) {
                errorLabel.setText("Please select a lesson!");
                errorLabel.setStyle("-fx-text-fill: red;");
                return;
            }

            TrainingEvent calendarEvent = new TrainingEvent();
            calendarEvent.setDate(Date.from(datePicker.getValue().atStartOfDay(zoneId).toInstant()));
            calendarEvent.setName(nameField.getText());
            calendarEvent.setLesson(Lesson.getLessonByName().get(lessonPicker.getValue()));
            Calendar.getInstance().getCalendarEventByName().put(calendarEvent.getName(), calendarEvent);

        }
        if(customEventButton.isSelected()) {
            CustomEvent calendarEvent = new CustomEvent();
            calendarEvent.setName(nameField.getText());
            calendarEvent.setDate(Date.from(datePicker.getValue().atStartOfDay(zoneId).toInstant()));
            calendarEvent.setDescription(descriptionBox.getText());
            Calendar.getInstance().getCalendarEventByName().put(calendarEvent.getName(), calendarEvent);
        }

        Stage stage = (Stage) saveButton.getScene().getWindow();
        stage.close();
    }

}

