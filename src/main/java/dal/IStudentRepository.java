package dal;

import be.user.Student;

import java.util.List;

public interface IStudentRepository {
    Student getStudent();
    List<Student> getAllStudents();
    void registerStudent(String firstName, String lastName, String email,String password);
}
