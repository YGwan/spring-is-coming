package com.example.transaction.jdbcApi;

import com.example.transaction.jdbcApi.dao.UserDao;
import com.example.transaction.entity.User;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class JdbcApp {

    public static void main(String[] args) {
        UserDao userDao = new UserDao();
        List<User> users = new ArrayList<>();
        users.add(new User(1L, "YongGwan", 26));
        users.add(new User(2L, "A", 27));
        users.add(new User(3L, "jinHwan", 27));

        try {
            userDao.joinAllUser(users);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
