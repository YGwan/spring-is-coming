package com.example.springhello;

import com.example.springhello.repository.User;
import com.example.springhello.repository.UserDao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;


public class JDBCSampleApp {

    public static void main(String[] args) {

        try {
            Connection conn = DriverManager.getConnection(
                    "jdbc:mysql://localhost:3306/YGWAN",
                    "root",
                    "0000"
            );
            UserDao userDao = new UserDao(conn);
            userDao.updateById(2L, new User(2L, "jinhena", 27));
            conn.close();
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