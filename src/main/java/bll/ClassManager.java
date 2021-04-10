package bll;

import be.Class;
import be.Date;
import be.user.Teacher;
import dal.IClassRepository;
import dal.db.DBClassRepository;

import java.util.List;
import java.util.stream.Collectors;

public class ClassManager {
    private IClassRepository classRepository;

    public ClassManager() {
        classRepository = new DBClassRepository();
    }

    public List<Date> getSchedule(int classID) {
        return classRepository.getClassSchedule(classID);
    }

    public List<Class> getAllStudentClasses(int studentID) {
        return classRepository.getAllStudentClasses(studentID);
    }

    public List<Date> getClassSchedule(int classID) {
        return classRepository.getClassSchedule(classID);
    }

    public List<Date> getStudentPresence(int studentID, int classID) {
        return classRepository.getStudentPresence(studentID, classID);
    }

    public List<Date> getStudentAbsence(int studentID, int classID) {
        return classRepository.getStudentAbsence(studentID, classID);
    }

    public void addStudentPresence(int studentID, int classID, Date date){
        classRepository.addStudentPresence(studentID,classID,date);
    }

    public List<String> getClassScheduleAsStringList(int classID){
        return classRepository.getClassSchedule(classID).stream().map(Date::toString).collect(Collectors.toList());
    }
    public List<String> getStudentPresenceAsStringList(int studentID, int classID){
        return classRepository.getStudentPresence(studentID,classID).stream().map(Date::toString).collect(Collectors.toList());
    }

    public List<Class> getAllTeacherClasses(int teacherID){
        return classRepository.getAllTeacherClasses(teacherID);
    }

    public void deleteClassDate(int classID, Date date){
        classRepository.deleteClassDate(classID,date);
    }

    public void addClassDate(int classID, Date date){
        classRepository.addClassDate(classID,date);
    }

    public void editClassDate(int classID, Date oldDate,Date newDate){
        classRepository.editClassDate(classID,oldDate,newDate);
    }

}
