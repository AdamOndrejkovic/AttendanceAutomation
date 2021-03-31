package dal.db;

import be.user.Teacher;
import dal.ITeacherRepository;

import java.util.List;

public class TeacherRepository implements ITeacherRepository {

    DatabaseConnection connection;
    List<Teacher> teacherList;

    public TeacherRepository() {
        connection = new DatabaseConnection();
    }

    // TODO: 3/30/2021 register a new teacher 
}
