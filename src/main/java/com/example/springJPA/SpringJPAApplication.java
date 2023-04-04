package com.example.springJPA;

import com.example.springJPA.dao.UserRepository;
import com.example.springJPA.entity.Sex;
import com.example.springJPA.entity.User;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.data.domain.Sort;

import java.util.List;
import java.util.Optional;

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
        userRepository.save(new User("hi", "hi", Sex.남, 12, "dsf@abc.com", "sdf", "sdf"));

        // findAll
        List<User> allAfterSave = userRepository.findAll();
        System.out.println(allAfterSave.get(0).getName());
        System.out.println(allAfterSave.get(0).getEmail());

        // findById
        Long id = 1L;
        Optional<User> user = userRepository.findById(id);
        User user1 = user.orElseThrow(() -> new IllegalArgumentException("사용자 없음"));
        System.out.println(user1.getName());

        // update User name
        user1.setName("yong");
        userRepository.save(user1);
        System.out.println(user1.getName());

        // deleteById
        userRepository.deleteById(id);

        userRepository.save(new User("hi", "hi", Sex.남, 22, "dsf@abc.com", "sdf", "sd-f"));
        userRepository.save(new User("yong", "hi", Sex.녀, 15, "dsdf@abc.com", "pys", "sdf-dsq"));
        userRepository.save(new User("jin", "hi", Sex.녀, 16, "dsszzdf@abc.com", "zkd", "sdf-dsd"));

        // findAll sort by age asc
        List<User> usersOrderByAgeAsc = userRepository.findAll(Sort.by(Sort.Direction.ASC, "age"));
        System.out.println(usersOrderByAgeAsc.get(0).getAge());
        System.out.println(usersOrderByAgeAsc.get(2).getAge());

        // findALl sort by name dec
        List<User> usersOrderByNameDec = userRepository.findAll(Sort.by(Sort.Direction.ASC, "name"));
        System.out.println(usersOrderByNameDec.get(0).getName());
        System.out.println(usersOrderByNameDec.get(2).getName());
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

// Squash room
// post /room/{roomId}/dispatch -> 예약자 정보, 함께 사용하는 유저 정보 / 단 이미 사용 중인 유저가 포함되선 안돼
// post /room/{roomId}/release  -> 예약자 또는 함께 사용하는 유저인지 확인
// get  /room/{roomId} -> 현재 예약 여부, 예약 생성 시간, 예약자 정보, 함께 사용하는 유저 정보


// Mission3
// 10. EntityManager, EntityManagerFactory
// 11. Persistent context
// 12. cache (lazy write, dirty checking), Identity
