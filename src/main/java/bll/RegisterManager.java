package bll;

import dal.IStudentRepository;
import dal.ITeacherRepository;
import dal.db.DBStudentRepository;
import dal.db.DBTeacherRepository;

public class RegisterManager {

    private IStudentRepository iStudentRepository;
    private ITeacherRepository iTeacherRepository;

    public RegisterManager(){
        iStudentRepository = new DBStudentRepository();
        iTeacherRepository = new DBTeacherRepository();
    }
    public boolean registerStudent(String fName, String lName, String email, String password) {
        return iStudentRepository.registerStudent(fName, lName, email, password);
    }

    public boolean registerTeacher(String fName, String lName, String email, String password) {
        return iTeacherRepository.registerTeacher(fName, lName, email, password);
    }
}
