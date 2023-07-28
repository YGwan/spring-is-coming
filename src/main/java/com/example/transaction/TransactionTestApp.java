package com.example.transaction;

import com.example.transaction.entity.User;
import com.example.transaction.service.AbsTransService;
import com.example.transaction.service.BasicTransService;
import com.example.transaction.service.SyncTransService;
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
        users.add(new User(2L, "AB", 27));
        users.add(new User(3L, "jinHwan", 27));

        BasicTransService basicTransService = ac.getBean(BasicTransService.class);
        SyncTransService syncTransService = ac.getBean(SyncTransService.class);
        AbsTransService absTransService = ac.getBean(AbsTransService.class);
        TransService transService = ac.getBean(TransService.class);

        // 직접 트랜잭션을 열고 닫고 트랜잭션을 매번 메소드 인자로 보내는 가장 기본적인 트랜잭션 처리 방식
        basicTransService.useJpa(users);
        basicTransService.useJdbc(users);

        // 트랜잭션 동기화 + 트랜잭션 템플릿을 적용한 트랜잭션 처리 방식
        syncTransService.useSyncTransByJdbc(users);
        syncTransService.useJpaTransactionTemplate(users);
        syncTransService.useJdbcTransactionTemplate(users);

        // 트랜잭션 추상화를 적용한 트랜잭션 처리 방식
        absTransService.useAbstractTransByJdbc(users);
        absTransService.useAbstractTransByJpa(users);
        absTransService.useAbstractTrans(users);

        // @Transactional 어노테이션을 사용한 트랜잭션 처리 방식
        transService.joinAllUser(users);
    }
}
