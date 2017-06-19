package com.br.pvemira.app.util;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.Calendar;

/**
 * Created by pvmeira on 18/06/17.
 */
public class TimeUtil {

    public static Boolean isPassPollTime() {
        Calendar c = Calendar.getInstance();
        c.set(Calendar.HOUR_OF_DAY, 11);
        c.set(Calendar.MINUTE, 00);
        c.set(Calendar.SECOND, 0);
        c.set(Calendar.MILLISECOND, 0);

        Calendar instance = Calendar.getInstance();
        return instance.after(c);
    }
    public static Boolean validRestaurantForNewPoll(LocalDate date){
        long between = ChronoUnit.WEEKS.between(date, LocalDate.now());
        return Boolean.TRUE ? between > 7 : Boolean.FALSE;
    }
}
