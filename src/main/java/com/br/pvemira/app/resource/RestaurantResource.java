package com.br.pvemira.app.resource;

import com.br.pvemira.app.model.DTO.RestaurantDTO;
import com.br.pvemira.app.model.StrawPoll;
import com.br.pvemira.app.model.Vote;
import com.br.pvemira.app.service.RestaurantServiceLocal;
import com.br.pvemira.app.service.StrawPollServiceLocal;
import com.br.pvemira.app.service.VoteServiceLocal;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Created by pvmeira on 17/06/17.
 */
@RestController
@RequestMapping("/app/restaurant")
public class RestaurantResource {
    private final RestaurantServiceLocal restaurantService;
    private final StrawPollServiceLocal strawPollService;
    private final VoteServiceLocal voteService;

    @Autowired
    public RestaurantResource(RestaurantServiceLocal restaurantService, StrawPollServiceLocal strawPollService, VoteServiceLocal voteService) {
        this.restaurantService = restaurantService;
        this.strawPollService = strawPollService;
        this.voteService = voteService;
    }

    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<RestaurantDTO> saveRestaurant(@RequestBody RestaurantDTO restaurantDTO) {
        this.restaurantService.saveRestaurant(restaurantDTO);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @RequestMapping(value = "/delete/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<RestaurantDTO> deleteRestaurant(@PathVariable Long id) {
        this.restaurantService.delete(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @RequestMapping(value = "/listAllAvaliable", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public List<RestaurantDTO> findAllRestaurantsAvaliable() {
        StrawPoll currentStrawPoll = this.strawPollService.findCurrentStrawPoll();
        List<Vote> list = this.voteService.findVotesbyStrawPollId(currentStrawPoll);
        return this.restaurantService.findAllRestaurantsAvaliable(currentStrawPoll, list);
    }

    @RequestMapping(value = "/listAll", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public List<RestaurantDTO> findAll() {
        return this.restaurantService.listAll();
    }

}
