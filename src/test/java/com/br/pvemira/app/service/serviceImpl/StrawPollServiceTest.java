package com.br.pvemira.app.service.serviceImpl;

import com.br.pvemira.app.BO.StrawPollBO;
import com.br.pvemira.app.UtilDataTest;
import com.br.pvemira.app.model.DTO.RestaurantDTO;
import com.br.pvemira.app.model.DTO.StrawPollDTO;
import com.br.pvemira.app.model.Restaurant;
import com.br.pvemira.app.model.StrawPoll;
import com.br.pvemira.app.model.Vote;
import com.br.pvemira.app.repository.RestaurantRepository;
import com.br.pvemira.app.repository.StrawPollRepository;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;

/**
 * Created by pvmeira on 20/06/17.
 */
@RunWith(MockitoJUnitRunner.class)
public class StrawPollServiceTest extends UtilDataTest {
    @Mock
    StrawPollRepository strawPollRepository;
    @Mock
    RestaurantService restaurantService;

    @Mock
    StrawPollBO pollBO;

    @InjectMocks
    StrawPollService strawPollService;

    @Before
    public void setUp() {
    }

    @Test
    public void findCurrentStrawPollWhenDataIsPresent() {
        when(strawPollRepository.findCurrentStrawPoll(LocalDate.now())).thenReturn(getNewListOfStrawOrderByDateDesc());
        LocalDate date = LocalDate.of(2017, 11, 07);
        assertEquals(this.strawPollService.findCurrentStrawPoll().getDate(), date);
    }

    @Test
    public void findCurrentStrawPollWhenNoDataIsPresent() {
        when(strawPollRepository.findCurrentStrawPoll(LocalDate.now())).thenReturn(new ArrayList<>());
        Assert.assertNull(this.strawPollService.findCurrentStrawPoll());
    }


    @Test
    public void getResultFromCurrentPool() {

        List<Vote> listVotes = getListOfVotesFromTheSameStrawPollWithSameRestaurants();
        List<Restaurant> restaurants = listVotes.stream().map(vote -> vote.getRestaurant()).collect(Collectors.toList());
        List<RestaurantDTO> dtos = new ArrayList<>();
        restaurants.stream().forEach(restaurant -> {
            dtos.add(new RestaurantDTO(restaurant.getId(), restaurant.getName(), restaurant.getLocation()));
        });
        StrawPoll poll = getNewStrawPoll();

        when(this.pollBO.tranformStrawPoll2StrawPollSTO(Mockito.any(StrawPoll.class))).thenReturn(new StrawPollDTO(poll.getId(), poll.getName(), poll.getDate()));
        when(this.pollBO.tranformRestaurant2RestaurantDTO(Mockito.anyList())).thenReturn(dtos);
        when(this.strawPollRepository.findCurrentStrawPoll(LocalDate.now())).thenReturn(getNewListOfStrawOrderByDateDesc());
        when(this.restaurantService.getRestaurantsFromVoteList(Mockito.anyList())).thenReturn(getNewListOfStrawOrderByDateDesc());

        List<RestaurantDTO> dtos1 = this.strawPollService.getResultFromCurrentPool(listVotes).getRestaurantList();

        assertEquals(dtos.size(), 6);

        Assert.assertTrue(dtos1.get(0).getTotalVotes().equals(3));
        Assert.assertTrue(dtos1.get(1).getTotalVotes().equals(3));
        Assert.assertTrue(dtos1.get(2).getTotalVotes().equals(3));
        Assert.assertTrue(dtos1.get(3).getTotalVotes().equals(2));
        Assert.assertTrue(dtos1.get(4).getTotalVotes().equals(2));
        Assert.assertTrue(dtos1.get(5).getTotalVotes().equals(1));
    }

    @Test
    public void isAvaliableToANewPoll() {
    }

    @Test
    public void isAvaliable() {
    }

}