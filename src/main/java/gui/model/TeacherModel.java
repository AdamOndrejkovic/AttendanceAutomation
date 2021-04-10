package gui.model;

import be.Class;
import be.Date;
import be.user.Student;
import bll.ClassManager;
import bll.StudentManager;
import gui.controller.Session;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.util.ArrayList;

public class TeacherModel {
    private ClassManager classManager;
    private StudentManager studentManager;

    private ObservableList<Student> studentsOverview;
    private ObservableList<Class> classOverview;
    private ObservableList<Date> scheduleOverview;

    private Session session = Session.getInstance();

    public TeacherModel() {
        classManager = new ClassManager();
        studentManager = new StudentManager();

        classOverview = FXCollections.observableList(classManager.getAllTeacherClasses(session.getUser().getId()));
        studentsOverview = FXCollections.observableList(new ArrayList<>());
        scheduleOverview = FXCollections.observableList(new ArrayList<>());
    }

    public void deleteClassDate(int classID, Date date){
        classManager.deleteClassDate(classID,date);
        updateSheduleOverview(classID);
    }

    public void addClassDate(int classID, Date date){
        classManager.addClassDate(classID,date);
        updateSheduleOverview(classID);
    }

    public void editClassDate(int classID, Date oldDate,Date newDate){
        classManager.editClassDate(classID,oldDate,newDate);
        updateSheduleOverview(classID);
    }

    public void updateStudentsOverview(){
        studentsOverview.clear();
        studentsOverview.addAll(FXCollections.observableList(studentManager.getClassStudents(session.getUser().getId())));
    }

    public void updateSheduleOverview(int classID){
        scheduleOverview.clear();
        scheduleOverview.addAll(FXCollections.observableList(classManager.getClassSchedule(classID)));
    }

    public ObservableList<Student> getStudentsOverview() {
        return studentsOverview;
    }

    public ObservableList<Class> getClassOverview() {
        return classOverview;
    }

    public ObservableList<Date> getSheduleOverview(){
        return scheduleOverview;
    }
}
