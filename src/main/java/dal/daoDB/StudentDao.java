package dal.daoDB;

import be.Student;
import dal.DataBaseConnection;

import java.util.List;

public class StudentDao {

    DataBaseConnection connection;
    List<Student> studentList;

    public StudentDao() {
        connection = new DataBaseConnection();
    }

    // TODO: 3/30/2021 register new student
    // TODO: 3/30/2021 return all students
}
