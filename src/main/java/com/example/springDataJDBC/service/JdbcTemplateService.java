package com.example.springDataJDBC.service;

import com.example.springDataJDBC.domain.User;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Service;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Service
public class JdbcTemplateService {

    private final JdbcTemplate jdbcTemplate;
    private final UserRowMapper userRowMapper = new UserRowMapper();

    public JdbcTemplateService(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public User getUser(Long id) {
        return jdbcTemplate.queryForObject(
                "SELECT * FROM PERSON WHERE ID=?",
                userRowMapper,
                id);
    }

    public List<User> getUsers(Long id) {
        List<User> users = jdbcTemplate.query(
                "SELECT * FROM PERSON WHERE ID=?",
                userRowMapper,
                id);
        return users;
    }

    public User insertUser(Long id, String name, int age) {
        jdbcTemplate.update(
                "INSERT INTO PERSON VALUES(?,?,?)",
                id, name, age
        );
        return new User(id, name, age);
    }

    public List<User> getAllUsers() {
        List<User> users = jdbcTemplate.query(
                "SELECT * FROM PERSON",
                userRowMapper
                );
        return users;
    }

    public User updateUserID(Long id, User user) {
        jdbcTemplate.update(
                "UPDATE PERSON SET ID = ? WHERE NAME = ?",
                id, user.getName()
        );
        return new User(id, user.getName(), user.getAge());
    }

    public Long deleteUser(Long id) {
        jdbcTemplate.update(
                "DELETE FROM PERSON WHERE id = ?",
                id
        );
        return id;
    }
}

class UserRowMapper implements RowMapper<User> {

    @Override
    public User mapRow(ResultSet rs, int count) throws SQLException {
        User user = new User();
        user.setId(rs.getLong("id"));
        user.setName(rs.getString("name"));
        user.setAge(rs.getInt("age"));
        return user;
    }
}
