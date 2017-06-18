package com.br.pvemira.app.model;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.Date;
import java.util.List;

/**
 * Created by pvmeira on 17/06/17.
 */
@Entity(name = "TB_STRAWPOLL")
@SequenceGenerator(name = "poll_generator", sequenceName = "poll_sequence", allocationSize = 1)
public class StrawPoll {

    @Id
    @GeneratedValue(generator = "poll_generator")
    @Column(name = "")
    private Long id;

    @Column(name = "NAME")
    private String name;

    @OneToMany
    private List<Restaurant> restaurantList;

    @Column(name = "DATE")
    private LocalDate date;

    public StrawPoll(String name, List<Restaurant> restaurantList, LocalDate date) {
        this.name = name;
        this.restaurantList = restaurantList;
        this.date = date;
    }

    public StrawPoll() {
    }

    public StrawPoll(String name, LocalDate date) {
        this.name = name;
        this.date = date;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Restaurant> getRestaurantList() {
        return restaurantList;
    }

    public void setRestaurantList(List<Restaurant> restaurantList) {
        this.restaurantList = restaurantList;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }
}
