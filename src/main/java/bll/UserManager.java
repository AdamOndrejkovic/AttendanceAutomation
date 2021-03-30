package bll;

import be.Attendance;
import be.User;
import dal.dao.UserDAO;

public class UserManager {
    private UserDAO userDAO;

    public UserManager() {
        userDAO = new UserDAO();
    }

    public Boolean isTeacher(int userID) {
        return userDAO.getUserByID(userID).isTeacher();
    }

    public String getName(int userID) {
        return userDAO.getUserByID(userID).getName();
    }

    public String getUsername(int userID) {
        return userDAO.getUserByID(userID).getUserName();
    }

    public int getID(String username) {
        for (User user : userDAO.getUsers()) {
            if(user.getUserName().equals(username)){
                return user.getId();
            }
        }
        return -1;
    }

    public void setPassword(int userID, String newPassword) {
        userDAO.getUserByID(userID).setPassword(newPassword);
    }

    public boolean hasAttended(int userID, int day, int month, int year) {
        User user = userDAO.getUserByID(userID);
        String attendanceDate = day + "/" + month + "/" + year;

        if (user != null) {
            for (Attendance attendance : user.getAttendances()) {
                if (attendance.getDate().equals(attendanceDate)) {
                    return true;
                }
            }
        }

        return false;
    }

    public void addAttendance(int userID, int day, int month, int year) {
        User user = userDAO.getUserByID(userID);
        String attendanceDate = day + "/" + month + "/" + year;

        if (user != null) {
            if (!hasAttended(userID,day,month,year)) {
                user.getAttendances().add(new Attendance(0, attendanceDate, true));
            }
        }
    }

    public void printAttendance(int userID){
        System.out.println("ATTENDANCE: ");
        for(Attendance attendance:userDAO.getUserByID(userID).getAttendances()){
            System.out.println(attendance.getDate());
        }

    }

}
