package bll;

import dal.ITeacherRepository;
import dal.db.DBTeacherRepository;

public class TeacherManager {
    ITeacherRepository teacherRepository;

    public TeacherManager(){
        teacherRepository = new DBTeacherRepository();
    }

}
