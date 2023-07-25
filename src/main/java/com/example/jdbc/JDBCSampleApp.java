package com.example.jdbc;

import com.example.jdbc.config.DBConfig;
import com.example.jdbc.dao.UserDao;
import com.example.jdbc.entity.User;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class JDBCSampleApp {

    public static void main(String[] args) {
        try (Connection conn = DBConfig.getMySqlConnection()) {
            UserDao userDao = UserDao.connect(conn);
            List<User> users = new ArrayList<>();
            users.add(new User(1L, "YongGwan", 26));
            users.add(new User(2L, "A", 27));
            users.add(new User(3L, "jinHwan", 27));
            userDao.joinAllUser(users);
//
//            userDao.insert(new User(1L, "YongGwan", 26));
//            userDao.insert(new User(3L, "A", 27));
//            userDao.insert(new User(2L, "jinHwan", 27));
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}

/*
1. User 정보(이름,나이) 를 담을 DB 테이블을 만들어라
2. User 정보(이름, 나이)를 담을 자바 class를 만들어라
3. Statement, PreparedStatement를 둘 다 사용해보고 차이점을 찾아본다
4.  User CRUD를 구현한다.
*/