package com.br.pvemira.app.service.serviceImpl;

import com.br.pvemira.app.BO.RestaurantBO;
import com.br.pvemira.app.model.DTO.RestaurantDTO;
import com.br.pvemira.app.model.Restaurant;
import com.br.pvemira.app.model.StrawPoll;
import com.br.pvemira.app.model.Vote;
import com.br.pvemira.app.repository.RestaurantRepository;
import com.br.pvemira.app.service.RestaurantServiceLocal;
import com.br.pvemira.app.service.StrawPollServiceLocal;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

/**
 * Created by pvmeira on 17/06/17.
 */
@Service
public class RestaurantService implements RestaurantServiceLocal {

    private final RestaurantRepository restaurantRepository;
    private RestaurantBO restaurantBO;

    @Autowired
    public RestaurantService(RestaurantRepository restaurantRepository) {
        this.restaurantRepository = restaurantRepository;
        this.restaurantBO = new RestaurantBO();
    }

    @Override
    public void saveRestaurant(RestaurantDTO restaurantDTO) {
        Restaurant restaurant = this.restaurantBO.transformRestaurantDTO2Restaurant(restaurantDTO);
        this.restaurantRepository.save(restaurant);
    }

    @Override
    public Restaurant findByid(Long id) {
        return this.restaurantRepository.findOne(id);
    }


    @Override
    public void addStrawPollDateToRestaurant(Restaurant restaurant, LocalDate date) {
        restaurant.setAddOnStrawPoll(date);
        this.restaurantRepository.save(restaurant);
    }

    @Override
    public List<RestaurantDTO> findAllRestaurantsAvaliable(StrawPoll strawPoll) {
        List<Restaurant> restaurants = (List<Restaurant>) this.restaurantRepository.findAll();
        List<RestaurantDTO> restaurantDTOS = this.restaurantBO.transformRestaurants2RestaurantsDTO(restaurants, strawPoll);
        return restaurantDTOS;
    }

    @Override
    public void addVoteToRestaurant(Restaurant restaurant, Vote vote) {
        restaurant.getVoteList().add(vote);
        this.restaurantRepository.save(restaurant);
    }
}
