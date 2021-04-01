package dal;

import be.user.Student;
import be.user.Teacher;

import java.util.List;

public interface ITeacherRepository {
    Teacher getTeacher(int ID);
    List<Teacher> getAllTeachers();
    void registerTeacher(String firstName, String lastName, String email,String password);
}
