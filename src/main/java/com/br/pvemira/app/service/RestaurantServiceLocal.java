package com.br.pvemira.app.service;

import com.br.pvemira.app.model.DTO.RestaurantDTO;
import com.br.pvemira.app.model.Restaurant;
import com.br.pvemira.app.model.StrawPoll;
import com.br.pvemira.app.model.Vote;

import java.time.LocalDate;
import java.util.List;

/**
 * Created by pvmeira on 17/06/17.
 */
public interface RestaurantServiceLocal {

    void saveRestaurant(RestaurantDTO restaurantDTO);

    Restaurant findByid(Long id);

    void addStrawPollDateToRestaurant(Restaurant restaurant, LocalDate date);

    List<RestaurantDTO> findAllRestaurantsAvaliable(StrawPoll strawPol, List<Vote> votesbyStrawPollId);

    void addVoteToRestaurant(Restaurant restaurant, Vote vote);

    void delete(Long id);

    List<RestaurantDTO> listAll();

    List<Restaurant> getRestaurantsFromVoteList(List<Vote> votes);
}
