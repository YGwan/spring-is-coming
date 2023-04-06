package com.example.springJPA.entity;

import javax.persistence.*;

@Entity
public class Squash {

    @GeneratedValue
    @Id
    private Long id;

    private Integer roomNumber;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "user_id")
    private User user;

    public Squash(Long id, Integer roomNumber, User user) {
        this.id = id;
        this.roomNumber = roomNumber;
        this.user = user;
    }

    public Squash() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getRoomNumber() {
        return roomNumber;
    }

    public void setRoomNumber(Integer roomNumber) {
        this.roomNumber = roomNumber;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
