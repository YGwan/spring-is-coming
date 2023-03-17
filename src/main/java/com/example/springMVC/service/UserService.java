package com.example.springMVC.service;

import com.example.springMVC.dao.UserDao;
import com.example.springMVC.dto.UpdateAgeRequest;
import com.example.springMVC.dto.UpdateAgeResponse;
import com.example.springMVC.dto.UpdatePhoneNumberRequest;
import com.example.springMVC.dto.UserResponse;
import com.example.springMVC.entity.User;
import com.example.springMVC.exception.UserException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

    private final UserDao userDao;

    public UserService(UserDao userDao) {
        this.userDao = userDao;
    }

    public UserResponse updatePhoneNumberByNameAndAge(UpdatePhoneNumberRequest request) throws UserException {
        final User requestUser = request.toUser();
        final User realUser = userDao.getUser(request.getId());

        requestUser.validateEqualByNameAndAge(realUser);

        if (requestUser.getPhoneNumber().equals(realUser.getPhoneNumber())) {
            throw new UserException("휴대폰 번호가 중복되었습니다.");
        }
        userDao.updatePhoneNumberById(request);
        return UserResponse.of(requestUser);
    }

    public UserResponse getUser(Long id) {
        return UserResponse.of(userDao.getUser(id));
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

    public UpdateAgeResponse updateAgeById(UpdateAgeRequest request) throws UserException {
        if (request.getAge() < 0 || request.getAge() >= 100) {
            throw new UserException("유효하지 않은 나이입니다.");
        }
        if (request.getAge() < 20) {
            throw new UserException("서비스 정책에 맞지 않는 사용자 나이입니다.");
        }
        return userDao.updateAgeById(request);
    }

    public Long deleteUser(Long id) {
        return userDao.deleteUser(id);
    }
}
