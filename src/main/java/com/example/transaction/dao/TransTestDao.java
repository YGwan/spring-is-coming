package com.example.transaction.dao;

import com.example.transaction.config.DBConfig;
import com.example.transaction.entity.User;
import org.springframework.jdbc.datasource.DataSourceUtils;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.support.TransactionSynchronizationManager;
import org.springframework.transaction.support.TransactionTemplate;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
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
@Repository
public class TransTestDao {

    private final DataSource dataSource;
    private final EntityManagerFactory emf;

    public TransTestDao(DataSource dataSource, EntityManagerFactory emf) {
        this.dataSource = dataSource;
        this.emf = emf;
    }

    /**
     * JDBC 사용
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
    }

    /**
     * JPA 사용
     */
    public void joinAllUserFromJPA(List<User> users) throws SQLException {
        EntityManager em = emf.createEntityManager();
        EntityTransaction tx = em.getTransaction();

        try {
            tx.begin();
            for (User user : users) {
                insert(user, em);
            }
            tx.commit();
        } catch (Exception e) {
            tx.rollback();

        } finally {
            em.close();
        }
    }

    public void insert(User user, EntityManager em) {
        if (Objects.equals(user.getName(), "A")) {
            throw new IllegalArgumentException();
        }
        em.persist(user);
    }

    /**
     * transaction synchronization : JDBC 사용
     */
    public void joinAllUserFromSyncTranByJdbc(List<User> users) throws SQLException {
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

    public void joinAllUserFromSyncTranByJpa(List<User> users) throws SQLException {
        JpaTransactionManager transactionManager = new JpaTransactionManager(emf);
        TransactionTemplate transactionTemplate = new TransactionTemplate(transactionManager);
        EntityManager em = emf.createEntityManager();

        transactionTemplate.execute(count -> {
            for (User user : users) {
                em.persist(user);
            }
            return users.size();
        });
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
