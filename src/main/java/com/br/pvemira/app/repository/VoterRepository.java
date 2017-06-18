package com.br.pvemira.app.repository;

import com.br.pvemira.app.model.Voter;
import org.springframework.data.repository.CrudRepository;

/**
 * Created by pvmeira on 17/06/17.
 */
public interface VoterRepository extends CrudRepository<Voter, Long> {
    Voter findByEmail(String email);
}
