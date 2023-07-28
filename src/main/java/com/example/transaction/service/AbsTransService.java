package com.example.transaction.service;

import com.example.transaction.dao.TransDao;
import com.example.transaction.entity.User;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.stereotype.Service;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.DefaultTransactionDefinition;

import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;
import java.util.List;

@Service
public class AbsTransService {

    private final DataSource dataSource;
    private final EntityManagerFactory emf;
    private final PlatformTransactionManager transactionManager;
    private final TransDao transDao;

    public AbsTransService(DataSource dataSource, EntityManagerFactory emf, PlatformTransactionManager transactionManager, TransDao transDao) {
        this.dataSource = dataSource;
        this.emf = emf;
        this.transactionManager = transactionManager;
        this.transDao = transDao;
    }

    public void useAbstractTransByJdbc(List<User> users) {
        joinAllUserFromAbstractTranByJdbc(users);
    }

    public void useAbstractTransByJpa(List<User> users) {
        joinAllUserFromAbstractTranByJpa(users);
    }

    public void useAbstractTrans(List<User> users) {
        joinAllUserFromAbstractTran(users);
    }

    /**
     * transaction abstraction : JDBC 사용
     */
    public void joinAllUserFromAbstractTranByJdbc(List<User> users) {
        PlatformTransactionManager transactionManager = new DataSourceTransactionManager(dataSource);
        TransactionStatus status = transactionManager.getTransaction(new DefaultTransactionDefinition());
        try {
            for (User user : users) {
                transDao.joinUser(user);
            }
            transactionManager.commit(status);

        } catch (Exception e) {
            transactionManager.rollback(status);
            System.out.println(e.getMessage());
        }
    }

    /**
     * transaction abstraction : JPA 사용
     */
    public void joinAllUserFromAbstractTranByJpa(List<User> users) {
        PlatformTransactionManager transactionManager = new JpaTransactionManager(emf);
        TransactionStatus status = transactionManager.getTransaction(new DefaultTransactionDefinition());

        try {
            for (User user : users) {
                transDao.joinUser(user);
            }
            transactionManager.commit(status);

        } catch (Exception e) {
            transactionManager.rollback(status);
            System.out.println(e.getMessage());
        }
    }

    /**
     * transaction abstraction : ALL
     */
    public void joinAllUserFromAbstractTran(List<User> users) {
        TransactionStatus status = transactionManager.getTransaction(new DefaultTransactionDefinition());

        try {
            for (User user : users) {
                transDao.joinUser(user);
            }
            transactionManager.commit(status);

        } catch (Exception e) {
            transactionManager.rollback(status);
            System.out.println(e.getMessage());
        }
    }
}
