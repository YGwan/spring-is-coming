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
        // 7-1 findAll
        List<User> all = userRepository.findAll();
        System.out.println(all.size());
        ;

        // 7-2 save
        userRepository.save(new User("hi", "hi", 12, "dsf", "sdf", "sdf"));

        // 7-3 findAll
        List<User> allAfterSave = userRepository.findAll();
        System.out.println(allAfterSave.get(0).getName());
        System.out.println(allAfterSave.get(0).getEmail());

        // 7-4 findById

        // 7-5 deleteById

    }
}

// 1. JPA, Hibernate 정의 알아오기
// 2. 이 패키지에서 Spring MVC 흔적 제거하기
// 3. Data 패키지를 없애고 application.properties 에서 키 값을 가져와보는데 @Value
// 4. Spring MVC에서 시간상 성별을 사용하지 않았으니, sex enum 지워주세요.
// 5. Spring boot 버전 확인
// 6. Gradle 버전

