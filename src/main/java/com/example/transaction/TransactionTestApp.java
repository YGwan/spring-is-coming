package com.example.transaction;

import com.example.transaction.entity.User;
import com.example.transaction.service.AbsTransService;
import com.example.transaction.service.BasicTransService;
import com.example.transaction.service.SyncTransService;
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
        users.add(new User(2L, "AB", 27));
        users.add(new User(3L, "jinHwan", 27));

        BasicTransService basicTransService = ac.getBean(BasicTransService.class);
        SyncTransService syncTransService = ac.getBean(SyncTransService.class);
        AbsTransService absTransService = ac.getBean(AbsTransService.class);

//        basicTransService.useJpa(users);
//        basicTransService.useJdbc(users);

//        syncTransService.useSyncTransByJdbc1(users);
        syncTransService.useJpaTransactionTemplate(users);

//        absTransService.useAbstractTransByJdbc(users);
//        absTransService.useAbstractTransByJpa(users);
    }
}
