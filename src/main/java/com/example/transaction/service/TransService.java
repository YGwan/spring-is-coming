package com.example.transaction.service;

import com.example.transaction.dao.TransDao;
import com.example.transaction.entity.User;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.SQLException;
import java.util.List;

@Service
public class TransService {

    private final TransDao transDao;

    public TransService(TransDao transDao) {
        this.transDao = transDao;
    }

    /**
     * transaction abstraction
     */
    @Transactional
    public void joinAllUser(List<User> users) {
        for (User user : users) {
            try {
                transDao.joinUser(user);
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
    }
}
