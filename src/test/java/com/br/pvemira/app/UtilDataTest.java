package com.br.pvemira.app;

import com.br.pvemira.app.model.DTO.StrawPollDTO;
import com.br.pvemira.app.model.DTO.VoterDTO;
import com.br.pvemira.app.model.Restaurant;
import com.br.pvemira.app.model.StrawPoll;
import com.br.pvemira.app.model.Vote;
import com.br.pvemira.app.model.StrawPoll;
import com.br.pvemira.app.model.Voter;

import java.time.LocalDate;
import java.util.ArrayList;
import java.time.LocalDate;
import java.util.Calendar;
import java.util.List;

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

//    public StrawPoll getNewStrawPoll() {
//        return new StrawPoll("Fake test", LocalDate.now(), Boolean.TRUE);
//    }

    public Vote getNewVote() {
        return new Vote(getNewVoter(), getNewRestaurant(), getNewStrawPoll());
    }

    public List<Vote> getListOfVotesFromTheSameStrawPoll() {
        List<Vote> l = new ArrayList<>();
        Vote vote = new Vote(new Voter(1L, "fakeEmail1", "Fake 01", null), new Restaurant(1l, "FakeR", "ss"), getNewStrawPoll());
        Vote vote1 = new Vote(new Voter(2L, "fakeEmail2", "Fake 02", null), new Restaurant(2l, "FakeR1", "ss1"), getNewStrawPoll());
        Vote vote2 = new Vote(new Voter(3L, "fakeEmail1", "Fake 03", null), new Restaurant(3l, "FakeR3", "ss3"), getNewStrawPoll());
        l.add(vote);
        l.add(vote1);
        l.add(vote2);


        return l;
    }

    public List<Vote> getListOfVotesFromTheSameStrawPollWithSameRestaurants() {
        List<Vote> l = new ArrayList<>();
        Vote vote = new Vote(new Voter(1L, "fakeEmail1", "Fake 01", null), new Restaurant(1l, "FakeR", "ss"), getNewStrawPoll());
        Vote vote1 = new Vote(new Voter(2L, "fakeEmail2", "Fake 02", null), new Restaurant(1l, "FakeR1", "ss1"), getNewStrawPoll());
        Vote vote2 = new Vote(new Voter(3L, "fakeEmail1", "Fake 03", null), new Restaurant(2l, "FakeR3", "ss3"), getNewStrawPoll());
        Vote vote3 = new Vote(new Voter(1L, "fakeEmail1", "Fake 01", null), new Restaurant(4l, "FakeR", "ss"), getNewStrawPoll());
        Vote vote4 = new Vote(new Voter(2L, "fakeEmail2", "Fake 02", null), new Restaurant(1l, "FakeR1", "ss1"), getNewStrawPoll());
        Vote vote5 = new Vote(new Voter(3L, "fakeEmail1", "Fake 03", null), new Restaurant(2l, "FakeR3", "ss3"), getNewStrawPoll());
        l.add(vote);
        l.add(vote1);
        l.add(vote2);
        l.add(vote3);
        l.add(vote4);
        l.add(vote5);


        return l;
    }


    public List<StrawPoll> getNewListOfStrawOrderByDateDesc() {
        List<StrawPoll> r = new ArrayList<>();

        LocalDate date = LocalDate.of(2017, 11, 01);
        LocalDate date1 = LocalDate.of(2017, 11, 04);
        LocalDate date2 = LocalDate.of(2017, 11, 07);

        StrawPoll strawPoll = getNewStrawPoll();
        strawPoll.setNew(Boolean.FALSE);
        strawPoll.setDate(date);


        StrawPoll newStrawPoll = getNewStrawPoll();
        newStrawPoll.setNew(Boolean.TRUE);
        newStrawPoll.setDate(date1);


        StrawPoll newStrawPoll1 = getNewStrawPoll();
        newStrawPoll1.setDate(date2);
        newStrawPoll1.setNew(Boolean.TRUE);

        r.add(newStrawPoll1);
        r.add(newStrawPoll);
        r.add(strawPoll);

        return r;
    }

    public StrawPoll getNewStrawPoll() {
        return new StrawPoll("Teste", LocalDate.now(), false);
    }

    public StrawPollDTO getNewStrawPollDTO() {
        return new StrawPollDTO(1L, "Teste", LocalDate.now());
    }
}
