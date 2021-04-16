package gui.controller;

import be.Class;
import be.Date;
import gui.model.StudentModel;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.chart.PieChart;
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
    @FXML
    private PieChart pieChart;
    @FXML
    private TilePane tileCalendar;
    @FXML
    private Text textMonth;

    private StudentModel studentModel;

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
        new Thread(() -> {

            textMonth.setText(choiceMonth.getValue().toString());

            int classID = choiceClass.getValue().getId();
            int loadedMonth = choiceMonth.getValue().getValue();
            Date currentDate = new Date(Calendar.getYear(), Calendar.getMonth(), Calendar.getDay());
            drawAbsencePieChart(classID, currentDate.getYear(), loadedMonth);

            List<String> schedule = studentModel.getClassSchedule(classID).stream().map(Date::toString).collect(Collectors.toList());
            List<String> presence = studentModel.getStudentPresence(classID).stream().map(Date::toString).collect(Collectors.toList());

            if (!tileCalendar.getChildren().isEmpty()) {
                Platform.runLater(() -> {
                    tileCalendar.getChildren().clear();
                });
            }
            for (int i = 1; i < Calendar.getDaysInMonth(currentDate.getYear(), loadedMonth); i++) {
                VBox vbox = new VBox();
                Label label = new Label(String.valueOf(i));
                Date loadedDate = new Date(currentDate.getYear(), loadedMonth, i);

                if (schedule.contains(loadedDate.toString())) {
                    if ((loadedDate.getMonth() <= currentDate.getMonth() && loadedDate.getDay() <= currentDate.getDay()) & presence.contains(loadedDate.toString())) {
                        vbox.setStyle("-fx-background-color: green");
                    } else if ((loadedDate.getMonth() <= currentDate.getMonth() && loadedDate.getDay() < currentDate.getDay()) & !presence.contains(loadedDate.toString())) {
                        vbox.setStyle("-fx-background-color: red");
                    } else if (loadedDate.getMonth() == currentDate.getMonth() && loadedDate.getDay() == currentDate.getDay()) {
                        vbox.setStyle("-fx-background-color: yellow;-fx-border-color: black;-fx-border-width: 1;");
                    } else {
                        vbox.setStyle("-fx-background-color: yellow");
                    }
                }
                vbox.getChildren().add(label);
                vbox.setAlignment(Pos.CENTER);

                vbox.setOnMouseClicked(mouseEvent -> {
                    Date selectedDate = new Date(Calendar.getYear(), loadedMonth, Integer.parseInt(label.getText()));

                    if (schedule.contains(selectedDate.toString()) && !presence.contains(selectedDate.toString())) {
                        if (Calendar.getYear() == selectedDate.getYear() && Calendar.getMonth() == selectedDate.getMonth() && Calendar.getDay() == selectedDate.getDay()) {
                            vbox.setStyle("-fx-background-color: green");
                            studentModel.addStudentPresence(classID, selectedDate);
                            drawAbsencePieChart(classID, loadedDate.getYear(), loadedDate.getMonth());
                        }
                    }
                });

                Platform.runLater(() -> {
                    tileCalendar.getChildren().add(vbox);
                });
            }
        }).start();
    }

    public void drawAbsencePieChart(int classID, int year, int month) {
        int presentDays = studentModel.getStudentPresence(classID, year, month).size();
        int absentDays = studentModel.getStudentAbsence(classID, year, month).size();
        String preText = "Your absence is: ";
        PieChart.Data present = new PieChart.Data("Present", presentDays);
        PieChart.Data absent = new PieChart.Data("Absent", absentDays);

        int totalDays = presentDays + absentDays;
        Platform.runLater(() -> {
            if (totalDays <= 0) {
                if (pieChart.isVisible()) {
                    pieChart.setVisible(false);
                }
            } else {
                if (!pieChart.isVisible()) {
                    pieChart.setVisible(true);
                }
                pieChart.setData(FXCollections.observableList(FXCollections.observableArrayList(
                        present, absent
                )));

                if (absentDays > 0) {
                    int absencePercentage = (int) (((float) absentDays / (float) totalDays) * 100);
                    pieChart.setTitle(preText + absencePercentage +"%");
                } else {
                    pieChart.setTitle(preText + "0%");
                }
            }
        });
    }
}
