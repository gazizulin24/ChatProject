package org.gazizulin.ChatProject.models;

import jakarta.persistence.*;
import jakarta.validation.constraints.Size;
import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.Fetch;
import org.hibernate.validator.constraints.UniqueElements;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Timur Gazizulin
 */
@Entity
@Table(name="Person")
public class User {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;


    @Column(name="name")
    private String username;

    @Column(name="password")
    @Size(min=8, message = "Password length must be min 8 characters")
    private String password;


    @Column(name="role")
    private String role;

    @Column(name="can_chat")
    private boolean canChat;


    @OneToMany(mappedBy = "sender")
    @Cascade(org.hibernate.annotations.CascadeType.SAVE_UPDATE)
    private List<Message> messages;


    public User(){}


    public User(String username, String password, String role, boolean canChat) {
        this.username = username;
        this.password = password;
        this.role = role;
        this.canChat = canChat;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String name) {
        this.username = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public boolean isCanChat() {
        return canChat;
    }

    public void setCanChat(boolean canChat) {
        this.canChat = canChat;
    }

    public List<Message> getMessages() {
        if (messages == null){
            return new ArrayList<Message>();
        }
        return messages;
    }

    public void setMessages(List<Message> messages) {
        this.messages = messages;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", name='" + username + '\'' +
                ", password='" + password + '\'' +
                ", role='" + role + '\'' +
                ", canChat=" + canChat +
                '}';
    }
}

