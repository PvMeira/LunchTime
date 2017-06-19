package com.br.pvemira.app.service.serviceImpl;

import com.br.pvemira.app.BO.VoterBO;
import com.br.pvemira.app.model.DTO.VoterDTO;
import com.br.pvemira.app.model.Voter;
import com.br.pvemira.app.repository.VoterRepository;
import com.br.pvemira.app.service.VoterServiceLocal;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by pvmeira on 17/06/17.
 */
@Service
public class VoterService implements VoterServiceLocal {
    private final VoterRepository voterRepository;
    private VoterBO voterBO;

    @Autowired
    public VoterService(VoterRepository voterRepository) {
        this.voterRepository = voterRepository;
        this.voterBO = new VoterBO();
    }

    /**
     * Add a new Voter to the Database
     *
     * @param voterDTO
     * @return Boolean.TRUE if everthing is okay, return Boolean.FALSE if the email
     * is already in use or is blank
     */
    @Override
    public Boolean addVoter(VoterDTO voterDTO) {
        Voter voter = this.voterBO.transformVoterDTO2Voter(voterDTO);
        try {
            this.voterRepository.save(voter);
            return Boolean.TRUE;
        } catch (Exception e) {
            e.printStackTrace();
            return Boolean.FALSE;
        }
    }

    /**
     * Find The Voter base on his email
     *
     * @param email the email that need to be found
     * @return the Voter that has the email that was passed before
     */
    @Override
    public Voter findByEmail(String email) {
        return this.voterRepository.findByEmail(email);
    }

    /**
     * Do the update to the VoteDate of the pass Voter, so that can be a controll of how many
     * time the same voter can vote.
     *
     * @param date  the LocalDate that going to be atach to the Voter
     * @param voter the voter that going to have his LastVoted date update
     */
    @Override
    public void updateVoteDateFromVoter(LocalDate date, Voter voter) {
        voter.setLastVoted(date);
        this.voterRepository.save(voter);
    }

    /**
     * Return the list with all Voters that are save on the aplication DataBase
     *
     * @return
     */
    @Override
    public List<VoterDTO> listAllVoters() {
        List<Voter> voters = (List<Voter>) this.voterRepository.findAll();
        return voters.stream().map(voter -> this.voterBO.transformVoter2VoterDTO(voter)).collect(Collectors.toList());
    }
}
