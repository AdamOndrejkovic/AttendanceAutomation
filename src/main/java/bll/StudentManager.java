package bll;

import be.Class;
import be.user.Student;
import dal.IStudentRepository;
import dal.db.DBStudentRepository;

import java.util.List;

public class StudentManager {
    IStudentRepository studentRepository;

    public StudentManager() {
        studentRepository = new DBStudentRepository();
    }


    public List<Student> getClassStudents(int classID) {
        return studentRepository.getClassStudents(classID);
    }
}
