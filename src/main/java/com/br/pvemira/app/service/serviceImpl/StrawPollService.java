package com.br.pvemira.app.service.serviceImpl;

import com.br.pvemira.app.BO.StrawPollBO;
import com.br.pvemira.app.model.DTO.RestaurantDTO;
import com.br.pvemira.app.model.DTO.StrawPollDTO;
import com.br.pvemira.app.model.Restaurant;
import com.br.pvemira.app.model.StrawPoll;
import com.br.pvemira.app.model.Vote;
import com.br.pvemira.app.repository.StrawPollRepository;
import com.br.pvemira.app.service.RestaurantServiceLocal;
import com.br.pvemira.app.service.StrawPollServiceLocal;
import com.br.pvemira.app.service.VoteServiceLocal;
import com.br.pvemira.app.util.TimeUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.Calendar;
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


    @Override
    public StrawPoll findCurrentStrawPoll() {
        List<StrawPoll> allStrawPolls = this.strawPollRepository.findCurrentStrawPoll(LocalDate.now());
        if (!allStrawPolls.isEmpty()) {
            return allStrawPolls.get(0);
        }
        return null;
    }


    @Override
    public void newStrawPoll(LocalDate date, String name) {
        StrawPoll poll = this.findCurrentStrawPoll();
        if (poll != null) {
            poll.setNew(Boolean.FALSE);
            this.strawPollRepository.save(poll);
        }
        this.strawPollRepository.save(new StrawPoll(name, date, Boolean.TRUE));
    }


    @Override
    public StrawPollDTO getResultFromCurrentPool(List<Vote> voteList) {
        StrawPoll currentStrawPoll = this.findCurrentStrawPoll();
        StrawPollDTO pollDTO = this.strawPollBO.tranformStrawPoll2StrawPollSTO(currentStrawPoll);

        List<Restaurant> restaurantList = voteList.stream().map(vote -> vote.getRestaurant()).distinct().collect(Collectors.toList());

        pollDTO.setRestaurantList(this.strawPollBO.tranformRestaurant2RestaurantDTO(restaurantList));

        voteList.stream().forEach(vote -> {
            Long idRestaurant = vote.getRestaurant().getId();
            pollDTO.getRestaurantList().stream().forEach(dto -> {
                if (idRestaurant.equals(dto.getId())) {
                    dto.setTotalVotes(dto.getTotalVotes() + 1);
                }
            });
        });

        List<RestaurantDTO> restDTOSorderByTotalVotes = pollDTO.getRestaurantList().stream().sorted(Comparator.comparing(RestaurantDTO::getTotalVotes).reversed()).collect(Collectors.toList());
        pollDTO.setRestaurantList(restDTOSorderByTotalVotes);
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

    @Override
    public Boolean isAvaliable() {
        return TimeUtil.isPassPollTime();
    }

}
