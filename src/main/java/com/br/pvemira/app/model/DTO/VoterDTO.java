package com.br.pvemira.app.model.DTO;

/**
 * Created by pvmeira on 17/06/17.
 */
public class VoterDTO {

    private Long id;

    private String email;

    private String name;


    public VoterDTO(Long id, String email, String name) {
        this.id = id;
        this.email = email;
        this.name = name;
    }

    public VoterDTO() {
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
}
