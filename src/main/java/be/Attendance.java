package be;

import java.util.List;

public class Attendance {
    private int id;
    private String date;
    private boolean attended;

    public Attendance(int id, String date, boolean attended) {
        this.id = id;
        this.date = date;
        this.attended = attended;
    }

    public int getId() {
        return id;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public boolean isAttended() {
        return attended;
    }

    public void setAttended(boolean attended) {
        this.attended = attended;
    }
}
