package com.example.springJPA.dao;

import com.example.springJPA.entity.Room;
import com.example.springJPA.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {

    Optional<User> findById(Long id);

    List<User> findAll();

    void deleteById(Long id);

    List<User> findByRoom(Room room);

    List<User> findByRoomNumber(int number);

    Optional<User> findByUsername(String username);

    Optional<User> findByUsernameAndRoomNumber(String username, int number);

    /*
    User getUser(Long id);

    List<User> getAllUsers();

    String getUsernameById(Long id);

    UpdateAgeResponse updateAgeById(UpdateAgeRequest request);

    Long deleteUser(Long id);

    int updatePhoneNumberById(UpdatePhoneNumberRequest request);

    void duplicateCheck(String targetColumn, String targetValue) throws UserException;

    String addUser(SignUpRequest request);

    void validateLogIn(LogInRequest request);
     */
}
