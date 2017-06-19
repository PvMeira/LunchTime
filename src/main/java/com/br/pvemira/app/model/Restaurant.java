package com.br.pvemira.app.model;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.List;

/**
 * Created by pvmeira on 17/06/17.
 */
@Entity(name = "TB_RESTAURANT")
@SequenceGenerator(name = "restaurant_generator", sequenceName = "restaurant_sequence", allocationSize = 1)
public class Restaurant {

    @Id
    @GeneratedValue(generator = "restaurant_generator")
    @Column(name = "ID_RESTAURANT")
    private Long id;

    @OneToMany
    private List<Vote> voteList;

    @Column(name = "NAME", nullable = false, length = 200)
    private String name;

    @Column(name = "LOCATION")
    private String location;


    private LocalDate addOnStrawPoll;

    public Restaurant() {
    }

    public Restaurant(String name, String location) {
        this.name = name;
        this.location = location;
    }

    public Restaurant(String name, String location, LocalDate addOnStrawPoll) {
        this.name = name;
        this.location = location;
        this.addOnStrawPoll = addOnStrawPoll;
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


    public LocalDate getAddOnStrawPoll() {
        return addOnStrawPoll;
    }

    public void setAddOnStrawPoll(LocalDate addOnStrawPoll) {
        this.addOnStrawPoll = addOnStrawPoll;
    }

    public List<Vote> getVoteList() {
        return voteList;
    }

    public void setVoteList(List<Vote> voteList) {
        this.voteList = voteList;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }
}
