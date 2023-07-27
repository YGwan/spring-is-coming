package com.example.transaction.service;

import com.example.transaction.dao.TransDao;
import com.example.transaction.entity.User;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.jdbc.datasource.DataSourceUtils;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.stereotype.Service;
import org.springframework.transaction.support.TransactionSynchronizationManager;
import org.springframework.transaction.support.TransactionTemplate;

import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

@Service
public class SyncTransService {

    private final EntityManagerFactory emf;
    private final DataSource dataSource;
    private final TransDao transDao;

    public SyncTransService(EntityManagerFactory emf, DataSource dataSource, TransDao transDao) {
        this.emf = emf;
        this.dataSource = dataSource;
        this.transDao = transDao;
    }

    public void useSyncTransByJdbc(List<User> users) {
        try {
            joinAllUserFromSyncTranByJdbc(users);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void useJdbcTransactionTemplate(List<User> users) {
        joinAllUserByJdbcTransactionTemplate(users);
    }

    public void useJpaTransactionTemplate(List<User> users) {
        joinAllUserByJpaTransactionTemplate(users);
    }

    /**
     * transaction synchronization : JDBC 사용
     */
    private void joinAllUserFromSyncTranByJdbc(List<User> users) throws SQLException {
        TransactionSynchronizationManager.initSynchronization();
        Connection conn = DataSourceUtils.getConnection(dataSource);
        conn.setAutoCommit(false);

        try {
            for (User user : users) {
                transDao.joinUser(user);
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


    /**
     * transaction Template 사용 - JDBC
     */
    public void joinAllUserByJdbcTransactionTemplate(List<User> users) {
        DataSourceTransactionManager transactionManager = new DataSourceTransactionManager(dataSource);
        TransactionTemplate transactionTemplate = new TransactionTemplate(transactionManager);

        transactionTemplate.executeWithoutResult(status -> {
            for (User user : users) {
                try {
                    transDao.joinUser(user);
                } catch (SQLException e) {
                    throw new RuntimeException(e);
                }
            }
        });
    }

    /**
     * transaction Template 사용
     */
    public void joinAllUserByJpaTransactionTemplate(List<User> users) {
        JpaTransactionManager transactionManager = new JpaTransactionManager(emf);
        TransactionTemplate transactionTemplate = new TransactionTemplate(transactionManager);

        transactionTemplate.executeWithoutResult(status -> {
            for (User user : users) {
                try {
                    transDao.joinUser(user);
                } catch (SQLException e) {
                    throw new RuntimeException(e);
                }
            }
        });
    }
}
