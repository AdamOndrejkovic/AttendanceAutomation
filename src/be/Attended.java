package be;

public class Attended {
    private int id;
    private int userId;
    private String course;
    private String date;
    private boolean attended;

    public Attended(int id, int userId, String course, String date, boolean attended) {
        this.id = id;
        this.userId = userId;
        this.course = course;
        this.date = date;
        this.attended = attended;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getCourse() {
        return course;
    }

    public void setCourse(String course) {
        this.course = course;
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
