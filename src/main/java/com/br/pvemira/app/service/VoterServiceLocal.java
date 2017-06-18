package com.br.pvemira.app.service;

import com.br.pvemira.app.model.DTO.VoterDTO;
import com.br.pvemira.app.model.Voter;

import java.time.LocalDate;
import java.util.List;

/**
 * Created by pvmeira on 17/06/17.
 */
public interface VoterServiceLocal {
    Boolean addVoter(VoterDTO voter);
    Voter findByEmail(String email);
    void updateVoteDateFromVoter(LocalDate date, Voter voter);
    List<VoterDTO> listAllVoters();
}
