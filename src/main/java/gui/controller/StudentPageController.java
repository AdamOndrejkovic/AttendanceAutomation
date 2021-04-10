package gui.controller;

import be.Class;
import be.Date;
import bll.ClassManager;
import gui.model.StudentModel;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.layout.TilePane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import utility.Months;

import java.net.URL;

import java.time.Month;
import java.time.YearMonth;
import java.util.*;
import java.util.stream.Collectors;

public class StudentPageController implements Initializable {
    @FXML
    private ChoiceBox<Class> choiceClass;
    @FXML
    private ChoiceBox<String> choiceMonth;
    @FXML
    private Text txtFullName;
    @FXML
    private Text txtClass;

    private StudentModel studentModel;

    private Calendar calendar = Calendar.getInstance(Locale.GERMANY);

    @FXML
    private TilePane tileCalendar;
    @FXML
    private Text textMonth;

    public StudentPageController() {
        studentModel = new StudentModel();
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        choiceClass.setItems(FXCollections.observableList(studentModel.getAllStudentClasses()));
        txtFullName.setText(Session.getInstance().getUser().getFirstName() + " " + Session.getInstance().getUser().getLastName());
        choiceMonth.setItems(FXCollections.observableList(Months.getValues()));

        choiceClass.setOnAction(actionEvent -> {
            txtClass.setText(choiceClass.getValue().getName());
            if (choiceMonth.getValue() != null) {
                loadCalendar();
            }
        });
        choiceMonth.setOnAction(actionEvent -> {
            if (choiceClass.getValue() != null) {
                loadCalendar();
            }
        });
    }

    private void loadCalendar() {
        int classID = choiceClass.getValue().getId();

        List<String> schedule = studentModel.getClassSchedule(classID).stream().map(Date::toString).collect(Collectors.toList());
        List<String> presence = studentModel.getStudentPresence(classID).stream().map(Date::toString).collect(Collectors.toList());

        Date currentDate = new Date(calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH) + 1, calendar.get(Calendar.DAY_OF_MONTH));

        int selectedMonthDays = YearMonth.of(calendar.get(Calendar.YEAR), Month.valueOf(choiceMonth.getValue().toUpperCase()).getValue()).lengthOfMonth() + 1;

        if (!tileCalendar.getChildren().isEmpty()) {
            tileCalendar.getChildren().clear();
        }
        for (int i = 1; i < selectedMonthDays; i++) {
            VBox vbox = new VBox();
            Label label = new Label(String.valueOf(i));

            Date loadedDate = new Date(calendar.get(Calendar.YEAR), Month.valueOf(choiceMonth.getValue().toUpperCase()).getValue(), i);

            if (schedule.contains(loadedDate.toString())) {
                if ((loadedDate.getMonth() < currentDate.getMonth() || loadedDate.getMonth() == currentDate.getMonth() && loadedDate.getDay() <= currentDate.getDay()) & presence.contains(loadedDate.toString())) {
                    vbox.setStyle("-fx-background-color: green");
                } else if ((loadedDate.getMonth() < currentDate.getMonth() || loadedDate.getMonth() == currentDate.getMonth() && loadedDate.getDay() < currentDate.getDay()) & !presence.contains(loadedDate.toString())) {
                    vbox.setStyle("-fx-background-color: red");
                } else if (loadedDate.getMonth() == currentDate.getMonth() && loadedDate.getDay() == currentDate.getDay()) {
                    vbox.setStyle("-fx-background-color: yellow;-fx-border-color: black;-fx-border-width: 1;");
                } else {
                    vbox.setStyle("-fx-background-color: yellow");
                }
            }

            vbox.setOnMouseClicked(mouseEvent -> {
                Date selectedDate = new Date(calendar.get(Calendar.YEAR), Month.valueOf(choiceMonth.getValue().toUpperCase()).getValue(), Integer.parseInt(label.getText()));

                if (schedule.contains(selectedDate.toString()) && !presence.contains(selectedDate.toString())) {
                    if (currentDate.getYear() == selectedDate.getYear() && currentDate.getMonth() == selectedDate.getMonth() && currentDate.getDay() == selectedDate.getDay()) {
                        vbox.setStyle("-fx-background-color: green");
                        studentModel.addStudentPresence(classID,selectedDate);
                    }
                }
            });

            vbox.getChildren().add(new Label(String.valueOf(i)));
            vbox.setAlignment(Pos.CENTER);
            tileCalendar.getChildren().add(vbox);
            textMonth.setText(choiceMonth.getValue());
        }
    }
}
