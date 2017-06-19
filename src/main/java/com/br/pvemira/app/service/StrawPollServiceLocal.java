package com.br.pvemira.app.service;

import com.br.pvemira.app.model.DTO.StrawPollDTO;
import com.br.pvemira.app.model.StrawPoll;
import com.br.pvemira.app.model.Vote;

import java.time.LocalDate;
import java.util.List;

/**
 * Created by pvmeira on 17/06/17.
 */
public interface StrawPollServiceLocal {

    StrawPoll findCurrentStrawPoll();

    void newStrawPoll(LocalDate date, String name);

    StrawPollDTO getResultFromCurrentPool(List<Vote> votes);

    Boolean isAvaliableToANewPoll();

    Boolean isAvaliable();
}
