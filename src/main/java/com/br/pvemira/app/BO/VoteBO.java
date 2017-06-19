package com.br.pvemira.app.BO;

import com.br.pvemira.app.model.Restaurant;
import com.br.pvemira.app.model.Vote;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by pvmeira on 17/06/17.
 */
public class VoteBO {

    public Boolean validateRestaurantIsOnCurrentPoll(List<Vote> votes, Restaurant restaurant) {
        List<Restaurant> restaurantList = votes.stream().map(vote -> vote.getRestaurant()).distinct().collect(Collectors.toList());
        return restaurantList.contains(restaurant);
    }
}
