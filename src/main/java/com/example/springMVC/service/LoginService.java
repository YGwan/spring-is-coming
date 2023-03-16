package com.example.springMVC.service;

import com.example.springMVC.dao.MyPageDao;
import com.example.springMVC.entity.MyPage;
import com.example.springMVC.exception.UserConditionException;
import com.example.springMVC.exception.UserException;
import org.springframework.stereotype.Service;

@Service
public class LoginService {

    private final MyPageDao myPageDao;

    public LoginService(MyPageDao myPageDao) {
        this.myPageDao = myPageDao;
    }

    public String insertUser(MyPage myPage) throws UserException {
        validCheck(myPage);
        return myPageDao.addUser(myPage);
    }

    private void validCheck(MyPage myPage) {
        validUsername(myPage.getUsername());
        validPassword(myPage.getPassword(), myPage.getRePassword());
        validAge(myPage.getAge());
        myPageDao.validDuplicate(myPage.getUsername(), myPage.getEmail());
    }

    private void validUsername(String username) throws UserException {
        if (!(username.length() >= 3 && username.length() < 10)) {
            throw new UserException("유효하지 않은 글자수 입니다.");
        }
    }

    private void validPassword(String passwd, String rePasswd) throws UserException {
        if (!passwd.equals(rePasswd)) {
            throw new UserException("패스워드가 서로 다릅니다.");
        }

        if (!(passwd.length() >= 3 && passwd.length() < 10)) {
            throw new UserException("유효하지 않은 글자수 입니다.");
        }
    }

    private void validAge(int age) throws UserException {
        if (age <= 0 || age >= 100) {
            throw new UserException("유효하지 않은 나이입니다.");
        }
        if (age < 20) {
            throw new UserConditionException("서비스 정책에 맞지 않는 사용자 나이입니다.");
        }
    }
}
