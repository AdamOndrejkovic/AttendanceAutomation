package gui.controller;
import be.Class;
import be.user.Student;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ListView;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.text.Text;

import javax.swing.*;
import java.awt.*;
import java.io.FileNotFoundException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class TeacherPageController implements Initializable {

    @FXML
    private TableView<Student> studentListTable;
    TableColumn<Student, String> colName = new TableColumn<>();
    TableColumn<Student, String> colSurName = new TableColumn<>();
    TableColumn<Student, String> colEmail = new TableColumn<>();
/*
    TableColumn<Class, String> colClassesNames = new TableColumn<>("All Classes");
*/
    @FXML
    private ListView<Class> classListTable;
    TableColumn<Class, String> className = new TableColumn<>();

    @FXML
    private Text lblTotalPercentage;
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
        studentListTable.getColumns().addAll(colName, colSurName, colEmail);
        colName.setCellValueFactory(new PropertyValueFactory<>("firstName"));
        colSurName.setCellValueFactory(new PropertyValueFactory<>("lastName"));
        colEmail.setCellValueFactory(new PropertyValueFactory<>("email"));

        Student student = new Student("Faustas","Anulis","faustasanulis@gmail.com",1);
        List<Student> studentList = new ArrayList<>();
        studentList.add(student);
        studentListTable.setItems(FXCollections.observableList(studentList));
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
}
