package com.br.pvemira.app.model.DTO;


/**
 * Created by pvmeira on 17/06/17.
 */
public class RestaurantDTO {

    private Long id;

    private String name;

    private Integer locationId;

    private String locationName;

    private Integer totalVotes;

    public RestaurantDTO(Long id, String name, Integer locationId, String locationName) {
        this.id = id;
        this.name = name;
        this.locationId = locationId;
        this.locationName = locationName;
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

    public Integer getLocation() {
        return locationId;
    }

    public void setLocation(Integer location) {
        this.locationId = location;
    }

    public String getLocationName() {
        return locationName;
    }

    public void setLocationName(String locationName) {
        this.locationName = locationName;
    }

    public Integer getTotalVotes() {
        return totalVotes;
    }

    public void setTotalVotes(Integer totalVotes) {
        this.totalVotes = totalVotes;
    }
}
