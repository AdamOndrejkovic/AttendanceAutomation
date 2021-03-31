package dal.db;

import be.user.Student;
import dal.IStudentRepository;

import java.util.List;

public class DBStudentRepository implements IStudentRepository {
    private DatabaseConnection connection;

    public DBStudentRepository() {
        connection = new DatabaseConnection();
    }

    @Override
    public Student getStudent() {
        return null;
    }

    @Override
    public List<Student> getAllStudents() {
        return null;
    }

    @Override
    public void registerStudent(String firstName, String lastName, String email, String password) {

    }
}
