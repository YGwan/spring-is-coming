package com.example.transaction.dao;

import com.example.transaction.entity.User;
import org.springframework.jdbc.datasource.DataSourceUtils;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Objects;

/**
 * 유저를 한번에 DB에 저장해야한다.
 * 저장하다가 한명이라도 저장이 안되면 이전에 저장했던 유저들을 다 삭제해야한다.
 */
@Repository
public class TransDao {

    private final DataSource dataSource;

    public TransDao(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    public void joinUser(User user, Connection conn) throws SQLException {
        insertUser(user, conn);
    }

    public void joinUser(User user) throws SQLException {
        Connection conn = DataSourceUtils.getConnection(dataSource);

        insertUser(user, conn);
    }

    public void joinUser(User user, EntityManager em) {
        if (Objects.equals(user.getName(), "A")) {
            throw new IllegalArgumentException();
        }
        em.persist(user);
    }

    private static void insertUser(User user, Connection conn) throws SQLException {
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
