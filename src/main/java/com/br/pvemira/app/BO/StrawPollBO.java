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
        return new StrawPollDTO(strawPoll.getId(), strawPoll.getName(), strawPoll.getDate());
    }

    public List<RestaurantDTO> tranformRestaurant2RestaurantDTO(List<Restaurant> restaurants) {
        List<RestaurantDTO> restaurantDTOList = new ArrayList<>();
        restaurants.stream().forEach(restaurant -> restaurantDTOList.add(this.restaurantBO.transformRestaurant2RestaurantDTO(restaurant)));
        return restaurantDTOList;
    }
}
