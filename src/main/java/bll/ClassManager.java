package bll;

import be.Date;
import be.user.Teacher;
import dal.IClassRepository;
import dal.db.DBClassRepository;

import java.util.List;

public class ClassManager {
    private IClassRepository classRepository;

    public ClassManager(){
        classRepository = new DBClassRepository();
    }

    public List<Date> getSchedule(int classID){
        return classRepository.getClassSchedule(classID);
    }


}
