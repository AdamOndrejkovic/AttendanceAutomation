package be.user;

import bll.AttendanceCalculator;

public class Student extends User{
    private double attendance;
    private AttendanceCalculator attendanceCalculator = new AttendanceCalculator();;
    public Student(String firstName, String lastName, String email, int id) {
        super(firstName, lastName, email, id);
        setAttendance(id);
    }

    public double getAttendance() {
        return attendance;
    }

    public void setAttendance(int id) {
           this.attendance =  attendanceCalculator.calculateAttendance(id);
    }
}
