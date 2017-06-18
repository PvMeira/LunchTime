package com.br.pvemira.app.model;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.Date;
import java.util.List;

/**
 * Created by pvmeira on 17/06/17.
 */
@Entity(name = "TB_VOTER")
@SequenceGenerator(name = "voter_generator", sequenceName = "voter_sequence", allocationSize = 1)
public class Voter {

    @Id
    @GeneratedValue(generator = "voter_generator")
    private Long id;

    @Column(name = "EMAIL", nullable = false, length = 100,unique = true)
    private String email;

    @Column(name = "NAME", nullable = false, length = 150)
    private String name;

    @OneToMany(mappedBy = "id", fetch = FetchType.EAGER)
    private List<Vote> voteList;

    @Column(name = "LAST_VOTED")
    private LocalDate lastVoted;


    public Voter() {
    }

    public Voter(Long id, String email, String name, List<Vote> voteList) {
        this.id = id;
        this.email = email;
        this.name = name;
        this.voteList = voteList;
    }

    public Voter(String email, String name) {
        this.email = email;
        this.name = name;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Vote> getVoteList() {
        return voteList;
    }

    public void setVoteList(List<Vote> voteList) {
        this.voteList = voteList;
    }

    public LocalDate getLastVoted() {
        return lastVoted;
    }

    public void setLastVoted(LocalDate lastVoted) {
        this.lastVoted = lastVoted;
    }
}
