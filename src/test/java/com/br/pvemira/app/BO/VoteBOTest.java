package com.br.pvemira.app.BO;

import com.br.pvemira.app.UtilDataTest;
import com.br.pvemira.app.model.Restaurant;
import com.br.pvemira.app.model.StrawPoll;
import com.br.pvemira.app.model.Vote;
import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by pvmeira on 19/06/17.
 */
public class VoteBOTest extends UtilDataTest {

    @Test
    public void validateRestaurantIsOnCurrentPollContains() throws Exception {
        VoteBO bo = new VoteBO();
        List<Vote> list = new ArrayList<>();
        list.add(new Vote(getNewVoter(), getNewRestaurant(), new StrawPoll()));
        list.add(new Vote(getNewVoter(), new Restaurant("Teste", "Teste"), new StrawPoll()));
        list.add(new Vote(getNewVoter(), getNewRestaurant(), new StrawPoll()));
        Restaurant restaurant = list.get(2).getRestaurant();

        Assert.assertTrue(bo.validateRestaurantIsOnCurrentPoll(list, restaurant));
    }

    @Test
    public void validateRestaurantIsOnCurrentPollDoesNotContains() throws Exception {
        VoteBO bo = new VoteBO();
        List<Vote> list = new ArrayList<>();
        list.add(new Vote(getNewVoter(), new Restaurant("Teste", "Teste"), new StrawPoll()));
        list.add(new Vote(getNewVoter(), getNewRestaurant(), new StrawPoll()));
        Restaurant restaurant = getNewRestaurant();

        Assert.assertFalse(bo.validateRestaurantIsOnCurrentPoll(list, restaurant));
    }


}