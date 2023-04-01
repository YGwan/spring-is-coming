package com.example.springJPA;

import com.example.springJPA.dao.UserRepository;
import com.example.springJPA.entity.User;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

import java.util.List;

@SpringBootApplication
public class SpringJPAApplication {

    public static void main(String[] args) {
        ConfigurableApplicationContext ctx = SpringApplication.run(SpringJPAApplication.class, args);
        UserRepository userRepository = ctx.getBean(UserRepository.class);
        playGround(userRepository);
    }

    private static void playGround(UserRepository userRepository) {
        // findAll
        List<User> all = userRepository.findAll();
        System.out.println(all.size());

        // save
        userRepository.save(new User("hi", "hi", 12, "dsf", "sdf", "sdf"));

        // findAll
        List<User> allAfterSave = userRepository.findAll();
        System.out.println(allAfterSave.get(0).getName());
        System.out.println(allAfterSave.get(0).getEmail());

        // findById

        // deleteById

        // update User name

        // findAll sort by age asc

        // findALl sort by name dec
    }
}

// Mission1
// 1. JPA, Hibernate full name, 구현체 알아보기
// 2. 이 패키지에서 Spring MVC 흔적 제거하기
// 3. JPA repository CRUD
// 4. Data 패키지를 없애고 application.properties 에서 키 값을 가져와보는데 @Value
// 5. Add User Sex data

// Mission2
// 6. Add new entity
// 7. Define relation between entities
// 8. OneToMany, ManyToOne, Performance comparison
// 9. Transactional

// Mission3
// 10. EntityManager, EntityManagerFactory
// 11. Persistent context
// 12. cache (lazy write, dirty checking), Identity
