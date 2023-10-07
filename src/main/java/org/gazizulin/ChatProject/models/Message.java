package org.gazizulin.ChatProject.models;

import jakarta.persistence.*;

import java.util.Date;

/**
 * @author Timur Gazizulin
 */
@Entity
@Table(name="message")
public class Message {

    @Id
    @Column(name="id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;


    @ManyToOne
    @JoinColumn(name = "sender_id",
    referencedColumnName = "id")
    private User sender;

    @Column(name="text")
    private String text;

    @Column(name="date")
    @Temporal(TemporalType.DATE)
    private Date date;


    public Message(){}

    public Message(User sender, String text) {
        this.sender = sender;
        this.text = text;
        this.date = new Date();
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public User getSender() {
        return sender;
    }

    public void setSender(User sender) {
        this.sender = sender;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    @Override
    public String toString() {
        return "Message{" +
                "id=" + id +
                ", sender=" + sender.getUsername() +
                ", text='" + text + '\'' +
                ", date=" + date +
                '}';
    }
}
