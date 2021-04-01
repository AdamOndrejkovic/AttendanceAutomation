package gui.controller;

import be.user.Student;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.text.Text;

import javax.swing.*;
import java.awt.*;
import java.io.FileNotFoundException;
import java.net.URL;
import java.util.ResourceBundle;

public class TeacherPageController implements Initializable {

    @FXML
    private TableView<Student> studentListTable;
    TableColumn<Student, String> colName = new TableColumn<>("Name");
    TableColumn<Student, String> colSurName = new TableColumn<>("Surname");
    TableColumn<Student, String> colEmail = new TableColumn<>("E-mail");
/*
    @FXML
    private TableView<StudentClasses> classesList;
    TableColumn<StudentClasses, String> colClassesNames = new TableColumn<>("All Classes");
*/
    @FXML
    private Text lblTotalPercentage;
    @FXML
    private Text lblMissedClassCount;
    @FXML
    private Text lblNotAttendedAtAll;

    static String newName, newSurName, newEMail; //For creating new student in popup window

    public TeacherPageController() throws FileNotFoundException {
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
            setAllListView();
    }

        /*
    -----------------------------------------------------------------------------------
    Everything related to lists in tables
    -----------------------------------------------------------------------------------
     */

    private void setAllListView() {
        setAllStudentList();
        setAllClasses();
    }

    private void setAllStudentList() {
        studentListTable.getColumns().addAll(colName, colSurName, colEmail);
        colName.setCellValueFactory(new PropertyValueFactory<>("Name"));
        colSurName.setCellValueFactory(new PropertyValueFactory<>("surname"));
        colEmail.setCellValueFactory(new PropertyValueFactory<>("email"));
    }

    private void setAllClasses(){
    }

    /*
    -----------------------------------------------------------------------------------
    Manually add/edit/remove student methods
    -----------------------------------------------------------------------------------
     */

    @FXML
    private void btnCreateNewStudent() {
        CreateNewStudent createNewStudent = new CreateNewStudent();
        createNewStudent.openNewWindow();
    }

    @FXML
    private void btnEditStudentsInfo() {
    }

    @FXML
    private void btnDeleteStudent(){
    }

     /*
    -----------------------------------------------------------------------------------
    Manually add/edit/remove class methods
    -----------------------------------------------------------------------------------
     */

    @FXML
    private void btnCreateNewClass() {
        String className = JOptionPane.showInputDialog(null, "Type class name:", "Create Class", JOptionPane.PLAIN_MESSAGE);
    }

    @FXML
    private void btnEditClass() {
    }

    @FXML
    private void btnDeleteClass() {
    }

    /*
    -----------------------------------------------------------------------------------
    Alert sound if something is wrong
    -----------------------------------------------------------------------------------
     */

    private void alertSound() {
        Toolkit.getDefaultToolkit().beep();
    }
}