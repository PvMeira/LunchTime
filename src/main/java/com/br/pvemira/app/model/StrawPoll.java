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
    @Column(name = "ID_STRAWPOLL")
    private Long id;

    @Column(name = "NAME")
    private String name;


    @Column(name = "DATE")
    private LocalDate date;

    @Column(name = "NEW")
    private Boolean isNew;

    public StrawPoll() {
    }

    public StrawPoll(String name, LocalDate date,Boolean isNew) {
        this.name = name;
        this.date = date;
        this.isNew = isNew;
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


    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public Boolean getNew() {
        return isNew;
    }

    public void setNew(Boolean aNew) {
        isNew = aNew;
    }
}
