package gui.controller;

import bll.StatisticsManager;
import bll.UserManager;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.chart.PieChart;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.TilePane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;

import java.net.URL;
import java.time.Month;
import java.time.YearMonth;
import java.util.Calendar;
import java.util.Locale;
import java.util.ResourceBundle;

public class StudentPageController implements Initializable {

    private Calendar calendar = Calendar.getInstance(Locale.GERMANY);

    @FXML
    private TilePane tileCalendar;
    @FXML
    private Text textMonth;
    @FXML
    private Text textName;
    @FXML
    private TextField idField;
    @FXML
    private PieChart pieChart;

    private static int loggedUserID = -1;

    private UserManager userManager;
    private StatisticsManager statisticsManager;

    public StudentPageController() {
        userManager = new UserManager();
        statisticsManager = new StatisticsManager();
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        addAttendanceTest();
    }

    private void addAttendanceTest() {
        userManager.addAttendance(5, 10, 2, 2021);
        userManager.addAttendance(5, 2, 1, 2021);
        userManager.addAttendance(5, 16, 2, 2021);
        userManager.addAttendance(5, 8, 2, 2021);
        userManager.addAttendance(5, 4, 2, 2021);
        userManager.addAttendance(5, 1, 2, 2021);
        userManager.addAttendance(5, 15, 2, 2021);

    }

    public void actionMonthClick(ActionEvent e) {
        textMonth.setText(((Button) e.getSource()).getText());

        int selectedMonth = Month.valueOf(((Button) e.getSource()).getText().toUpperCase()).getValue();

        int currentDay = calendar.get(Calendar.DAY_OF_MONTH);
        int currentMonth = calendar.get(Calendar.MONTH) + 1;
        int currentYear = calendar.get(Calendar.YEAR);

        if (!tileCalendar.getChildren().isEmpty()) {
            tileCalendar.getChildren().clear();
        }

        int daysInMonth = YearMonth.of(calendar.get(Calendar.YEAR), selectedMonth).lengthOfMonth();
        for (int i = 1; i < daysInMonth + 1; i++) {
            VBox vbox = new VBox();
            Label label = new Label(String.valueOf(i));

            if (selectedMonth < currentMonth || selectedMonth == currentMonth && i < currentDay ||selectedMonth == currentMonth && i == currentDay && userManager.hasAttended(loggedUserID, i, selectedMonth, currentYear)) {
                if (userManager.hasAttended(loggedUserID, i, selectedMonth, currentYear)) {
                    vbox.setStyle("-fx-background-color: green");
                } else {
                    vbox.setStyle("-fx-background-color: red");
                }
            }else if(i == currentDay && selectedMonth == currentMonth){
                vbox.setStyle("-fx-background-color: blue");
            }

            vbox.setOnMouseClicked(mouseEvent -> {
                if (Integer.parseInt(label.getText()) == currentDay && currentMonth == selectedMonth) {
                    vbox.setStyle("-fx-background-color: green");
                    if(!userManager.hasAttended(loggedUserID, currentDay, currentMonth, currentYear)) {
                        userManager.addAttendance(loggedUserID, currentDay, currentMonth, currentYear);
                        drawPieChart(statisticsManager.getPresentDays(loggedUserID,selectedMonth),statisticsManager.getAbsenceDays(loggedUserID,selectedMonth));
                        Alert.displayAlert("Attendance Submitted","Your attendace: "+currentDay+"/"+currentMonth+"/"+currentYear+" has successfully been submitted!");
                    }
                }
            });

            vbox.getChildren().add(label);
            tileCalendar.getChildren().add(vbox);
            vbox.setAlignment(Pos.CENTER);
        }
        drawPieChart(statisticsManager.getPresentDays(loggedUserID,selectedMonth),statisticsManager.getAbsenceDays(loggedUserID,selectedMonth));
    }

    public int getLoggedUserID() {
        return loggedUserID;
    }

    private void drawPieChart(int presentDays,int absentDays){
        ObservableList<PieChart.Data> pieData = FXCollections.observableArrayList(
          new PieChart.Data("Present",presentDays),
                new PieChart.Data("Absent",absentDays)
        );
        pieChart.setData(pieData);
    }

    public void setLoggedUserID(int loggedUserID) {
        this.loggedUserID = loggedUserID;
        if (loggedUserID != -1) {
            setDisplayName(userManager.getName(loggedUserID));
        } else {
            System.out.println("NOT LOGGED IN");
        }
    }

    public void setDisplayName(String name) {
        textName.setText(name);
    }

}
