package com.example.springMVC.dao;

import com.example.springMVC.dto.LogInRequest;
import com.example.springMVC.entity.Person;
import com.example.springMVC.exception.DBException;
import com.example.springMVC.exception.UserException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import java.sql.ResultSet;
import java.sql.SQLException;

@Component
public class PersonDao {

    private final JdbcTemplate jdbcTemplate;
    private final PersonMapper personMapper = new PersonMapper();

    public PersonDao(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }


    public void duplicateCheck(String targetColumn, String targetValue) throws UserException {
        try {
            jdbcTemplate.queryForObject(
                    "SELECT USERNAME FROM PERSON WHERE " + targetColumn + " IN (?)",
                    String.class,
                    targetValue
            );
            throw new UserException(targetColumn + " 이 중복되었습니다.");
        } catch (EmptyResultDataAccessException ignored) {
        }
    }

    public String addUser(Person person) {
        jdbcTemplate.update(
                "INSERT INTO PERSON VALUES(?,?,?,?,?,?,?)",
                person.getId(), person.getUsername(), person.getPassword(),
                person.getAge(), person.getEmail(), person.getName(), person.getPhoneNumber()
        );
        return person.getUsername();
    }

    public String validLogIn(LogInRequest request) {
        try {
            String password = jdbcTemplate.queryForObject(
                    "SELECT PASSWORD FROM PERSON WHERE USERNAME IN (?)",
                    String.class,
                    request.getUsername()
            );

            if (!(request.getPassword().equals(password))) {
                throw new DBException("로그인 실패");
            }

            return jdbcTemplate.queryForObject(
                    "SELECT EMAIL FROM PERSON WHERE USERNAME IN (?)",
                    String.class,
                    request.getUsername()
            );

        } catch (Exception e) {
            throw new DBException("로그인 실패");
        }
    }
}

class PersonMapper implements RowMapper<Person> {
    @Override
    public Person mapRow(ResultSet rs, int count) throws SQLException {
        Person person = new Person();
        person.setId(rs.getLong("id"));
        person.setUsername(rs.getString("username"));
        person.setPassword(rs.getString("password"));
        person.setAge(rs.getInt("age"));
        person.setEmail(rs.getString("email"));
        person.setName(rs.getString("name"));
        person.setPhoneNumber(rs.getString("phoneNumber"));
        return person;
    }
}


