package dal;

import be.user.Student;
import be.user.Teacher;

import java.util.List;

public interface ITeacherRepository {
    Teacher getTeacher();
    List<Student> getAllTeachers();
    void registerTeacher(String firstName, String lastName, String email,String password);
}
