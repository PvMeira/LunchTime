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

    /**
     * Perform the vote, base on the histories, the condition that the vote can be validate are as follow :
     * 1º - The Voter can not be null
     * 2º - The last time that the voter, has voted, must be at least one day(as Specified in the histories)
     * 3º - The Restaurant can not be null
     * 4º - The restaurant must have a date of at least 1 week(as Specified in the histories)
     * 5º - If the Restaurant don't follow the 4ª condition BUT the same Restaurant is present in the
     * current poll,his is a candidate to be use on the vote if the remaining conditions are followed
     *
     * @param email        the email that's is going to be use to found the Voter
     * @param idRestaurant the id that's is going to be use to found the Restaurant
     * @return
     */
    @Override
    public Boolean vote(String email, Long idRestaurant) {

        Boolean validateVoter = Boolean.FALSE;
        Boolean validateRestaurant = Boolean.TRUE;

        StrawPoll currentStrawPoll = this.strawPollService.findCurrentStrawPoll();

        Restaurant restaurant = this.restaurantService.findByid(idRestaurant);
        Voter voter = this.voterService.findByEmail(email);
        List<Vote> votesByPollEquals = this.voteRepository.findVotesByPollEquals(currentStrawPoll);

        if (voter != null) {
            validateVoter = this.voterBO.validateVoter(voter);
        }

        if (restaurant != null && !this.voteBO.validateRestaurantIsOnCurrentPoll(votesByPollEquals, restaurant)) {
            validateRestaurant = this.voterBO.validateRestaurant(restaurant);
        }

        if (validateVoter && validateRestaurant && restaurant != null && currentStrawPoll != null) {
            Vote vote = new Vote(voter, restaurant, currentStrawPoll);
            this.voteRepository.save(vote);
            this.voterService.updateVoteDateFromVoter(LocalDate.now(), voter);
            this.restaurantService.addVoteToRestaurant(restaurant, vote);
            this.restaurantService.addStrawPollDateToRestaurant(restaurant, LocalDate.now());

            return Boolean.TRUE;
        }
        return Boolean.FALSE;
    }

    /**
     * Find all the votes that belong to the current poll
     *
     * @param poll the current poll that contains the votes
     * @return
     */
    @Override
    public List<Vote> findVotesbyStrawPollId(StrawPoll poll) {
        return this.voteRepository.findVotesByPollEquals(poll);
    }


}
