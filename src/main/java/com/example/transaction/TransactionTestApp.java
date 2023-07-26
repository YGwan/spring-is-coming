package com.example.transaction;

import com.example.transaction.entity.User;
import com.example.transaction.service.TransService;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

import java.util.ArrayList;
import java.util.List;

@SpringBootApplication
public class TransactionTestApp {

    public static void main(String[] args) {
        ApplicationContext ac = SpringApplication.run(TransactionTestApp.class, args);
        List<User> users = new ArrayList<>();
        users.add(new User(1L, "YongGwan", 26));
        users.add(new User(2L, "A", 27));
        users.add(new User(3L, "jinHwan", 27));

        TransService transService = ac.getBean(TransService.class);


        transService.useJdbcApi(users);
        transService.useSyncTrans(users);
    }
}
