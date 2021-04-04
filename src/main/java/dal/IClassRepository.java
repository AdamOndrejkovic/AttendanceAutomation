package dal;

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

    void assignStudentToClass(int studentID, int classID);
    void assignTeacherToClass(int teacherID, int classID);
    void removeStudentFromClass(int studentID, int classID);
    void removeTeacherFromClass(int teacherID, int classID);
}
