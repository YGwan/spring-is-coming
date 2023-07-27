package com.example.transaction.service;

import com.example.transaction.dao.TransTestDao;
import com.example.transaction.entity.User;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;
import java.sql.SQLException;
import java.util.List;

@Service
public class TransService {

    private final TransTestDao transTestDao;

    public TransService(DataSource dataSource, EntityManagerFactory emf) {
        this.transTestDao = new TransTestDao(dataSource, emf);
    }

    public void useJdbc(List<User> users) {
        try {
            transTestDao.joinAllUserFromJdbc(users);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void useJpa(List<User> users) {
        try {
            transTestDao.joinAllUserFromJPA(users);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void useSyncTransByJdbc(List<User> users) {
        try {
            transTestDao.joinAllUserFromSyncTranByJdbc(users);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void useSyncTransByJpa(List<User> users) {
        try {
            transTestDao.joinAllUserFromSyncTranByJpa(users);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void useAbstractTransByJdbc(List<User> users) {
        try {
            transTestDao.joinAllUserFromAbstractTranByJdbc(users);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void useAbstractTransByJpa(List<User> users) {
        try {
            transTestDao.joinAllUserFromAbstractTranByJpa(users);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
