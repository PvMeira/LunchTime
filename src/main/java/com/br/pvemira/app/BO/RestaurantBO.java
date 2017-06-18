package com.br.pvemira.app.BO;

import com.br.pvemira.app.enumProject.LocationEnum;
import com.br.pvemira.app.model.DTO.RestaurantDTO;
import com.br.pvemira.app.model.Restaurant;
import com.br.pvemira.app.model.StrawPoll;
import com.br.pvemira.app.model.Vote;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by pvmeira on 17/06/17.
 */
public class RestaurantBO {

    public Restaurant transformRestaurantDTO2Restaurant(RestaurantDTO a) {
        return new Restaurant(a.getName(), LocationEnum.findById(a.getLocation()));
    }

    public List<RestaurantDTO> transformRestaurants2RestaurantsDTO(List<Restaurant> restaurants, StrawPoll strawPoll) {
        List<RestaurantDTO> restaurantDTOS = new ArrayList<>();
        restaurants.stream().forEach(r -> {
            RestaurantDTO dto;
            if (validateRestaurantDate(r, strawPoll)) {
                dto = new RestaurantDTO(r.getId(), r.getName(), r.getLocation().getId(), r.getLocation().getName());
                restaurantDTOS.add(dto);
            }

        });
        return restaurantDTOS;
    }

    public RestaurantDTO transformRestaurant2RestaurantDTO(Restaurant r) {
        return new RestaurantDTO(r.getId(), r.getName(), r.getLocation().getId(), r.getLocation().getName());
    }

    private Boolean validateRestaurantDate(Restaurant restaurant, StrawPoll strawPoll) {
        if (restaurant.getAddOnStrawPoll() != null) {
            long between = ChronoUnit.WEEKS.between(restaurant.getAddOnStrawPoll(), LocalDate.now());
            if (between > 7) return Boolean.TRUE;
            else {
                if (strawPoll.getRestaurantList().contains(restaurant)) {
                    return Boolean.TRUE;
                }
                return Boolean.FALSE;
            }

        } else {
            return Boolean.TRUE;
        }
    }
}
