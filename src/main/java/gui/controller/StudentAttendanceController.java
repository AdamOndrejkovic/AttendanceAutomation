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
import utility.Months;

import java.net.URL;
import java.util.*;
import java.util.stream.Collectors;

public class StudentAttendanceController implements Initializable {

    private AttendanceCalculator attendanceCalculator;

    private ObservableList<PieChart.Data> pieChartDataList;
    private int absence;
    private int presence;
    private int studentId;
    private int classId;

    @FXML
    PieChart pieChart;

    @FXML
    LineChart<String,Number> lineChart;

    @FXML
    Label title;

    @FXML
    JFXComboBox yearPicker;

    @FXML
    JFXComboBox monthPicker;

    @FXML
    Label totalAbsence;

    @FXML
    Label totalPresence;

    @FXML
    Label mostAbsentDay;

    @FXML
    JFXButton close;


    public StudentAttendanceController() {
        attendanceCalculator = new AttendanceCalculator();
    }

    private ObservableList<String> months = FXCollections.observableArrayList("January", "February", "March", "April", "May", "June", "July", "August", "September", "October", "November", "December");
    private ObservableList<Integer> years = FXCollections.observableArrayList(Calendar.getInstance().get(Calendar.YEAR), Calendar.getInstance().get(Calendar.YEAR) - 1, Calendar.getInstance().get(Calendar.YEAR) - 2);

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        yearPicker.setValue(years.get(0));
        monthPicker.setItems(months);
        yearPicker.setItems(years);

        monthPicker.getSelectionModel().selectedItemProperty().addListener( (observableValue, o, t1) -> {
            lineChart.getData().clear();
            int selectedYear = (int) yearPicker.getSelectionModel().selectedItemProperty().getValue();
            if (t1 != null){
                // Slow region new Thread
                Thread t = new Thread(()->{
                    List<Date> dateListPresent = attendanceCalculator.getPresenceList(studentId, classId).stream().filter(date -> date.getMonth() == months.indexOf(t1) + 1 && date.getYear() == selectedYear).collect(Collectors.toList());
                    List<Date> dateListAbsence = attendanceCalculator.getAbsenceList(studentId, classId).stream().filter(date -> date.getMonth() == months.indexOf(t1) + 1 && date.getYear() == selectedYear).collect(Collectors.toList());
                    // GUI update
                    Platform.runLater(()->{
                        System.out.println(months.indexOf(t1));
                        absence = dateListAbsence.size();
                        presence = dateListPresent.size();


                        if(absence != 0 || presence != 0) {
                            pieChartDataList = FXCollections.observableArrayList(
                                    new PieChart.Data("Absence", absence),
                                    new PieChart.Data("Presence", presence)
                            );
                            pieChart.setData(pieChartDataList);
                            totalAbsence.setText(String.valueOf(absence));
                            totalPresence.setText(String.valueOf(presence));

                            XYChart.Series <String, Number> absenceSeries = new XYChart.Series<>();
                            absenceSeries.setName("Absence");
                            XYChart.Series <String, Number> presenceSeries = new XYChart.Series<>();
                            presenceSeries.setName("Presence");
                            for (Date date : dateListAbsence) {
                                absenceSeries.getData().add(new XYChart.Data<>(String.valueOf(date.getDay()),2));
                                //dataSeries.getData().add(new XYChart.Data(date.dayToString(date.getDay()), 0));
                            }
                            for (Date date : dateListPresent) {
                                presenceSeries.getData().add(new XYChart.Data<>(String.valueOf(date.getDay()),3));
                                //dataSeries.getData().add(new XYChart.Data(date.dayToString(date.getDay()), 10));
                            }
                            lineChart.getData().add(absenceSeries);
                            lineChart.getData().add(presenceSeries);
                        }
                    });
                });
                t.start();

            }
        });


        yearPicker.getSelectionModel().selectedItemProperty().addListener( (observableValue, o, t1) -> {
            if (t1 != null){
                List<Date> dateListPresent = attendanceCalculator.getPresenceList(studentId, classId).stream().filter(date ->  date.getYear() == (int) t1).collect(Collectors.toList());
                List<Date> dateListAbsence = attendanceCalculator.getAbsenceList(studentId, classId).stream().filter(date -> date.getMonth() == (int) t1).collect(Collectors.toList());

                pieChartDataList = FXCollections.observableArrayList(
                        new PieChart.Data("Absence" , absence),
                        new PieChart.Data("Presence" , presence)
                );
                pieChart.setData(pieChartDataList);
                totalAbsence.setText(String.valueOf(absence));
                totalPresence.setText(String.valueOf(presence));

            }
        });


           // System.out.println(attendanceCalculator.getAbsence(studentId,classId));
           // System.out.println(attendanceCalculator.getPresence(studentId,classId));





            System.out.println(getClassId());
            System.out.println(getStudentId());
            System.out.println(presence);
            System.out.println(absence);

            monthPicker.setItems(months);
            yearPicker.setItems(years);
    }

    public void setStudentId(int id){
        this.studentId = id;
    }

    public void setClassId(int classId){
        this.classId = classId;
    }

    public int getStudentId() {
        return studentId;
    }

    public int getClassId() {
        return classId;
    }

    public void closeWindow(ActionEvent actionEvent) {
        System.out.println(getClassId());
        System.out.println(getStudentId());
       /* Stage stage = (Stage) close.getScene().getWindow();
        stage.close();*/
    }
}
