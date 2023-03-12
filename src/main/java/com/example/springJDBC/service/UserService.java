package com.example.springJDBC.service;

import com.example.springJDBC.dao.UserDao;
import com.example.springJDBC.dto.UpdatePhoneNumberRequest;
import com.example.springJDBC.entity.User;
import org.springframework.stereotype.Service;

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
}
