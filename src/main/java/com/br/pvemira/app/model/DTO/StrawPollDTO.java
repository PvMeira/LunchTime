package com.br.pvemira.app.model.DTO;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;

/**
 * Created by pvmeira on 17/06/17.
 */
public class StrawPollDTO {

    private Long id;

    private String name;

    private List<RestaurantDTO> restaurantList;

    private Boolean isTime;

    @JsonFormat(pattern = "dd-MM-yyyy")
    private LocalDate date;

    public StrawPollDTO() {
    }

    public StrawPollDTO(Long id, String name, List<RestaurantDTO> restaurantList, LocalDate date) {
        this.id = id;
        this.name = name;
        this.restaurantList = restaurantList;
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

    public List<RestaurantDTO> getRestaurantList() {
        return restaurantList;
    }

    public void setRestaurantList(List<RestaurantDTO> restaurantList) {
        this.restaurantList = restaurantList;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public Boolean getTime() {
        return isTime;
    }

    public void setTime(Boolean time) {
        isTime = time;
    }
}
