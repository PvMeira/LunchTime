package com.br.pvemira.app.service.serviceImpl;

import com.br.pvemira.app.BO.VoterBO;
import com.br.pvemira.app.model.DTO.VoterDTO;
import com.br.pvemira.app.model.Voter;
import com.br.pvemira.app.repository.VoterRepository;
import com.br.pvemira.app.service.VoterServiceLocal;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

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

    @Override
    public Boolean addVoter(VoterDTO voterDTO) {
        Voter voter1 = this.voterBO.transformVoterDTO2Voter(voterDTO);
        try {
            this.voterRepository.save(voter1);
            return Boolean.TRUE;
        } catch (Exception e) {
            e.printStackTrace();
            return Boolean.FALSE;
        }
    }

    @Override
    public Voter findByEmail(String email) {
        return this.voterRepository.findByEmail(email);
    }

    @Override
    public void updateVoteDateFromVoter(LocalDate date, Voter voter) {
        voter.setLastVoted(date);
        this.voterRepository.save(voter);
    }

    @Override
    public List<VoterDTO> listAllVoters() {
        List<Voter> voters = (List<Voter>) this.voterRepository.findAll();
        List<VoterDTO> dtoList = new ArrayList<>();
        voters.stream().forEach(voter -> {
            dtoList.add(this.voterBO.transformVoter2VoterDTO(voter));
        });
        return dtoList;
    }
}
