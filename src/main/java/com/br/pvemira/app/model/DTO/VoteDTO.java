package com.br.pvemira.app.model.DTO;

/**
 * Created by pvmeira on 17/06/17.
 */
public class VoteDTO {

    private Long id;

    private String voterName;

    private String restaurantName;

    private String pollName;

    public VoteDTO(Long id, String voterName, String restaurantName, String pollName) {
        this.id = id;
        this.voterName = voterName;
        this.restaurantName = restaurantName;
        this.pollName = pollName;
    }

    public VoteDTO() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getVoterName() {
        return voterName;
    }

    public void setVoterName(String voterName) {
        this.voterName = voterName;
    }

    public String getRestaurantName() {
        return restaurantName;
    }

    public void setRestaurantName(String restaurantName) {
        this.restaurantName = restaurantName;
    }

    public String getPollName() {
        return pollName;
    }

    public void setPollName(String pollName) {
        this.pollName = pollName;
    }
}
