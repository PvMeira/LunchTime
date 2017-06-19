package com.br.pvemira.app.BO;

import com.br.pvemira.app.model.DTO.VoterDTO;
import com.br.pvemira.app.model.Restaurant;
import com.br.pvemira.app.model.Voter;
import com.br.pvemira.app.util.TimeUtil;

import java.time.LocalDate;

/**
 * Created by pvmeira on 17/06/17.
 */
public class VoterBO {

    public Voter transformVoterDTO2Voter(VoterDTO voterDTO) {
        Voter voter = new Voter(voterDTO.getEmail(), voterDTO.getName());
        return voter;
    }

    public Boolean validateVoter(Voter voter, LocalDate now) {

        LocalDate date = voter.getLastVoted();
        if (date != null) {
            int day = date.getDayOfMonth();
            int currentDay = now.getDayOfMonth();
            if (day == currentDay) {
                return Boolean.FALSE;
            }
        }
        return Boolean.TRUE;
    }

    public VoterDTO transformVoter2VoterDTO(Voter voter) {
        return new VoterDTO(voter.getId(), voter.getEmail(), voter.getName());
    }

    public Boolean validateRestaurant(Restaurant restaurant, LocalDate now) {
        LocalDate addOnStrawPoll = restaurant.getAddOnStrawPoll();
        if (addOnStrawPoll != null) {
            return TimeUtil.validRestaurantForNewPoll(addOnStrawPoll, now);
        }
        return Boolean.TRUE;
    }
}
