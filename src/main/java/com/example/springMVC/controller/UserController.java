package com.example.springMVC.controller;

import com.example.springMVC.dto.*;
import com.example.springMVC.exception.DBException;
import com.example.springMVC.service.UserService;
import com.example.springMVC.token.JwtProvider;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user")
public class UserController {

    private final UserService userService;
    private final JwtProvider jwtProvider;

    public UserController(UserService userService, JwtProvider jwtProvider) {
        this.userService = userService;
        this.jwtProvider = jwtProvider;
    }

    @PutMapping("/phoneNumber")
    public ResponseEntity<UpdatePhoneNumberResponse> updatePhoneNumber(@RequestBody UpdatePhoneNumberRequest request) {
        return ResponseEntity.ok(userService.updatePhoneNumberByNameAndAge(request));
    }

    @PutMapping("/age")
    public ResponseEntity<UpdateAgeResponse> updateAge(@RequestBody UpdateAgeRequest request) {
        return ResponseEntity.ok(userService.updateAgeById(request));
    }

    @PostMapping("/logIn")
    public ResponseEntity<String> logIn(@RequestBody LogInRequest request) {
        String jwtToken = jwtProvider.createToken(request.getUsername(), userService.logIn(request));
        return ResponseEntity.ok(jwtToken);
    }

    @ExceptionHandler(DBException.class)
    public ResponseEntity<?> logInHandler(DBException e) {
        return ResponseEntity.badRequest().body(e.toString());
    }

    @ExceptionHandler(NullPointerException.class)
    public ResponseEntity<?> npeHandler() {
        return ResponseEntity.badRequest().body("적절하지 않은 로그인 요청");
    }

    // TODO 3 : 사용자 회원 가입을 진행하세요. (username, password, re-password, age, email, name, phoneNumber 가 주어집니다.)
    //          서비스 유저 정책은 다음과 같습니다.
    //          username은 전체 유저에서 고유해야 합니다. 예외 메시지 "중복된 유저 이름입니다"를 반환하세요.
    //          username은 3글자 이상 10글자 미만입니다. 예외 메시지 "유효하지 않은 글자수 입니다"를 반환하세요.
    //          age가 음수 또는 100이상일 때는 "유효하지 않은 나이입니다"를 사용자에게 반환하고,
    //          age가 19세 미만인 경우 "서비스 정책에 맞지 않는 사용자 나이입니다"를 사용자에게 반환하세요.
    //          패스워드 글자수 정책은 username과 동일, 메시지는 알아서.
    //          패스워드/re-password가 불일치 시에는 "패스워드가 서로 다릅니다"
    //          이메일은 이메일 형식 (@)를 만족시키세요. (Spring mvc @Valid) 뭐 이런 키워드가 있습니다. 이메일도 전체 중복될 수 없음

    // TODO 4 : DB에서 생기는 예외는 절대 사용자에게 노출되면 안됩니다.
    //          DB에서 생기는 모든 예외는 사용자 입력이 있는 쿼리의 경우 BAD_REQUEST를, 그렇지 않은 나머지 경우는 500를 반환하세요.
    //          예를 들면 로그인을 했는데 username이 없어 -> 그럼 이 예외를 바로 내보내면 안됩니다.

    // TODO 5 : 지금은 애플리케이션에 문제가 생기면 `Whitelabel Error Page` 가 출력됩니다.
    //           가능한 모든 예외 상황을 정의하고 표시하세요. (@ControllerAdvice)

    // TODO 6 : JWT 토큰 사용해보기 (회원가입과 전체 구조)
    //          - 회원가입을 하면 유저 정보 중 적절한 값을 키로 JWT 토큰을 생성하고 해당 토큰 값을 응답으로 반환합니다.
    //          - 토큰을 저장해야 할까요? 고민하세요.

    // TODO 7 : 로그인 구현
    //          - 사용자는 username, password 요청합니다.
    //          - 사용자가 없거나, password가 틀린 경우 -> "로그인 실패"를 반환
    //          - 나머지 모든 경우는 -> "적절하지 않는 로그인 요청"을 반환합니다.
    //          - 로그인 성공의 경우 로그인 토큰을 반환합니다.

    // TODO 8 : 사용자 마이페이지 관련 컨트롤러 입니다.
    //          - 사용자의 Authorization 헤더에서 토큰을 읽어 유효한 토큰인지 확인하고 그런 경우에만 해당 API가 실행됩니다.
    //          - 유효하지 않은 경우 적절한 상태 코드와 함께 적절한 예외 메시지를 출력하세요.

    // TODO 9 : 최종적으로 아래 API를 구현되어야 합니다.
    //          - 회원 가입
    //          - 로그인
    //          - 폰 넘버 수정
    //          - age 값 수정
    //          - 이메일 값 수정

    // TODO 10 : 스프링 스큐리티 사용 금지
    //          - 라이브러리 (jjwt) 사용하세요. gradle 의존성을 추가하면 됩니다.
}
