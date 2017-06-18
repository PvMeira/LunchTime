package com.br.pvemira.app.enumProject;

/**
 * Created by pvmeira on 17/06/17.
 */
public enum LocationEnum {

    BENTO(1, "Bento"),
    PUC(2, "Puc"),
    IPIRANGA(3, "Ipiranga");

    private Integer id;
    private String name;

    LocationEnum(Integer id, String name) {
        this.id = id;
        this.name = name;
    }

    public Integer getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public static LocationEnum findById(Integer id) {
        for (LocationEnum e : values()) {
            if (e.getId().equals(id))
                return e;
        }
        return null;
    }
}
