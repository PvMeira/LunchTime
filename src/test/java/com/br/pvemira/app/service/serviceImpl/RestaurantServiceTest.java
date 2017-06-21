package com.br.pvemira.app.service.serviceImpl;

import com.br.pvemira.app.BO.RestaurantBO;
import com.br.pvemira.app.UtilDataTest;
import com.br.pvemira.app.model.Restaurant;
import com.br.pvemira.app.model.Vote;
import com.br.pvemira.app.repository.RestaurantRepository;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import java.util.List;
import java.util.stream.Collectors;

import static org.junit.Assert.*;

/**
 * Created by pvmeira on 20/06/17.
 */
@RunWith(MockitoJUnitRunner.class)
public class RestaurantServiceTest extends UtilDataTest {

    @InjectMocks
    private RestaurantService restaurantService;

    @Test
    public void getRestaurantsFromVoteListWhenListHaveRepeatedValues() {
        List<Vote> voteList = getListOfVotesFromTheSameStrawPoll();
        voteList.get(0).getRestaurant().setId(2l);
        Assert.assertEquals(this.restaurantService.getRestaurantsFromVoteList(voteList).size(), 2);
    }

    @Test
    public void getRestaurantsFromVoteListWhenListDontHaveRepeatedValues() {
        List<Vote> voteList = getListOfVotesFromTheSameStrawPoll();
        Assert.assertEquals(this.restaurantService.getRestaurantsFromVoteList(voteList).size(), 3);
    }

}