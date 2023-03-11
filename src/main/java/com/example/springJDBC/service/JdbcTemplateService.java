package com.example.springJDBC.service;

import com.example.springJDBC.dto.UpdatePhoneNumberRequest;
import com.example.springJDBC.entity.User;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Service;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.NoSuchElementException;

@Service
public class JdbcTemplateService {

    private final JdbcTemplate jdbcTemplate;
    private final UserRowMapper userRowMapper = new UserRowMapper();


    // TODO 2 : Controller의 todo2와 더불어 jdbcTemplate이 어떻게 주입될 수 있었는지 고민해보세요.
    //  만약 스프링의 의존성 주입이라면 앞선 세가지 방식 중 어떤 방식에 해당되는 주입 방식인가요.

    public JdbcTemplateService(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public User getUser(Long id) {
        return jdbcTemplate.queryForObject(
                "SELECT * FROM USER WHERE ID=?",
                userRowMapper,
                id);
    }

    public List<User> getUsers(Long id) {
        return jdbcTemplate.query(
                "SELECT * FROM USER WHERE ID=?",
                userRowMapper,
                id);
    }

    public Long insertUser(User user) {
        jdbcTemplate.update(
                "INSERT INTO USER VALUES(?,?,?,?)",
                user.getId(), user.getName(), user.getAge(), user.getPhoneNumber()
        );
        return user.getId();
    }

    public List<User> getAllUsers() {
        List<User> users = jdbcTemplate.query(
                "SELECT * FROM USER",
                userRowMapper
        );
        return users;
    }

    public User updateUserByID(User user) {
        jdbcTemplate.update(
                "UPDATE USER SET NAME = ?, AGE = ?, PHONENUMBER = ?  WHERE ID = ?",
                user.getName(), user.getAge(), user.getPhoneNumber(), user.getId()
        );
        return new User(user.getId(), user.getName(), user.getAge(), user.getPhoneNumber());
    }

    public Long deleteUser(Long id) {
        jdbcTemplate.update(
                "DELETE FROM USER WHERE id = ?",
                id
        );
        return id;
    }

    public User updatePhoneNumberByNameAndAge(UpdatePhoneNumberRequest updatePhoneNumberRequest) {

        final Long targetId = updatePhoneNumberRequest.getId();
        final String targetName = updatePhoneNumberRequest.getName();
        final int targetAge = updatePhoneNumberRequest.getAge();
        final String targetPhoneNumber = updatePhoneNumberRequest.getPhoneNumber();

        if (!targetName.equals(findNameByID(targetId)) || (targetAge != findAgeByID(targetId))) {
            throw new NoSuchElementException("기존 사용자 정보와 맞지 않습니다.");
        }

        if (targetPhoneNumber.equals(findPhoneNumberByID(targetId))) {
            throw new NoSuchElementException("휴대폰 번호가 중복되었습니다.");
        }

        jdbcTemplate.update(
                "UPDATE USER SET PHONENUMBER = ?  WHERE ID = ?",
                updatePhoneNumberRequest.getPhoneNumber(), updatePhoneNumberRequest.getId()
        );

        return new User(targetId, targetName, targetAge,targetPhoneNumber);
    }

    private String findNameByID(Long id) {
        return getUser(id).getName();
    }

    private int findAgeByID(Long id) {
        return getUser(id).getAge();
    }

    private String findPhoneNumberByID(Long id) {
        return getUser(id).getPhoneNumber();
    }
}

class UserRowMapper implements RowMapper<User> {

    @Override
    public User mapRow(ResultSet rs, int count) throws SQLException {
        User user = new User();
        user.setId(rs.getLong("id"));
        user.setName(rs.getString("name"));
        user.setAge(rs.getInt("age"));
        user.setPhoneNumber(rs.getString("phoneNumber"));
        return user;
    }
}