package dal;

import be.user.Student;

import java.util.List;

public interface IStudentRepository {
    Student getStudent(int ID);
    List<Student> getAllStudents();
    List<Student> getClassStudents(int classID);
    boolean registerStudent(String firstName, String lastName, String email, String password);
    int getStudentClass(int id);
}
