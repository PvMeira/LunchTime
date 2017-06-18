package com.br.pvemira.app.repository;

import com.br.pvemira.app.model.StrawPoll;
import com.br.pvemira.app.model.Vote;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

/**
 * Created by pvmeira on 17/06/17.
 */
public interface VoteRepository extends CrudRepository<Vote, Long> {
    List<Vote> findVotesByPollEquals(StrawPoll poll);
}
