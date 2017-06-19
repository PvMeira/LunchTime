package com.br.pvemira.app.resource;

import com.br.pvemira.app.model.DTO.StrawPollDTO;
import com.br.pvemira.app.model.StrawPoll;
import com.br.pvemira.app.model.Vote;
import com.br.pvemira.app.service.StrawPollServiceLocal;
import com.br.pvemira.app.service.VoteServiceLocal;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;
import java.util.List;

/**
 * Created by pvmeira on 17/06/17.
 */
@RestController
@RequestMapping("/app/strawPoll")
public class StrawPollResource {

    private final StrawPollServiceLocal strawPollService;
    private final VoteServiceLocal voteService;

    @Autowired
    public StrawPollResource(StrawPollServiceLocal strawPollService, VoteServiceLocal voteService) {
        this.strawPollService = strawPollService;
        this.voteService = voteService;
    }

    @RequestMapping(value = "/new/{name}", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<StrawPollDTO> newStrawPoll(@PathVariable String name) {
        this.strawPollService.newStrawPoll(LocalDate.now(), name);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @RequestMapping(value = "/newPollAvaliable", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<StrawPollDTO> newPollAvaliable() {
        if (this.strawPollService.isAvaliableToANewPoll()) {
            return new ResponseEntity<>(HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }
    @RequestMapping(value = "/avaliable", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<StrawPollDTO> avaliable() {
        if (this.strawPollService.isAvaliable()) {
            return new ResponseEntity<>(HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }


    @RequestMapping(value = "/getResultCurrentPoll", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public StrawPollDTO getResultFromCurrentPoll() {
        StrawPoll currentStrawPoll = this.strawPollService.findCurrentStrawPoll();
        if (currentStrawPoll == null) {
            return null;
        }
        List<Vote> voteList = this.voteService.findVotesbyStrawPollId(currentStrawPoll);
        return this.strawPollService.getResultFromCurrentPool(voteList);
    }
}
