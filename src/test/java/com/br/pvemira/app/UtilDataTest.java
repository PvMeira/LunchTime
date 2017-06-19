package com.br.pvemira.app;

import com.br.pvemira.app.model.DTO.StrawPollDTO;
import com.br.pvemira.app.model.DTO.VoterDTO;
import com.br.pvemira.app.model.Restaurant;
import com.br.pvemira.app.model.StrawPoll;
import com.br.pvemira.app.model.Voter;

import java.time.LocalDate;
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

    public VoterDTO getNewVoterDTO() {
        return new VoterDTO(1L, "Teste@test.vom", "Foo");
    }

    public Voter getNewVoter() {
        return new Voter(1L, "Teste@test.vom", "Foo", null);
    }

    public Restaurant getNewRestaurant() {
        return new Restaurant("Ponto 50", "Dentro da PUC");
    }

    public StrawPoll getNewStrawPoll() {
        return new StrawPoll("Teste", LocalDate.now(), false);
    }

    public StrawPollDTO getNewStrawPollDTO() {
        return new StrawPollDTO(1L, "Teste", LocalDate.now());
    }
}
