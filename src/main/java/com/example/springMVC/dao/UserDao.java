package com.example.springMVC.dao;

import com.example.springMVC.dto.*;
import com.example.springMVC.entity.User;
import com.example.springMVC.exception.DBException;
import com.example.springMVC.exception.UserException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Component
public class UserDao {

    private final JdbcTemplate jdbcTemplate;
    private final UserRowMapper userRowMapper = new UserRowMapper();

    public UserDao(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public User getUser(Long id) {
        return jdbcTemplate.queryForObject(
                "SELECT * FROM USER WHERE ID=?",
                userRowMapper,
                id);
    }

    public List<User> getAllUsers() {
        return jdbcTemplate.query(
                "SELECT * FROM USER",
                userRowMapper
        );
    }

    public UpdateAgeResponse updateAgeById(UpdateAgeRequest request) {
        jdbcTemplate.update(
                "UPDATE USER SET AGE = ?  WHERE ID = ?",
                request.getAge(), request.getId()
        );
        return new UpdateAgeResponse(request.getId(), request.getAge());
    }

    public Long deleteUser(Long id) {
        jdbcTemplate.update(
                "DELETE FROM USER WHERE id = ?",
                id
        );
        return id;
    }

    public int updatePhoneNumberById(UpdatePhoneNumberRequest request) {
        return jdbcTemplate.update(
                "UPDATE USER SET PHONENUMBER = ?  WHERE ID = ?",
                request.getPhoneNumber(), request.getId()
        );
    }

    public void duplicateCheck(String targetColumn, String targetValue) throws UserException {
        try {
            jdbcTemplate.queryForObject(
                    "SELECT USERNAME FROM USER WHERE " + targetColumn + " = ?",
                    String.class,
                    targetValue
            );
            throw new UserException(targetColumn + " 이 중복되었습니다.");
        } catch (EmptyResultDataAccessException ignored) {
        }
    }

    public String addUser(SignUpRequest request) {
        jdbcTemplate.update(
                "INSERT INTO USER VALUES(?,?,?,?,?,?,?)",
                request.getId(), request.getUsername(), request.getPassword(),
                request.getAge(), request.getEmail(), request.getName(), request.getPhoneNumber()
        );
        return request.getUsername();
    }

    public String validLogIn(LogInRequest request) {
        String password = jdbcTemplate.queryForObject(
                "SELECT PASSWORD FROM USER WHERE USERNAME IN (?)",
                String.class,
                request.getUsername()
        );

        if (!(request.getPassword().equals(password))) {
            throw new DBException("로그인 실패");
        }

        return jdbcTemplate.queryForObject(
                "SELECT EMAIL FROM USER WHERE USERNAME IN (?)",
                String.class,
                request.getUsername()
        );
    }
}

class UserRowMapper implements RowMapper<User> {
    @Override
    public User mapRow(ResultSet rs, int count) throws SQLException {
        User user = new User();
        user.setId(rs.getLong("id"));
        user.setUsername(rs.getString("username"));
        user.setPassword(rs.getString("password"));
        user.setAge(rs.getInt("age"));
        user.setEmail(rs.getString("email"));
        user.setName(rs.getString("name"));
        user.setPhoneNumber(rs.getString("phoneNumber"));
        return user;
    }
}
