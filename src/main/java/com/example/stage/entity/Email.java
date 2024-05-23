package com.example.stage.entity;

//import org.hibernate.validator.constraints.Length;

import javax.persistence.*;
import java.io.File;
import java.util.Date;
import java.util.List;


@Entity
@Table(name = "email")
public class Email {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id ;

    @ManyToOne
    @JoinColumn(name = "senderid")
    private Candidat sender ;





    private String subject ;

    private String message ;





    private boolean isRead ;
    private boolean trash ;

    private Date time ;

    public Email() {
    }

    public Email(long id, Candidat sender, String subject, String message,  boolean isRead, boolean trash, Date time) {
        this.id = id;
        this.sender = sender;
        this.subject = subject;
        this.message = message;
        this.isRead = isRead;
        this.trash = trash;
        this.time = time;
      //  this.sender=candidat;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Candidat getSender() {
        return sender;
    }

    public void setSender(Candidat sender) {
        this.sender = sender;
    }


    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }


    public boolean isRead() {
        return isRead;
    }

    public void setRead(boolean read) {
        isRead = read;
    }


    public Date getTime() {
        return time;
    }

    public void setTime(Date time) {
        this.time = time;
    }

    public boolean isTrash() {
        return trash;
    }

    public void setTrash(boolean trash) {
        this.trash = trash;
    }

    @Override
    public String toString() {
        return "Email{" +
                "id=" + id +
                ", sender=" + sender +
                ", subject='" + subject + '\'' +
                ", message='" + message + '\'' +
                ", isRead=" + isRead +
                ", time=" + time +
                '}';
    }


}
