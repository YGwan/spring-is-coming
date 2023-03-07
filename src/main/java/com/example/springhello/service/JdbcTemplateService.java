package com.example.springhello.service;

import com.example.springhello.domain.User;
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

    public Long setUser(User user) {
        jdbcTemplate.update(
                "INSERT INTO PERSON VALUES(?,?,?)",
                user.getId(), user.getName(), user.getAge()
        );
        return user.getId();
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
