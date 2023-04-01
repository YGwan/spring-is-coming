package com.example.springJPA.dao;

import com.example.springJPA.dto.*;
import com.example.springJPA.entity.User;
import com.example.springJPA.exception.DBException;
import com.example.springJPA.exception.UserException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Component;

import java.sql.Connection;
import java.sql.PreparedStatement;
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
        try {
            return jdbcTemplate.queryForObject(
                    "SELECT * FROM USER WHERE ID=?",
                    userRowMapper,
                    id);
        } catch (EmptyResultDataAccessException e) {
            throw new DBException("존재하지 않는 유저입니다");
        }
    }

    public List<User> getAllUsers() {
        return jdbcTemplate.query(
                "SELECT * FROM USER",
                userRowMapper
        );
    }

    public String getUsernameById(Long id) {
        return jdbcTemplate.queryForObject(
                "SELECT USERNAME FROM USER WHERE ID=?",
                String.class,
                id);
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
        KeyHolder keyHolder = new GeneratedKeyHolder();
        jdbcTemplate.update(
                (Connection con) -> {
                    PreparedStatement pstmt = con.prepareStatement(
                            "INSERT INTO USER(USERNAME, PASSWORD, AGE, EMAIL, NAME, PHONENUMBER)" +
                                    " VALUES(?,?,?,?,?,?)", new String[]{"ID"}
                            );
                            pstmt.setString(1, request.getUsername());
                            pstmt.setString(2, request.getPassword());
                            pstmt.setInt(3, request.getAge());
                            pstmt.setString(4, request.getEmail());
                            pstmt.setString(5, request.getName());
                            pstmt.setString(6, request.getPhoneNumber());
                            return pstmt;
                }, keyHolder);
        return request.getUsername();
    }

    public void validateLogIn(LogInRequest request) {
        try {
            jdbcTemplate.queryForObject(
                    "SELECT ID FROM USER WHERE USERNAME = ? AND PASSWORD = ?",
                    Long.class,
                    request.getUsername(), request.getPassword()
            );

        } catch (EmptyResultDataAccessException e) {
            throw new DBException("로그인 실패");
        }
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
