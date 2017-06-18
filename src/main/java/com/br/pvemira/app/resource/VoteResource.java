package com.br.pvemira.app.resource;

import com.br.pvemira.app.model.Vote;
import com.br.pvemira.app.service.VoteServiceLocal;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * Created by pvmeira on 17/06/17.
 */
@RestController
@RequestMapping("/app/vote")
public class VoteResource {

    private final VoteServiceLocal voteService;

    public VoteResource(VoteServiceLocal voteService) {
        this.voteService = voteService;
    }

    @RequestMapping(value = "/new/{email}/{idRestaurant}", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Vote> vote(@PathVariable("email") String email, @PathVariable("idRestaurant") Long idRestaurant) {
        Boolean vote = this.voteService.vote(email, idRestaurant);
        if (vote) {
            return new ResponseEntity<>(HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }
}
