package com.br.pvemira.app.BO;

import com.br.pvemira.app.model.DTO.VoterDTO;
import com.br.pvemira.app.model.Restaurant;
import com.br.pvemira.app.model.Voter;
import com.br.pvemira.app.util.TimeUtil;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.Date;

/**
 * Created by pvmeira on 17/06/17.
 */
public class VoterBO {

    public Voter transformVoterDTO2Voter(VoterDTO voterDTO) {
        Voter voter = new Voter(voterDTO.getEmail(), voterDTO.getName());
        return voter;
    }

    public Boolean validateVoter(Voter voter) {

        LocalDate date = voter.getLastVoted();
        if (date != null) {
            LocalDate date1 = LocalDate.now();
            int day = date.getDayOfMonth();
            int currentDay = date1.getDayOfMonth();
            if (day == currentDay) {
                return Boolean.FALSE;
            }
        }
        return Boolean.TRUE;
    }

    public VoterDTO transformVoter2VoterDTO(Voter voter) {
        return new VoterDTO(voter.getId(), voter.getEmail(), voter.getName());
    }

    public Boolean validateRestaurant(Restaurant restaurant) {
        LocalDate addOnStrawPoll = restaurant.getAddOnStrawPoll();
        if (addOnStrawPoll != null) {
            return TimeUtil.validRestaurantForNewPoll(addOnStrawPoll);
        }
        return Boolean.TRUE;
    }
}
