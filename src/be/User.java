package be;

import java.util.ArrayList;
import java.util.List;

public class User {
    private int id;
    private String name;
    private String password;
    private boolean teacher;
    private String userName;
    private List<Attendance> attendance;

    public User(int id, String name, String userName, String password,boolean teacher){
        this.id = id;
        this.name = name;
        this.userName = userName;
        this.password = password;
        this.teacher = teacher;
        attendance = new ArrayList<>();
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public boolean isTeacher() {
        return teacher;
    }

    public void setTeacher(boolean teacher) {
        this.teacher = teacher;
    }

    public List<Attendance> getAttendances(){
        return attendance;
    }
}
