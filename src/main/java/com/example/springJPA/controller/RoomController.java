package com.example.springJPA.controller;

import com.example.springJPA.dao.RoomRepository;
import com.example.springJPA.dao.UserRepository;
import com.example.springJPA.dto.ReleaseRoomRequest;
import com.example.springJPA.dto.ReserveRoomRequest;
import com.example.springJPA.entity.User;
import com.example.springJPA.service.RoomService;
import com.example.springMVC.exception.UserException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/room")
public class RoomController {

    private final RoomService roomService;
    private final RoomRepository roomRepository;
    private final UserRepository userRepository;

    public RoomController(RoomService roomService, RoomRepository roomRepository, UserRepository userRepository) {
        this.roomService = roomService;
        this.roomRepository = roomRepository;
        this.userRepository = userRepository;
    }

    @PostMapping("/{roomNumber}/dispatch")
    public String reserveRoom(@RequestBody ReserveRoomRequest request, @PathVariable int roomNumber) {
        User user = userRepository.findByUsername(request.getUsername()).orElseThrow(() -> new UserException("유저 정보가 존재하지 않습니다."));
        if (user.isInRoom()) {
            throw new UserException("해당 유저는 예약 정보가 존재합니다.");
        }
        user.setRoom(roomRepository.findByNumber(roomNumber));
        userRepository.save(user);
        return "예약 완료";
    }

    @GetMapping("/{roomNumber}")
    public List<User> inquiryRoomUsers(@PathVariable int roomNumber) {
//        userRepository.findByRoom(roomRepository.findByNumber(roomNumber));
        return userRepository.findByRoomNumber(roomNumber);
    }

    @PostMapping("/{roomNumber}/release")
    public String releaseRoom(@RequestBody ReleaseRoomRequest request, @PathVariable int roomNumber) {
        User user = userRepository.findByUsernameAndRoomNumber(request.getUsername(), roomNumber).orElseThrow(() -> new UserException("유저 정보가 존재하지 않습니다."));
        user.setRoom(null);
        userRepository.save(user);
        return "예약 취소 완료";
    }

    @ExceptionHandler(UserException.class)
    public ResponseEntity<String> userExceptionHandler(UserException e) {
        return ResponseEntity.badRequest().body(e.getMessage());
    }
}
