package bll;

import java.time.YearMonth;
import java.util.Calendar;
import java.util.Locale;

public class StatisticsManager {

    public StatisticsManager() {
    }

    public int getPresentDays(int userID, int month) {
        /*
        int presentDays = 0;

        User user = userDAO.getUserByID(userID);
        for (Attendance a : user.getAttendances()) {

            String[] date = a.getDate().split("/");
            if (Integer.parseInt(date[1]) == month) {
                presentDays++;
            }

        }
        return presentDays;

         */
        return -1;
    }

    public int getAbsenceDays(int userID, int month) {
        /*
        Calendar calendar = Calendar.getInstance(Locale.GERMANY);
        int currentMonth = calendar.get(Calendar.MONTH) + 1;

        if (month == currentMonth) {

            int currentDay = calendar.get(Calendar.DAY_OF_MONTH);
            return currentDay - getPresentDays(userID, month);

        } else if (month < currentMonth) {
            int daysInMonth = YearMonth.of(calendar.get(Calendar.YEAR), month).lengthOfMonth();
            return daysInMonth - getPresentDays(userID, month);
        }
         */
        return -1;
    }
}