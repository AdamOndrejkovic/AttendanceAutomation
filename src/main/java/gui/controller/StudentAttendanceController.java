package gui.controller;

import be.Date;
import bll.AttendanceCalculator;
import bll.ClassManager;
import bll.StudentManager;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.SortedList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.chart.*;
import javafx.scene.control.Label;
import javafx.stage.Stage;
import utility.Calendar;
import utility.Months;

import java.net.URL;
import java.time.DayOfWeek;
import java.util.*;
import java.util.function.BinaryOperator;
import java.util.stream.Collectors;

public class StudentAttendanceController implements Initializable {

    private AttendanceCalculator attendanceCalculator;
    private ClassManager classManager;

    private ObservableList<PieChart.Data> pieChartDataList;
    private int studentId;
    private int classId;

    @FXML
    PieChart pieChart;

    @FXML
    LineChart<String, Number> lineChart;

    @FXML
    Label title;

    @FXML
    JFXComboBox<Integer> yearPicker;

    @FXML
    JFXComboBox<Months> monthPicker;

    @FXML
    Label totalAbsence;

    @FXML
    Label totalPresence;

    @FXML
    Label mostAbsentDay;

    public StudentAttendanceController() {
        attendanceCalculator = new AttendanceCalculator();
        classManager = new ClassManager();
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        monthPicker.setItems(FXCollections.observableList(Arrays.asList(Months.values())));
        yearPicker.setItems(FXCollections.observableArrayList(Calendar.getYear(), Calendar.getYear() - 1, Calendar.getYear() - 2));
        yearPicker.setValue(yearPicker.getItems().get(0));


        monthPicker.getSelectionModel().selectedItemProperty().addListener((observableValue, o, t1) -> {
            if (!yearPicker.getSelectionModel().isEmpty()) {
                loadContent(t1.getValue(),yearPicker.getValue());
            }
        });


        yearPicker.getSelectionModel().selectedItemProperty().addListener((observableValue, o, t1) -> {
            if(!monthPicker.getSelectionModel().isEmpty()) {
                loadContent(monthPicker.getValue().getValue(), t1);
            }
        });
    }

    private String getMostAbsentDay(int year, int month) {
        List<Integer> absenceDates = classManager.getStudentAbsence(studentId, classId, year, month).stream().map(Date::getDayOfTheWeek).collect(Collectors.toList());
        Optional<Integer> maxOccurredElement = absenceDates.stream()
                .reduce(BinaryOperator.maxBy(Comparator.comparingInt(o -> Collections.frequency(absenceDates, o))));

        return maxOccurredElement.map(integer -> DayOfWeek.of(integer).name()).orElse("No Data");
    }

    public void loadContent(int month, int year) {
        new Thread(() -> {
            List<Date> presenceDates = classManager.getStudentPresence(studentId, classId, year, month);
            List<Date> absenceDates = classManager.getStudentAbsence(studentId, classId, year, month);
            List<Date> scheduleDates = classManager.getClassSchedule(classId);
            // GUI update
            Platform.runLater(() -> {
                lineChart.getData().clear();

                pieChartDataList = FXCollections.observableArrayList(
                        new PieChart.Data("Absence", absenceDates.size()),
                        new PieChart.Data("Presence", presenceDates.size())
                );

                pieChart.setData(pieChartDataList);

                XYChart.Series<String, Number> presenceSeries = new XYChart.Series<>();
                presenceSeries.setName("Presence");

                for (Date date : scheduleDates) {
                    if (presenceDates.stream().map(Date::toString).anyMatch(s -> s.equals(date.toString()))) {
                        presenceSeries.getData().add(new XYChart.Data<>(String.valueOf(date.getDay()), 2));
                    } else if (absenceDates.stream().map(Date::toString).anyMatch(s -> s.equals(date.toString()))) {
                        presenceSeries.getData().add(new XYChart.Data<>(String.valueOf(date.getDay()), 1));
                    }
                }

                lineChart.getData().add(presenceSeries);
                totalPresence.setText("Total Presence: " + presenceDates.size());
                totalAbsence.setText("Total Absence: " + absenceDates.size());
                mostAbsentDay.setText("Most Absent Day: " + getMostAbsentDay(year, month));
            });
        }).start();
    }

    public void setStudentId(int id) {
        this.studentId = id;
    }

    public void setClassId(int classId) {
        this.classId = classId;
    }

    public int getClassId() {
        return classId;
    }
}
