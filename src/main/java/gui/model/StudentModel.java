package gui.model;

import be.Class;
import be.Date;
import bll.ClassManager;
import gui.controller.Session;
import javafx.collections.ObservableList;

import java.util.ArrayList;
import java.util.List;

public class StudentModel {
    private ClassManager classManager;

    private Session session = Session.getInstance();

    public StudentModel() {
        classManager = new ClassManager();
    }



    public List<Class> getAllStudentClasses() {
        return classManager.getAllStudentClasses(session.getUser().getId());
    }

    public List<Date> getClassSchedule(int classID){
        return classManager.getClassSchedule(classID);
    }

    public List<Date> getStudentPresence(int classID){
        return classManager.getStudentPresence(session.getUser().getId(),classID);
    }

    public void addStudentPresence(int classID, Date date){
        classManager.addStudentPresence(session.getUser().getId(),classID,date);
    }
}
