package com.example.springMVC.service;

import com.example.springMVC.dao.PersonDao;
import com.example.springMVC.dto.LogInRequest;
import com.example.springMVC.entity.Person;
import com.example.springMVC.exception.DBException;
import com.example.springMVC.exception.UserException;
import org.springframework.stereotype.Service;

@Service
public class MyPageService {

    private final PersonDao personDao;

    public MyPageService(PersonDao personDao) {
        this.personDao = personDao;
    }

    public String insertUser(Person person) throws UserException {
        validCheck(person);
        return personDao.addUser(person);
    }

    public String logIn(LogInRequest request) throws DBException {
        return personDao.validLogIn(request);
    }

    private void validCheck(Person person) {
        validUsername(person.getUsername());
        personDao.duplicateCheck("USERNAME", person.getUsername());
        validPassword(person.getPassword(), person.getRePassword());
        validAge(person.getAge());
        personDao.duplicateCheck("EMAIL", person.getEmail());
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
            throw new UserException("서비스 정책에 맞지 않는 사용자 나이입니다.");
        }
    }
}
