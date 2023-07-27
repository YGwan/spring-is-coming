package com.example.transaction.service;

import com.example.transaction.config.DBConfig;
import com.example.transaction.dao.TransDao;
import com.example.transaction.entity.User;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

@Service
public class BasicTransService {

    private final TransDao transDao;
    private final EntityManagerFactory emf;

    public BasicTransService(TransDao transDao, EntityManagerFactory emf) {
        this.transDao = transDao;
        this.emf = emf;
    }

    public void useJdbc(List<User> users) {
        try {
            joinAllUserFromJdbc(users);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void useJpa(List<User> users) {
        try {
            joinAllUserFromJPA(users);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * JDBC 사용
     */
    private void joinAllUserFromJdbc(List<User> users) throws SQLException {
        Connection conn = DBConfig.getMySqlConnection();
        conn.setAutoCommit(false);

        try {
            for (User user : users) {
                transDao.joinUser(user, conn);
            }
            conn.commit();
        } catch (Exception e) {
            conn.rollback();

        } finally {
            conn.setAutoCommit(true);
            conn.close();
        }
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
                transDao.joinUser(user, em);
            }
            tx.commit();
        } catch (Exception e) {
            tx.rollback();

        } finally {
            em.close();
        }
    }


}
