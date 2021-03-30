package dal.daoDB;

import be.Teacher;
import dal.DataBaseConnection;

import java.util.List;

public class TeacherDao {

    DataBaseConnection connection;
    List<Teacher> teacherList;

    public TeacherDao() {
        connection = new DataBaseConnection();
    }

    // TODO: 3/30/2021 register a new teacher 
}
