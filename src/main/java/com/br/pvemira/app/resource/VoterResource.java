package com.br.pvemira.app.resource;

import com.br.pvemira.app.model.DTO.VoterDTO;
import com.br.pvemira.app.service.VoterServiceLocal;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by pvmeira on 17/06/17.
 */
@RestController
@RequestMapping("/app/voter")
public class VoterResource {
    private final VoterServiceLocal voterService;

    public VoterResource(VoterServiceLocal voterService) {
        this.voterService = voterService;
    }

    @RequestMapping(method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<VoterDTO> saveVoter(@RequestBody VoterDTO voterDTO) {
        Boolean voterAlreadyExist = this.voterService.addVoter(voterDTO);
        if(voterAlreadyExist){
            return new ResponseEntity<>(HttpStatus.OK);
        }else{
           return new ResponseEntity<>(HttpStatus.NOT_ACCEPTABLE);
        }
    }
}
