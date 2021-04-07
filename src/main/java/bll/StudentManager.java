package bll;

import be.Class;
import dal.IStudentRepository;
import dal.db.DBStudentRepository;

import java.util.List;

public class StudentManager {
    IStudentRepository studentRepository;

    public StudentManager() {
        studentRepository = new DBStudentRepository();
    }
}
