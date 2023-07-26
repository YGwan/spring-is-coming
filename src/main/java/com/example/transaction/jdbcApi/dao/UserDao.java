package com.example.transaction.jdbcApi.dao;


import com.example.transaction.jdbcApi.config.DBConfig;
import com.example.transaction.entity.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;
import java.util.Objects;

public class UserDao {

    /**
     * 유저를 한번에 DB에 저장해야한다.
     * 저장하다가 한명이라도 저장이 안되면 이전에 저장했던 유저들을 다 삭제해야한다.
     */
    public void joinAllUser(List<User> users) throws SQLException {
        Connection conn = DBConfig.getMySqlConnection();
        conn.setAutoCommit(false);

        try {
            for (User user : users) {
                insert(user, conn);
            }
            conn.commit();
        } catch (Exception e) {
            conn.rollback();

        } finally {
            conn.setAutoCommit(true);
            conn.close();
        }
    }

    public void insert(User user, Connection conn) throws SQLException {
        if (Objects.equals(user.getName(), "A")) {
            throw new IllegalArgumentException();
        }

        String sql = "INSERT INTO USER VALUES (?, ?, ?)";
        PreparedStatement pstmt = conn.prepareStatement(sql);
        pstmt.setLong(1, user.getId());
        pstmt.setString(2, user.getName());
        pstmt.setInt(3, user.getAge());
        pstmt.executeUpdate();
    }
}
