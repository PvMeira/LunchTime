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
import org.mockito.runners.MockitoJUnitRunner;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import static org.mockito.Mockito.when;

/**
 * Created by pvmeira on 20/06/17.
 */
@RunWith(MockitoJUnitRunner.class)
public class StrawPollServiceTest extends UtilDataTest {

    @InjectMocks
    StrawPollService strawPollService;

    @Mock
    StrawPollRepository strawPollRepository;

    @InjectMocks
    RestaurantService restaurantService;

//    @Mock
//    RestaurantRepository restaurantRepository;

    @Mock
    StrawPollBO pollBO;

    @Before
    public void setUp() {
    }

    @Test
    public void findCurrentStrawPollWhenDataIsPresent() {
        when(strawPollRepository.findCurrentStrawPoll(LocalDate.now())).thenReturn(getNewListOfStrawOrderByDateDesc());
        LocalDate date = LocalDate.of(2017, 11, 07);
        Assert.assertEquals(this.strawPollService.findCurrentStrawPoll().getDate(),date);
    }
    @Test
    public void findCurrentStrawPollWhenNoDataIsPresent() {
        when(strawPollRepository.findCurrentStrawPoll(LocalDate.now())).thenReturn(new ArrayList<>());
        Assert.assertNull(this.strawPollService.findCurrentStrawPoll());
    }


    @Test
    public void getResultFromCurrentPool() {
//
//        List<Vote> listVotes = getListOfVotesFromTheSameStrawPollWithSameRestaurants();
//        List<Restaurant> restaurants = listVotes.stream().map(vote -> vote.getRestaurant()).collect(Collectors.toList());
//        StrawPoll poll = getNewStrawPoll();
//
//        when(this.pollBO.tranformStrawPoll2StrawPollSTO(poll)).thenReturn(new StrawPollDTO(poll.getId(),poll.getName(),poll.getDate()));
//        when(this.pollBO.tranformRestaurant2RestaurantDTO(new ArrayList<>())).thenReturn(restaurants);
//        List<RestaurantDTO> dtos = this.strawPollService.getResultFromCurrentPool(listVotes).getRestaurantList();
//
//        Assert.assertEquals(dtos.size(),3);
//        Assert.assertEquals(dtos.get(0),3);
//        Assert.assertEquals(dtos.get(1),2);
//        Assert.assertEquals(dtos.get(2),1);

    }

    @Test
    public void isAvaliableToANewPoll() {
    }

    @Test
    public void isAvaliable() {
    }

}