package gui.controller;

import be.Class;
import be.Date;
import gui.model.StudentModel;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.layout.TilePane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import utility.Months;
import utility.Calendar;

import java.net.URL;

import java.util.*;
import java.util.stream.Collectors;

public class StudentPageController implements Initializable {
    @FXML
    private ChoiceBox<Class> choiceClass;
    @FXML
    private ChoiceBox<Months> choiceMonth;
    @FXML
    private Text txtFullName;
    @FXML
    private Text txtClass;

    private StudentModel studentModel;

    @FXML
    private TilePane tileCalendar;
    @FXML
    private Text textMonth;

    public StudentPageController() {
        studentModel = new StudentModel();
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        choiceClass.setItems(studentModel.getClassOverview());
        txtFullName.setText(Session.getInstance().getUser().getFirstName() + " " + Session.getInstance().getUser().getLastName());
        choiceMonth.setItems(FXCollections.observableList(Arrays.asList(Months.values())));

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
        int selectedMonth = choiceMonth.getValue().getValue();

        List<String> schedule = studentModel.getClassSchedule(classID).stream().map(Date::toString).collect(Collectors.toList());
        List<String> presence = studentModel.getStudentPresence(classID).stream().map(Date::toString).collect(Collectors.toList());

        if (!tileCalendar.getChildren().isEmpty()) {
            tileCalendar.getChildren().clear();
        }
        for (int i = 1; i < Calendar.getDaysInMonth(Calendar.getYear(),selectedMonth); i++) {
            VBox vbox = new VBox();
            Label label = new Label(String.valueOf(i));

            Date loadedDate = new Date(Calendar.getYear(), selectedMonth, i);

            if (schedule.contains(loadedDate.toString())) {
                if ((loadedDate.getMonth() < Calendar.getMonth() || loadedDate.getMonth() == Calendar.getMonth() && loadedDate.getDay() <= Calendar.getDay()) & presence.contains(loadedDate.toString())) {
                    vbox.setStyle("-fx-background-color: green");
                } else if ((loadedDate.getMonth() < Calendar.getMonth() || loadedDate.getMonth() == Calendar.getMonth() && loadedDate.getDay() < Calendar.getDay()) & !presence.contains(loadedDate.toString())) {
                    vbox.setStyle("-fx-background-color: red");
                } else if (loadedDate.getMonth() == Calendar.getMonth() && loadedDate.getDay() == Calendar.getDay()) {
                    vbox.setStyle("-fx-background-color: yellow;-fx-border-color: black;-fx-border-width: 1;");
                } else {
                    vbox.setStyle("-fx-background-color: yellow");
                }
            }

            vbox.setOnMouseClicked(mouseEvent -> {
                Date selectedDate = new Date(Calendar.getYear(), selectedMonth, Integer.parseInt(label.getText()));

                if (schedule.contains(selectedDate.toString()) && !presence.contains(selectedDate.toString())) {
                    if (Calendar.getYear() == selectedDate.getYear() && Calendar.getMonth() == selectedDate.getMonth() && Calendar.getDay() == selectedDate.getDay()) {
                        vbox.setStyle("-fx-background-color: green");
                        studentModel.addStudentPresence(classID,selectedDate);
                    }
                }
            });

            vbox.getChildren().add(new Label(String.valueOf(i)));
            vbox.setAlignment(Pos.CENTER);
            tileCalendar.getChildren().add(vbox);
            textMonth.setText(choiceMonth.getValue().toString());
        }
    }
}
