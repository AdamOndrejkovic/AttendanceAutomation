package be.user;

import bll.AttendanceCalculator;

public class Student extends User{
    private String attendance = "";
    private AttendanceCalculator attendanceCalculator = new AttendanceCalculator();;
    public Student(String firstName, String lastName, String email, int id) {
        super(firstName, lastName, email, id);
    }

    public String getAttendance() {
        return attendance;
    }

    public void setAttendance(int id, int classId) {
           this.attendance =  attendanceCalculator.calculateAttendance(id, classId);
    }
}
