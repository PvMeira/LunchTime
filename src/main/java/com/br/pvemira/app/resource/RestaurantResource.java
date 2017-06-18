package com.br.pvemira.app.resource;

import com.br.pvemira.app.model.DTO.RestaurantDTO;
import com.br.pvemira.app.model.StrawPoll;
import com.br.pvemira.app.service.RestaurantServiceLocal;
import com.br.pvemira.app.service.StrawPollServiceLocal;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * Created by pvmeira on 17/06/17.
 */
@RestController
@RequestMapping("/app/restaurant")
public class RestaurantResource {
    private final RestaurantServiceLocal restaurantService;
    private final StrawPollServiceLocal strawPollService;

    @Autowired
    public RestaurantResource(RestaurantServiceLocal restaurantService, StrawPollServiceLocal strawPollService) {
        this.restaurantService = restaurantService;
        this.strawPollService = strawPollService;
    }

    @RequestMapping(method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<RestaurantDTO> saveRestaurant(@RequestBody RestaurantDTO restaurantDTO) {
        this.restaurantService.saveRestaurant(restaurantDTO);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @RequestMapping(value = "/listAllAvaliable", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public List<RestaurantDTO> findAllRestaurantsAvaliable() {
        StrawPoll currentStrawPoll = this.strawPollService.findCurrentStrawPoll();
        return this.restaurantService.findAllRestaurantsAvaliable(currentStrawPoll);
    }

}
