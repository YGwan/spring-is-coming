package com.example.springJPA.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Room {

    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private Long id;

    private Integer roomNumber;

    private boolean rsvStatus;

    private String usedUsername;

    public Room() {
    }

    public Room(Integer roomNumber) {
        this.roomNumber = roomNumber;
    }

    public Room(Integer roomNumber, boolean rsvStatus, String usedUsername) {
        this.roomNumber = roomNumber;
        this.rsvStatus = rsvStatus;
        this.usedUsername = usedUsername;
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

    public boolean isRsvStatus() {
        return rsvStatus;
    }

    public void setRsvStatus(boolean rsvStatus) {
        this.rsvStatus = rsvStatus;
    }

    public String getUsedUsername() {
        return usedUsername;
    }

    public void setUsedUsername(String usedUsername) {
        this.usedUsername = usedUsername;
    }
}
