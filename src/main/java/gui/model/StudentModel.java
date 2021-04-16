package gui.model;

import be.Class;
import be.Date;
import bll.ClassManager;
import gui.controller.Session;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class StudentModel {

    private ClassManager classManager;

    private ObservableList<Class> classOverview;

    private Session session = Session.getInstance();

    public StudentModel() {
        classManager = new ClassManager();
        classOverview = FXCollections.observableList(classManager.getAllStudentClasses(session.getUser().getId()));
    }

    public List<Date> getClassSchedule(int classID) {
        return classManager.getClassSchedule(classID);
    }

    public List<Date> getStudentPresence(int classID) {
        return classManager.getStudentPresence(session.getUser().getId(), classID);
    }
    public List<Date> getStudentAbsence(int classID) {
        return classManager.getStudentAbsence(session.getUser().getId(), classID);
    }

    public List<Date> getStudentPresence(int classID, int year, int month) {
        return classManager.getStudentPresence(session.getUser().getId(), classID,year,month);
    }

    public List<Date> getStudentAbsence(int classID, int year, int month) {
        return classManager.getStudentAbsence(session.getUser().getId(), classID,year,month);
    }

    public void addStudentPresence(int classID, Date date) {
        classManager.addStudentPresence(session.getUser().getId(), classID, date);
    }

    public ObservableList<Class> getClassOverview() {
        return classOverview;
    }
}
