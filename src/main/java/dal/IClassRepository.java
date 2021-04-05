package dal;

import be.Date;
import be.user.Student;
import be.user.Teacher;
import be.Class;

import java.util.List;

public interface IClassRepository {
    void createClass(String className);

    Class getClass(int classID);
    List<Class> getAllClasses();
    List<Class> getAllStudentClasses(int studentID);
    List<Class> getAllTeacherClasses(int teacherID);

    void assignStudent(int studentID, int classID);
    void assignTeacher(int teacherID, int classID);
    void removeStudent(int studentID, int classID);
    void removeTeacher(int teacherID, int classID);

    List<Date> getClassSchedule(int classID);
    List<Date> getStudentPresence(int studentID,int classID);
    List<Date> getStudentAbsence(int studentID,int classID);
}
