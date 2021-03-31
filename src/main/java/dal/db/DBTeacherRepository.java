package dal.db;

import be.user.Student;
import be.user.Teacher;
import dal.ITeacherRepository;

import java.util.List;

public class DBTeacherRepository implements ITeacherRepository {
    private DatabaseConnection connection;

    public DBTeacherRepository() {
        connection = new DatabaseConnection();
    }

    @Override
    public Teacher getTeacher() {
        return null;
    }

    @Override
    public List<Student> getAllTeachers() {
        return null;
    }

    @Override
    public void registerTeacher(String firstName, String lastName, String email, String password) {

    }
}
