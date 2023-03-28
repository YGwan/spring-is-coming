package com.example.springMVC.service;

import com.example.springMVC.dao.UserDao;
import com.example.springMVC.dto.*;
import com.example.springMVC.entity.User;
import com.example.springMVC.exception.DBException;
import com.example.springMVC.exception.UserException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

    private final UserDao userDao;

    public UserService(UserDao userDao) {
        this.userDao = userDao;
    }

    public UpdatePhoneNumberResponse updatePhoneNumberByNameAndAge(UpdatePhoneNumberRequest request) throws UserException {
        final User realUser = userDao.getUser(request.getId());

        realUser.validateEqualByNameAndAge(request.getName(), request.getAge());

        if (request.getPhoneNumber().equals(realUser.getPhoneNumber())) {
            throw new UserException("휴대폰 번호가 중복되었습니다.");
        }
        userDao.updatePhoneNumberById(request);
        return UpdatePhoneNumberRequest.of(request);
    }

    public String getUsername(Long id) {
        return userDao.getUsernameById(id);
    }

    public UserResponse getUser(Long id) {
        return UserResponse.of(userDao.getUser(id));
    }

    public List<UserResponse> getAllUsers() {
        return UserResponse.listOf(userDao.getAllUsers());
    }

    public UpdateAgeResponse updateAgeById(UpdateAgeRequest request) throws UserException {
        validateAge(request.getAge());
        return userDao.updateAgeById(request);
    }

    public Long deleteUser(Long id) {
        return userDao.deleteUser(id);
    }

    public void logIn(LogInRequest request) throws DBException {
        userDao.validateLogIn(request);
    }

    public String signUp(SignUpRequest request) throws UserException {
        signUpValidate(request);
        return userDao.addUser(request);
    }

    private void signUpValidate(SignUpRequest request) {
        validateUsername(request.getUsername());
        validatePassword(request.getPassword(), request.getRePassword());
        userDao.duplicateCheck("USERNAME", request.getUsername());
        validateAge(request.getAge());
        userDao.duplicateCheck("EMAIL", request.getEmail());
    }

    private void validateUsername(String username) throws UserException {
        if (!(username.length() >= 3 && username.length() < 10)) {
            throw new UserException("유효하지 않은 글자수 입니다.");
        }
    }

    private void validatePassword(String password, String rePassword) throws UserException {
        if (!password.equals(rePassword)) {
            throw new UserException("패스워드가 서로 다릅니다.");
        }
    }

    private void validateAge(int age) throws UserException {
        if (age <= 0 || age >= 100) {
            throw new UserException("유효하지 않은 나이입니다.");
        }
        if (age < 19) {
            throw new UserException("서비스 정책에 맞지 않는 사용자 나이입니다.");
        }
    }
}
