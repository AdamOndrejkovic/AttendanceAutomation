package bll;

import dal.IClassRepository;
import dal.IStudentRepository;
import dal.db.DBClassRepository;
import dal.db.DBStudentRepository;

public class AttendanceCalculator {
    private IStudentRepository iStudentRepository = new DBStudentRepository();
    private IClassRepository iClassRepository = new DBClassRepository();

    public String calculateAttendance(int id, int classId){
        double absence = iClassRepository.getStudentAbsence(id, classId).size();
        double presence = iClassRepository.getStudentPresence(id, classId).size();
        double attendanceInteger = absence / ( absence + presence ) * 100;

        String attendance = String.valueOf(attendanceInteger) + " %";
        System.out.println(absence);
        System.out.println(presence);
        System.out.println(attendance);


        return attendance;
    }
}
