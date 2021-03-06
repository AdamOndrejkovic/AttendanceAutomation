package be;

import java.time.DayOfWeek;
import java.time.LocalDate;

public class Date {
    private int year, month, day;

    public Date(int year, int month, int day) {
        this.year = year;
        this.month = month;
        this.day = day;
    }
    public Date(String date){
        String[] arrayDate = date.split("-");
        year = Integer.parseInt(arrayDate[0]);
        month = Integer.parseInt(arrayDate[1]);
        day = Integer.parseInt(arrayDate[2]);
    }
    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public int getMonth() {
        return month;
    }

    public void setMonth(int month) {
        this.month = month;
    }

    public int getDay() {
        return day;
    }

    public void setDay(int day) {
        this.day = day;
    }

    public int getDayOfTheWeek(){
        return DayOfWeek.from(LocalDate.of(year,month,day)).getValue();
    }

    @Override
    public String toString() {
        return year + "-" + month + "-" + day;
    }

    public String dayToString(int day){
        return  Integer.toString(day);
    }
}
