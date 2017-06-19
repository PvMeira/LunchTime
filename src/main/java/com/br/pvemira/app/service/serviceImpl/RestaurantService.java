package com.br.pvemira.app.service.serviceImpl;

import com.br.pvemira.app.BO.RestaurantBO;
import com.br.pvemira.app.model.DTO.RestaurantDTO;
import com.br.pvemira.app.model.Restaurant;
import com.br.pvemira.app.model.StrawPoll;
import com.br.pvemira.app.model.Vote;
import com.br.pvemira.app.repository.RestaurantRepository;
import com.br.pvemira.app.service.RestaurantServiceLocal;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

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

    /**
     * Save a new restaurant to the aplication DataBase
     *
     * @param restaurantDTO the DTO that going to be converted and then save on the database
     */
    @Override
    public void saveRestaurant(RestaurantDTO restaurantDTO) {
        Restaurant restaurant = this.restaurantBO.transformRestaurantDTO2Restaurant(restaurantDTO);
        this.restaurantRepository.save(restaurant);
    }

    /**
     * Find the Current Restaurant by his own ID
     *
     * @param id
     * @return Return the Restaurant that was found with the given ID
     */
    @Override
    public Restaurant findByid(Long id) {
        return this.restaurantRepository.findOne(id);
    }

    /**
     * Add a LocalDate to the Restaurant that was pass by param, so that the same restaurant
     * has his own controll of when was the last time that he was vonted
     *
     * @param restaurant the Restaurant that id going to have his AddOnStrawPoll updated
     * @param date       the LocalDate that going to be add to the given Restaurant
     */
    @Override
    public void addStrawPollDateToRestaurant(Restaurant restaurant, LocalDate date) {
        restaurant.setAddOnStrawPoll(date);
        this.restaurantRepository.save(restaurant);
    }

    /**
     * Find all restaurant that follow the rules passed by the histories,
     * if the restaurants are in the current StraePoll, they will be added on the return list.
     * But if they dont belong to the current poll,but their last date on a poll is more than
     * the dats especify on the histories,they also will be added on the return list.
     * Otherwise they will not be add to the return list
     *
     * @param strawPoll the current strawPoll
     * @param votes     the List of votes fo the current strawPoll
     * @return
     */
    @Override
    public List<RestaurantDTO> findAllRestaurantsAvaliable(StrawPoll strawPoll, List<Vote> votes) {
        List<Restaurant> restaurants = (List<Restaurant>) this.restaurantRepository.findAll();
        return this.restaurantBO.transformRestaurants2RestaurantsDTO(restaurants, this.getRestaurantsFromVoteList(votes));
    }

    /**
     * Add a vote to the Current restaurant
     *
     * @param restaurant the restaurant that is going to have a new vote
     * @param vote       the vote that's is going to be add on the restaurant
     */
    @Override
    public void addVoteToRestaurant(Restaurant restaurant, Vote vote) {
        restaurant.getVoteList().add(vote);
        this.restaurantRepository.save(restaurant);
    }

    /**
     * Delete the Restaurant by his own ID
     *
     * @param id the ID of the desire restaurant to be deleted
     */
    @Override
    public void delete(Long id) {
        this.restaurantRepository.delete(id);
    }

    /**
     * List All restaurants that exist on the application DataBase
     *
     * @return
     */
    @Override
    public List<RestaurantDTO> listAll() {
        List<Restaurant> restaurants = (List<Restaurant>) this.restaurantRepository.findAll();
        return restaurants.stream().map(restaurant -> this.restaurantBO.transformRestaurant2RestaurantDTO(restaurant)).collect(Collectors.toList());
    }

    /**
     * Return a list with all the Restaurants of the List<Vote> passed
     *
     * @param votes the list of votes that contains the restaurants that are going to
     *              be add on the return list
     * @return
     */
    @Override
    public List<Restaurant> getRestaurantsFromVoteList(List<Vote> votes) {
        return votes.stream().map(vote -> vote.getRestaurant()).distinct().collect(Collectors.toList());
    }

}
