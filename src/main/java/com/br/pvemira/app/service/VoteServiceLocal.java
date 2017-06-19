package com.br.pvemira.app.service;

import com.br.pvemira.app.model.StrawPoll;
import com.br.pvemira.app.model.Vote;

import java.util.List;

/**
 * Created by pvmeira on 17/06/17.
 */
public interface VoteServiceLocal {
    Boolean vote(String email, Long idRestaurant);

    List<Vote> findVotesbyStrawPollId(StrawPoll poll);
}
