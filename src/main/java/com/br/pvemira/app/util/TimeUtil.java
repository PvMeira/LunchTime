package com.br.pvemira.app.util;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.Calendar;

/**
 * Created by pvmeira on 18/06/17.
 */
public class TimeUtil {

    private static final Integer HOUR_TO_POLL_TIME = 11;
    private static final Integer MINUTE_TO_POLL_TIME = 00;
    private static final Integer DAYS_TO_VALIDATE_RESTAURANT = 5;

    public static Boolean isPassPollTime() {
        Calendar c = Calendar.getInstance();
        c.set(Calendar.HOUR_OF_DAY, HOUR_TO_POLL_TIME);
        c.set(Calendar.MINUTE, MINUTE_TO_POLL_TIME);
        c.set(Calendar.SECOND, 0);
        c.set(Calendar.MILLISECOND, 0);

        Calendar instance = Calendar.getInstance();
        return instance.after(c);
    }

    public static Boolean validRestaurantForNewPoll(LocalDate date,LocalDate date01) {
        long between = ChronoUnit.DAYS.between(date, date01);
        return Boolean.TRUE ? between > DAYS_TO_VALIDATE_RESTAURANT : Boolean.FALSE;
    }
}
