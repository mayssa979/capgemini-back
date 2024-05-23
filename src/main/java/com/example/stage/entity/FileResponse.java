package com.example.stage.entity;

import javax.persistence.CascadeType;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;

public class FileResponse {

    private int id;

    private String type;

    private String name;

  //  private Candidat candidat;

    public FileResponse() {

    }

    public FileResponse(int id, String type, String name) {
        super();
        this.id = id;
        this.type = type;
        this.name = name;
      //  this.candidat = candidat;
    }

   /* public Candidat getCandidat() {
        return candidat;
    }

    public void setCandidat(Candidat candidat) {
        this.candidat = candidat;
    }*/

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

}
