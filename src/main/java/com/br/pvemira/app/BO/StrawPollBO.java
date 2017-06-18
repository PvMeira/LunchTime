package com.br.pvemira.app.BO;

import com.br.pvemira.app.model.DTO.RestaurantDTO;
import com.br.pvemira.app.model.DTO.StrawPollDTO;
import com.br.pvemira.app.model.Restaurant;
import com.br.pvemira.app.model.StrawPoll;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by pvmeira on 17/06/17.
 */
public class StrawPollBO {
    private RestaurantBO restaurantBO;

    public StrawPollBO() {
        this.restaurantBO = new RestaurantBO();
    }

    public Boolean restaurantExistInCurrentStrawPoll(List<Restaurant> restaurants, Restaurant restaurant) {
        for (Restaurant r : restaurants) {
            if (r.getId().equals(restaurant.getId()) && r.getAddOnStrawPoll() != null)
                return Boolean.TRUE;
        }
        return Boolean.FALSE;
    }

    public Boolean validateRestaurant(Restaurant restaurant) {
        if (restaurant.getAddOnStrawPoll() != null) {
            long between = ChronoUnit.WEEKS.between(restaurant.getAddOnStrawPoll(), LocalDate.now());
            if (between > 7) {
                return Boolean.TRUE;
            } else {
                return Boolean.FALSE;
            }

        } else {
            return Boolean.TRUE;
        }
    }

    public StrawPollDTO tranformStrawPoll2StrawPollSTO(StrawPoll strawPoll) {
        List<RestaurantDTO> restaurantDTOList = new ArrayList<>();
        strawPoll.getRestaurantList().stream().forEach(restaurant -> restaurantDTOList.add(this.restaurantBO.transformRestaurant2RestaurantDTO(restaurant)));
        return new StrawPollDTO(strawPoll.getId(), strawPoll.getName(), restaurantDTOList, strawPoll.getDate());
    }
}
