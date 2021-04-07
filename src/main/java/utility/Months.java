package utility;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public enum Months {
    January,
    February,
    March,
    April,
    May,
    June,
    July,
    August,
    September,
    October,
    November,
    December;

    private static final List<String> VALUES;

    static {
        VALUES = new ArrayList<>();
        for (Months month : Months.values()) {
            VALUES.add(month.name());
        }
    }

    public static List<String> getValues() {
        return Collections.unmodifiableList(VALUES);
    }
}
