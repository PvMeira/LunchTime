package com.br.pvemira.app.util;

import com.br.pvemira.app.UtilDataTest;
import org.junit.Assert;
import org.junit.Test;

import java.time.LocalDate;
import java.util.Calendar;

import static org.junit.Assert.*;

/**
 * Created by pvmeira on 19/06/17.
 */
public class TimeUtilTest extends UtilDataTest {

    @Test
    public void validRestaurantForNewPollTrue() {
        LocalDate date = LocalDate.of(2017, 01, 01);
        LocalDate date1 = LocalDate.of(2017, 01, 06);
        Assert.assertTrue(TimeUtil.validRestaurantForNewPoll(date, date1));
    }

    @Test
    public void validRestaurantForNewPollFalse() {
        LocalDate date = LocalDate.of(2017, 01, 01);
        LocalDate date1 = LocalDate.of(2017, 01, 02);
        Assert.assertFalse(TimeUtil.validRestaurantForNewPoll(date, date1));
    }


}