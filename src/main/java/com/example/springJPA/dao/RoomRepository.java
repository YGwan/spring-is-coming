package com.example.springJPA.dao;

import com.example.springJPA.entity.Room;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoomRepository extends JpaRepository<Room, Long> {

    Room findByNumber(int roomNumber);
}