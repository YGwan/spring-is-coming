package com.example.springJDBC.service;

import com.example.springJDBC.dao.UserDao;
import com.example.springJDBC.dto.UpdateAgeRequest;
import com.example.springJDBC.dto.UpdateAgeResponse;
import com.example.springJDBC.dto.UpdatePhoneNumberRequest;
import com.example.springJDBC.dto.UserResponse;
import com.example.springJDBC.entity.User;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;

@Service
public class UserService {

    private final UserDao userDao;

    public UserService(UserDao userDao) {
        this.userDao = userDao;
    }

    public User updatePhoneNumberByNameAndAge(UpdatePhoneNumberRequest request) {
        final User requestUser = request.toUser();
        final User realUser = userDao.getUser(request.getId());

        requestUser.validateEqualByNameAndAge(realUser);

        if (requestUser.getPhoneNumber().equals(realUser.getPhoneNumber())) {
            throw new NoSuchElementException("휴대폰 번호가 중복되었습니다.");
        }
        userDao.updatePhoneNumberById(request);
        return requestUser;
    }

    public User getUser(Long id) {
        return userDao.getUser(id);
    }

    public Long insertUser(User user) {
        return userDao.insertUser(user);
    }

    public List<UserResponse> getAllUsers() {
        return UserResponse.listOf(userDao.getAllUsers());
    }

    public UserResponse updateUserById(User user) {
        return UserResponse.of(userDao.updateUserById(user));
    }

    public UpdateAgeResponse updateAgeById(UpdateAgeRequest request) {
        return userDao.updateAgeById(request);
    }

    public Long deleteUser(Long id) {
        return userDao.deleteUser(id);
    }
}
