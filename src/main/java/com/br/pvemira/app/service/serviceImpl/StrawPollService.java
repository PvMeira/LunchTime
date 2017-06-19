package com.br.pvemira.app.service.serviceImpl;

import com.br.pvemira.app.BO.StrawPollBO;
import com.br.pvemira.app.model.DTO.RestaurantDTO;
import com.br.pvemira.app.model.DTO.StrawPollDTO;
import com.br.pvemira.app.model.StrawPoll;
import com.br.pvemira.app.model.Vote;
import com.br.pvemira.app.repository.StrawPollRepository;
import com.br.pvemira.app.service.RestaurantServiceLocal;
import com.br.pvemira.app.service.StrawPollServiceLocal;
import com.br.pvemira.app.util.TimeUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by pvmeira on 17/06/17.
 */
@Service
public class StrawPollService implements StrawPollServiceLocal {

    private final StrawPollRepository strawPollRepository;
    private final RestaurantServiceLocal restaurantService;
    private StrawPollBO strawPollBO;

    @Autowired
    public StrawPollService(StrawPollRepository strawPollRepository, RestaurantServiceLocal restaurantService) {
        this.strawPollRepository = strawPollRepository;
        this.restaurantService = restaurantService;
        this.strawPollBO = new StrawPollBO();
    }

    /**
     * Return the currentPoll that is going on, case its more than one,
     * get the first one since the list is order by date DESC
     *
     * @return the current StrawPoll
     */
    @Override
    public StrawPoll findCurrentStrawPoll() {
        List<StrawPoll> allStrawPolls = this.strawPollRepository.findCurrentStrawPoll(LocalDate.now());
        if (!allStrawPolls.isEmpty()) {
            return allStrawPolls.get(0);
        }
        return null;
    }

    /**
     * Create a new StrawPoll acording to the rules on the history.
     * Also if there is a current poll when creating another, the currents is
     * invalidated, so that the new represent the current pooll
     *
     * @param date
     * @param name
     */
    @Override
    public void newStrawPoll(LocalDate date, String name) {
        StrawPoll poll = this.findCurrentStrawPoll();
        if (poll != null) {
            poll.setNew(Boolean.FALSE);
            this.strawPollRepository.save(poll);
        }
        this.strawPollRepository.save(new StrawPoll(name, date, Boolean.TRUE));
    }

    /**
     * Return the StrawPollDTO that contains the parcial/final result  of the current poll,
     * and also order the list of Restaurant base on the number of totalVotes that they have.
     *
     * @param votes the votes that belong to the current StrawPoll
     * @return
     */
    @Override
    public StrawPollDTO getResultFromCurrentPool(List<Vote> votes) {
        StrawPollDTO pollDTO = this.strawPollBO.tranformStrawPoll2StrawPollSTO(this.findCurrentStrawPoll());
        pollDTO.setRestaurantList(this.strawPollBO.tranformRestaurant2RestaurantDTO(this.restaurantService.getRestaurantsFromVoteList(votes)));

        votes.stream().forEach(vote -> {
            Long idRestaurant = vote.getRestaurant().getId();
            pollDTO.getRestaurantList().stream().forEach(dto -> {
                if (idRestaurant.equals(dto.getId())) {
                    dto.setTotalVotes(dto.getTotalVotes() + 1);
                }
            });
        });
        pollDTO.setRestaurantList(pollDTO.getRestaurantList().stream()
                .sorted(Comparator.comparing(RestaurantDTO::getTotalVotes)
                        .reversed()).collect(Collectors.toList()));
        return pollDTO;
    }

    @Override
    public Boolean isAvaliableToANewPoll() {
        StrawPoll currentStrawPoll = this.findCurrentStrawPoll();
        if (currentStrawPoll == null || currentStrawPoll.getNew() == Boolean.FALSE || TimeUtil.isPassPollTime()) {
            return Boolean.TRUE;
        }
        return Boolean.FALSE;
    }

    /**
     * Verifys if is time to show the winner of the current poll
     *
     * @return Boolean.TRUE case it pass the time estipulated to present the winner
     * or Boolean.FALSE if its not time yet
     */
    @Override
    public Boolean isAvaliable() {
        return TimeUtil.isPassPollTime();
    }

}
