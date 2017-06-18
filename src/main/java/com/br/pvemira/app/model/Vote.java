package com.br.pvemira.app.model;

import javax.persistence.*;

/**
 * Created by pvmeira on 17/06/17.
 */
@Entity(name = "TB_VOTE")
@SequenceGenerator(name = "vote_generator", sequenceName = "vote_sequence", allocationSize = 1)
public class Vote {

    @Id
    @GeneratedValue(generator = "vote_generator")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "VOTER_ID")
    private Voter voter;

    @ManyToOne
    @JoinColumn(name = "RESTAURANT_ID")
    private Restaurant restaurant;

    @ManyToOne
    @JoinColumn(name = "STRAW_POLL_ID")
    private StrawPoll poll;


    public Vote(Voter voter, Restaurant restaurant, StrawPoll poll) {
        this.voter = voter;
        this.restaurant = restaurant;
        this.poll = poll;
    }

    public Vote() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Voter getVoter() {
        return voter;
    }

    public void setVoter(Voter voter) {
        this.voter = voter;
    }

    public Restaurant getRestaurant() {
        return restaurant;
    }

    public void setRestaurant(Restaurant restaurant) {
        this.restaurant = restaurant;
    }

    public StrawPoll getPoll() {
        return poll;
    }

    public void setPoll(StrawPoll poll) {
        this.poll = poll;
    }
}
