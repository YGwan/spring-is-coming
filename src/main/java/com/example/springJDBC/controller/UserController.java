package com.example.springJDBC.controller;

import com.example.springJDBC.dto.*;
import com.example.springJDBC.entity.User;
import com.example.springJDBC.dao.UserDao;
import com.example.springJDBC.service.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class UserController {

    // TODO 1 : @Autowired의 의미를 알아오세요.
    // TODO 2 : Spring의 의존성 주입 방법 3가지를 조사하세요. 그리고 그 중 가장 마음에 드는 방식을 본인 근거와 함께 골라오세요.

    private final UserDao userDao;
    private final UserService userService;

    public UserController(UserDao userDao, UserService userService) {
        this.userDao = userDao;
        this.userService = userService;
    }

    @GetMapping("/user/{id}")
    public ResponseEntity<User> userInfo(@PathVariable Long id) {
        return ResponseEntity.ok(userDao.getUser(id));
    }

    // TODO 3 : User 정보를 응답해야할 때마다 매번 StringBuilder로 정보를 만들어야할까요?
    //  만약 그렇다고 하면 이 코드가 매번 중복될 것 같은데 불편하지 않을까요? 이 불편함을 해결해보세요.
    @GetMapping("/users/{id}")
    public ResponseEntity<UsersInfoResponse> usersInfo(@PathVariable Long id) {
        List<User> users = userDao.getUsers(id);
        return ResponseEntity.ok(new UsersInfoResponse(users));
    }

    // TODO 4 : 이 User 정보는 사용자에게 응답으로 노출되는 값이에요. 사용자 측에서 원하는 형식이 바뀌면 어떻게 될까요?
    //  예를들면 갑자기 사용자가 User 정보의 name 부분을 username으로 바꾸고 싶다고 하면 어떻게 할까요.
    //  단, 그렇다고 User class의 name 필드 자체가 바뀐다면 DB랑 컬럼명도 맞춰야 하고 개발자들끼리 용어에도 문제가 될거에요.
    //  따라서 User class는 변경되지 않도록 하면서도 사용자가 원하는대로 username으로 이름 정보를 응답할 수 있도록 고민해보세요.
    @GetMapping("userName/{id}")
    public ResponseEntity<UsernameInfoResponse> userNameInfo(@PathVariable Long id) {
        User user = userDao.getUser(id);
        return ResponseEntity.ok(new UsernameInfoResponse(user));
    }

    @PostMapping("/user")
    public ResponseEntity<Long> userAdd(@RequestBody User user) {
        return ResponseEntity.ok(userDao.insertUser(user));
    }

    @GetMapping("/users")
    public ResponseEntity<List<User>> userAll() {
        return ResponseEntity.ok(userDao.getAllUsers());
    }

    @PutMapping("/user")
    public ResponseEntity<User> updateUser(@RequestBody User user) {
        return ResponseEntity.ok(userDao.updateUserById(user));
    }

    // TODO 5 : 사용자의 휴대폰 번호를 수정하는 API를 생성해보세요.
    //  이때 사용자 측에서는 사용자의 id, name, age, phonenumber를 요청 바디로 전달합니다.
    //  기존 사용자 정보와 name, age가 맞는지 확인하고 맞다면 phonenumber를 수정하세요.
    //  예외 사항 1 ) 기존 사용자 정보가 맞지 않는 경우 예외를 발생합니다.
    //  예외 사항 2 ) 동일한 휴대폰 번호로 수정 요청을 할 수 없습니다.
    @PutMapping("/user/phoneNumber")
    public ResponseEntity<User> updatePhoneNumber(@RequestBody UpdatePhoneNumberRequest request) {
        return ResponseEntity.ok(userService.updatePhoneNumberByNameAndAge(request));
    }

    // TODO 6 : 사용자의 나이를 수정하는 API를 생성해보세요.
    //  이때 사용자 측에서는 사용자의 id, age만을 요청 바디로 전달합니다.
    //  특이 사항 ) 휴대폰 번호는 중요한 개인정보입니다. 나이 수정 요청의 응답으로 휴대폰 번호 필드가 노출되지 않도록 주의하세요.
    @PutMapping("/user/age")
    public ResponseEntity<UpdateAgeResponse> updateAge(@RequestBody UpdateAgeRequest request) {
        return ResponseEntity.ok(userDao.updateAgeById(request));
    }

    @DeleteMapping("/user/{id}")
    public ResponseEntity<Long> deleteUser(@PathVariable Long id) {
        return ResponseEntity.ok(userDao.deleteUser(id));
    }
}
