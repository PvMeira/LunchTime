package com.br.pvemira.app.BO;

import com.br.pvemira.app.model.DTO.RestaurantDTO;
import com.br.pvemira.app.model.Restaurant;
import com.br.pvemira.app.model.StrawPoll;
import com.br.pvemira.app.util.TimeUtil;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by pvmeira on 17/06/17.
 */
public class RestaurantBO {

    public Restaurant transformRestaurantDTO2Restaurant(RestaurantDTO a) {
        return new Restaurant(a.getName(), a.getLocation());
    }

    public List<RestaurantDTO> transformRestaurants2RestaurantsDTO(List<Restaurant> restaurants, List<Restaurant> strawPollRestaurants) {
        List<RestaurantDTO> restaurantDTOS = new ArrayList<>();
        restaurants.stream().forEach(r -> {
            RestaurantDTO dto;
            if (validateRestaurantDate(r, strawPollRestaurants)) {
                dto = new RestaurantDTO(r.getId(), r.getName(), r.getLocation());
                restaurantDTOS.add(dto);
            }

        });
        return restaurantDTOS;
    }

    public RestaurantDTO transformRestaurant2RestaurantDTO(Restaurant r) {
        return new RestaurantDTO(r.getId(), r.getName(), r.getLocation());
    }

    private Boolean validateRestaurantDate(Restaurant restaurant, List<Restaurant> strawPollRestaurants) {
        if (restaurant.getAddOnStrawPoll() != null) {
            if (TimeUtil.validRestaurantForNewPoll(restaurant.getAddOnStrawPoll())) return Boolean.TRUE;
            else {
                if (strawPollRestaurants.contains(restaurant)) {
                    return Boolean.TRUE;
                }
                return Boolean.FALSE;
            }

        } else {
            return Boolean.TRUE;
        }
    }
}
