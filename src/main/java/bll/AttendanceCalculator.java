package bll;

import dal.IStudentRepository;
import dal.db.DBStudentRepository;

public class AttendanceCalculator {
    private IStudentRepository iStudentRepository = new DBStudentRepository();

    public double calculateAttendance(int id){
        int classId = iStudentRepository.getStudentClass(id);


        return 1;
    }
}
