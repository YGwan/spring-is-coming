package com.example.transaction.dao;

import com.example.transaction.entity.User;
import com.example.transaction.config.DBConfig;
import org.springframework.jdbc.datasource.DataSourceUtils;
import org.springframework.transaction.support.TransactionSynchronizationManager;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;
import java.util.Objects;

/**
 * 유저를 한번에 DB에 저장해야한다.
 * 저장하다가 한명이라도 저장이 안되면 이전에 저장했던 유저들을 다 삭제해야한다.
 */
public class TransTestDao {

    private final DataSource dataSource;

    public TransTestDao(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    /**
     * jdbc api 사용
     */
    public void joinAllUserFromJdbc(List<User> users) throws SQLException {
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
        pstmt.close();
    }

    /**
     * transaction synchronization 사용
     */
    public void joinAllUserFromSyncTrans(List<User> users) throws SQLException {
        TransactionSynchronizationManager.initSynchronization();
        Connection conn = DataSourceUtils.getConnection(dataSource);
        conn.setAutoCommit(false);

        try {
            for (User user : users) {
                insert(user);
            }
            conn.commit();
        } catch (Exception e) {
            conn.rollback();

        } finally {
            DataSourceUtils.releaseConnection(conn, dataSource);
            TransactionSynchronizationManager.unbindResource(dataSource);
            TransactionSynchronizationManager.clearSynchronization();
        }
    }

    public void insert(User user) throws SQLException {
        Connection conn = DataSourceUtils.getConnection(dataSource);

        if (Objects.equals(user.getName(), "A")) {
            throw new IllegalArgumentException();
        }

        String sql = "INSERT INTO USER VALUES (?, ?, ?)";
        PreparedStatement pstmt = conn.prepareStatement(sql);
        pstmt.setLong(1, user.getId());
        pstmt.setString(2, user.getName());
        pstmt.setInt(3, user.getAge());
        pstmt.executeUpdate();
        pstmt.close();
    }
}
