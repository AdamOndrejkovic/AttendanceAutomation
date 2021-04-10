package gui.controller;
import be.Class;
import be.Date;
import be.user.Student;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.DatePicker;
import javafx.scene.control.ListView;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.text.Text;

import javax.swing.*;
import java.awt.*;
import java.io.FileNotFoundException;
import java.net.URL;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class TeacherPageController implements Initializable {
    @FXML
    private ListView<Class> classListTable;

    @FXML
    private Text lblTotalPercentage;
    @FXML
    private DatePicker datePicker;
    @FXML
    private Text lblMissedClassCount;
    @FXML
    private Text lblNotAttendedAtAll;

    static String newName, newSurName, newEMail; //For creating new student in popup window

    public TeacherPageController() {
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        setAllStudentList();
        setAllClasses();
    }

    private void setAllStudentList() {
    }

    private void setAllClasses(){

    }

    @FXML
    private void btnEditStudentsInfo() {
    }

    @FXML
    private void btnDeleteStudent(){
    }

    @FXML
    private void btnEditClass() {
    }

    @FXML
    private void btnDeleteClass() {
    }

    public void submitDate(ActionEvent actionEvent) {
        int year = datePicker.getValue().getYear();
        int month = datePicker.getValue().getMonthValue();
        int day = datePicker.getValue().getDayOfMonth();
        Date date = new Date(year,month,day);
    }
}
