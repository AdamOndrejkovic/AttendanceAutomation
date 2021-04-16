package dal;

import be.Date;
import be.Class;

import java.util.List;

public interface IClassRepository {
    void createClass(String className);

    Class getClass(int classID);

    List<Class> getAllClasses();

    List<Class> getAllStudentClasses(int studentID);

    List<Class> getAllTeacherClasses(int teacherID);

    void assignStudent(int studentID, int classID);

    void removeStudent(int studentID, int classID);

    void addStudentPresence(int studentID, int classID, Date date);

    void assignTeacher(int teacherID, int classID);

    void removeTeacher(int teacherID, int classID);

    void deleteClassDate(int classID, Date date);

    void editClassDate(int classID, Date oldDate, Date newDate);

    void addClassDate(int classID, Date date);


    List<Date> getClassSchedule(int classID);

    List<Date> getStudentPresence(int studentID, int classID);

    List<Date> getStudentAbsence(int studentID, int classID);
}
