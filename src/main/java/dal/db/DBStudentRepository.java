package dal.db;

import be.user.Student;
import dal.IStudentRepository;

import java.util.List;

public class DBStudentRepository implements IStudentRepository {

    DatabaseConnection connection;
    List<Student> studentList;

    public DBStudentRepository() {
        connection = new DatabaseConnection();
    }

    // TODO: 3/30/2021 register new student
    // TODO: 3/30/2021 return all students
}
