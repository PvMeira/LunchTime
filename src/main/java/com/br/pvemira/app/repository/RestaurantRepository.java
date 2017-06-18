package com.br.pvemira.app.repository;

import com.br.pvemira.app.model.Restaurant;
import org.springframework.data.repository.CrudRepository;

/**
 * Created by pvmeira on 17/06/17.
 */
public interface RestaurantRepository extends CrudRepository<Restaurant,Long> {
}
