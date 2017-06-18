package com.br.pvemira.app.repository;

import com.br.pvemira.app.model.StrawPoll;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.time.LocalDate;
import java.util.List;

/**
 * Created by pvmeira on 17/06/17.
 */
public interface StrawPollRepository extends CrudRepository<StrawPoll, Long> {
    @Query(nativeQuery = true,value = "SELECT * FROM TB_STRAWPOLL ORDER BY DATE DESC FETCH first 10 rows only")
    List<StrawPoll> findAllStrawPolls();
}
