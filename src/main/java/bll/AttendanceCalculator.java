package bll;

import be.Date;
import dal.IClassRepository;
import dal.IStudentRepository;
import dal.db.DBClassRepository;
import dal.db.DBStudentRepository;

import java.text.DecimalFormat;
import java.util.List;

public class AttendanceCalculator {
    private IClassRepository iClassRepository = new DBClassRepository();
    private DecimalFormat decimalFormat = new DecimalFormat("#,##");

    public String calculateAttendance(int id, int classId){
        double absence = getAbsence(id, classId);
        double presence = getPresence(id, classId);
        double attendanceInteger = absence / ( absence + presence ) * 100;

        return decimalFormat.format(attendanceInteger) + " %";
    }

    public int getPresence(int studentId, int classId){
        return iClassRepository.getStudentPresence(studentId, classId).size();
    }

    public int getAbsence(int studentId, int classId){
        return iClassRepository.getStudentAbsence(studentId, classId).size();
    }

    public List<Date> getAbsenceList(int studentId, int classId){
        return  iClassRepository.getStudentAbsence(studentId,classId);
    }

    public List<Date> getPresenceList(int studentId, int classId){
        return  iClassRepository.getStudentPresence(studentId,classId);
    }
}
