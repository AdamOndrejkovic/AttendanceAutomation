package bll;

import dal.IStudentRepository;
import dal.db.DBStudentRepository;

public class StudentManager {
    IStudentRepository studentRepository;

    public StudentManager() {
        studentRepository = new DBStudentRepository();
    }
}
