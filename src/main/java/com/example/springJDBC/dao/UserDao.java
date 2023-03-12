package com.example.springJDBC.dao;

import com.example.springJDBC.dto.UpdateAgeRequest;
import com.example.springJDBC.dto.UpdateAgeResponse;
import com.example.springJDBC.dto.UpdatePhoneNumberRequest;
import com.example.springJDBC.entity.User;
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

    // TODO 2 : Controller의 todo2와 더불어 jdbcTemplate이 어떻게 주입될 수 있었는지 고민해보세요.
    //  만약 스프링의 의존성 주입이라면 앞선 세가지 방식 중 어떤 방식에 해당되는 주입 방식인가요.
    public UserDao(JdbcTemplate jdbcTemplate) {
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
        return jdbcTemplate.query(
                "SELECT * FROM USER",
                userRowMapper
        );
    }

    public User updateUserById(User user) {
        jdbcTemplate.update(
                "UPDATE USER SET NAME = ?, AGE = ?, PHONENUMBER = ?  WHERE ID = ?",
                user.getName(), user.getAge(), user.getPhoneNumber(), user.getId()
        );
        return new User(user.getId(), user.getName(), user.getAge(), user.getPhoneNumber());
    }

    public UpdateAgeResponse updateAgeById(UpdateAgeRequest request) {
        jdbcTemplate.update(
                "UPDATE USER SET AGE = ?  WHERE ID = ?",
                request.getAge(), request.getId()
        );
        return new UpdateAgeResponse(request.getId(), findNameById(request.getId()), request.getAge());
    }

    private String findNameById(Long id) {
        return jdbcTemplate.queryForObject(
                "SELECT NAME FROM USER WHERE ID=?",
                String.class,
                id);
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