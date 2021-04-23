package utility;

import be.Date;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.YearMonth;
import java.time.temporal.Temporal;
import java.util.Locale;

public class Calendar {
    public static java.util.Calendar calendar = java.util.Calendar.getInstance(Locale.GERMANY);

    public static Date getDate() {
        return new Date(getYear(), getMonth(), getDay());
    }

    public static int getMonth() {
        return calendar.get(java.util.Calendar.MONTH) + 1;
    }

    public static int getDay() {
        return calendar.get(java.util.Calendar.DAY_OF_MONTH);
    }

    public static int getYear() {
        return calendar.get(java.util.Calendar.YEAR);
    }

    public static int getDaysInMonth(int year,int month) {
        return YearMonth.of(year, month).lengthOfMonth() + 1;
    }
}
