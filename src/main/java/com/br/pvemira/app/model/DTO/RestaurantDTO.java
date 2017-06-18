package com.br.pvemira.app.model.DTO;


/**
 * Created by pvmeira on 17/06/17.
 */
public class RestaurantDTO {

    private Long id;

    private String name;

    private String location;

    private Integer totalVotes;

    public RestaurantDTO(Long id, String name,String location) {
        this.id = id;
        this.name = name;
        this.location = location;
        this.totalVotes = 0;
    }

    public RestaurantDTO() {
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

    public Integer getTotalVotes() {
        return totalVotes;
    }

    public void setTotalVotes(Integer totalVotes) {
        this.totalVotes = totalVotes;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }
}
