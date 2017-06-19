package com.br.pvemira.app;

import java.util.Calendar;

/**
 * Created by pvmeira on 19/06/17.
 */
public abstract class UtilDataTest {

    private static final Integer HOUR_TO_POLL_TIME = 11;
    private static final Integer MINUTE_TO_POLL_TIME = 00;
    private static final Integer DAYS_TO_VALIDATE_RESTAURANT = 5;

    public Calendar getApplicationWinnerTime() {
        Calendar c = Calendar.getInstance();
        c.set(Calendar.HOUR_OF_DAY, HOUR_TO_POLL_TIME);
        c.set(Calendar.MINUTE, MINUTE_TO_POLL_TIME);
        c.set(Calendar.SECOND, 0);
        c.set(Calendar.MILLISECOND, 0);
        return c;
    }
}
