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

    void assignStudentClass(int studentID, int classID);
    void assignTeacherClass(int teacherID, int classID);
    void removeStudentClass(int studentID, int classID);
    void removeTeacherClass(int teacherID, int classID);
}
