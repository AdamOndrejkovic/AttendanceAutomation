package gui.model;

import be.StudentClasses;
import be.Student;
import bll.Mock_data;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.io.FileNotFoundException;
import java.util.List;

public class AttendanceModel {
    private ObservableList<Student> studentList;
    private ObservableList<StudentClasses> allStudentClasses;
    private ObservableList<Student> thisList;

    private Mock_data mock_data;

    public AttendanceModel() throws FileNotFoundException {
        mock_data = new Mock_data();
        studentList = FXCollections.observableArrayList(mock_data.getStudentList());
        allStudentClasses = FXCollections.observableArrayList(mock_data.getClassList());
    }

    public ObservableList<Student> getStudentsList() {
        updateClassesList();
        return studentList;
    }

    public ObservableList<StudentClasses> getClassList() {
        updateStudentsList();
        return allStudentClasses;
    }

    /*
    -----------------------------------------------------------------------------------
    Manually Add/remove student methods
    Update students list method
    -----------------------------------------------------------------------------------
     */

    public void createStudent(String name, String surName, String eMail) {
        mock_data.addStudent(name,surName,eMail,"International Class 2021_EASV");
        updateStudentsList();
    }

    public void deleteStudent(int selectedItems){
        mock_data.deleteStudent(selectedItems);
        updateStudentsList();
    }

    public void updateStudentsList() {
        studentList.clear();
        studentList.addAll(mock_data.getStudentList());
    }

    /*
    -----------------------------------------------------------------------------------
    Manually Add/remove class methods
    Update classes list method
    -----------------------------------------------------------------------------------
     */

    public void createClass(String className) {
        mock_data.createClass(className);
        updateClassesList();
    }

    public void deleteClass(int selectedMenuItem) {
        mock_data.deleteClass(selectedMenuItem);
        updateClassesList();
    }

    public void updateClassesList(){
        allStudentClasses.clear();
        allStudentClasses.addAll(mock_data.getClassList());
    }
}
