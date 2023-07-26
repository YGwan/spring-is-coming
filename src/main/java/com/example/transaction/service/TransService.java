package com.example.transaction.service;

import com.example.transaction.dao.TransTestDao;
import com.example.transaction.entity.User;
import org.springframework.stereotype.Service;

import javax.sql.DataSource;
import java.sql.SQLException;
import java.util.List;

@Service
public class TransService {

    private final TransTestDao transTestDao;

    public TransService(DataSource dataSource) {
        this.transTestDao = new TransTestDao(dataSource);
    }

    public void useJdbcApi(List<User> users) {
        try {
            transTestDao.joinAllUserFromJdbc(users);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void useSyncTrans(List<User> users) {
        try {
            transTestDao.joinAllUserFromSyncTrans(users);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
