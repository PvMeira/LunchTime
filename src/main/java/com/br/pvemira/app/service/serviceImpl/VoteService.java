package com.br.pvemira.app.service.serviceImpl;

import com.br.pvemira.app.BO.VoteBO;
import com.br.pvemira.app.BO.VoterBO;
import com.br.pvemira.app.model.Restaurant;
import com.br.pvemira.app.model.StrawPoll;
import com.br.pvemira.app.model.Vote;
import com.br.pvemira.app.model.Voter;
import com.br.pvemira.app.repository.VoteRepository;
import com.br.pvemira.app.service.RestaurantServiceLocal;
import com.br.pvemira.app.service.StrawPollServiceLocal;
import com.br.pvemira.app.service.VoteServiceLocal;
import com.br.pvemira.app.service.VoterServiceLocal;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

/**
 * Created by pvmeira on 17/06/17.
 */
@Service
public class VoteService implements VoteServiceLocal {

    private final VoteRepository voteRepository;
    private final RestaurantServiceLocal restaurantService;
    private final VoterServiceLocal voterService;
    private final StrawPollServiceLocal strawPollService;
    private VoteBO voteBO;
    private VoterBO voterBO;

    @Autowired
    public VoteService(VoteRepository voteRepository, RestaurantServiceLocal restaurantService, VoterServiceLocal voterService, StrawPollServiceLocal strawPollService) {
        this.voteRepository = voteRepository;
        this.restaurantService = restaurantService;
        this.voterService = voterService;
        this.strawPollService = strawPollService;
        this.voteBO = new VoteBO();
        this.voterBO = new VoterBO();
    }

    @Override
    public Boolean vote(String email, Long idRestaurant) {

        Restaurant restaurant = this.restaurantService.findByid(idRestaurant);
        Voter voter = this.voterService.findByEmail(email);
        Boolean validateVoter = Boolean.FALSE;
        if (voter != null) {
            validateVoter = this.voterBO.validateVoter(voter);
        }
        StrawPoll currentStrawPoll = this.strawPollService.findCurrentStrawPoll();

        if (validateVoter && restaurant != null && currentStrawPoll != null) {
            Vote vote = new Vote(voter, restaurant, currentStrawPoll);
            this.voteRepository.save(vote);
            this.voterService.updateVoteDateFromVoter(LocalDate.now(), voter);
            this.restaurantService.addVoteToRestaurant(restaurant, vote);
            this.strawPollService.addRestaurantToCurrentStrawPoll(idRestaurant);

            return Boolean.TRUE;
        }

        return Boolean.FALSE;

    }

    @Override
    public List<Vote> findVotesbyStrawPollId(StrawPoll poll) {
        return this.voteRepository.findVotesByPollEquals(poll);
    }


}
