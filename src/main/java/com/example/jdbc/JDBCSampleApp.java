package com.example.jdbc;

import com.example.jdbc.config.DBConfig;
import com.example.jdbc.dao.UserDao;
import com.example.jdbc.entity.User;

import java.sql.Connection;
import java.sql.SQLException;

public class JDBCSampleApp {

    public static void main(String[] args) {
        try (Connection conn = DBConfig.getMySqlConnection()) {
            UserDao userDao = UserDao.connect(conn);
            userDao.updateById(2L, new User(2L, "jinhena", 27));
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