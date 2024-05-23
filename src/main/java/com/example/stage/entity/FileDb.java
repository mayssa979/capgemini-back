package com.example.stage.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.hibernate.annotations.GenericGenerator;

import javax.annotation.sql.DataSourceDefinition;
import javax.persistence.*;
import javax.transaction.Transactional;

@Entity
@Transactional
@Table(name ="files")
public class FileDb {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
   // @GeneratedValue(generator="system-uuid")
   // @GenericGenerator(name="system-uuid", strategy = "uuid")
    @Column(name = "id")
    private int id;

    @Column(name="name")
    private String name;

    @Column(name="type")
    private String type;


    @Lob
    @Column(name="data")
    private byte[] data;

    public FileDb() {

    }


    public FileDb(String name, String type, byte[] data) {
        super();
      //  this.id = id;
        this.name = name;
        this.type = type;
        this.data = data;
    }



    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public byte[] getData() {
        return data;
    }

    public void setData(byte[] data) {
        this.data = data;
    }

}
