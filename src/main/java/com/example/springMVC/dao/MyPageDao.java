package com.example.springMVC.dao;

import com.example.springMVC.entity.MyPage;
import com.example.springMVC.exception.UserException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import java.sql.ResultSet;
import java.sql.SQLException;

@Component
public class MyPageDao {

    private final JdbcTemplate jdbcTemplate;
    private final MyPageMapper myPageMapper = new MyPageMapper();

    public MyPageDao(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }


    public void validDuplicate(String username, String email) throws UserException {
        try {
            jdbcTemplate.queryForObject(
                    "SELECT USERNAME FROM MYPAGE WHERE EXISTS ( " +
                            "SELECT * FROM MYPAGE WHERE NAME = ? OR EMAIL = ?)",
                    String.class,
                    username, email
            );
            throw new UserException("이메일 혹은 아이디가 중복되었습니다.");
        } catch (EmptyResultDataAccessException ignored) {
        }
    }

    public String addUser(MyPage myPage) {
        jdbcTemplate.update(
                "INSERT INTO MYPAGE VALUES(?,?,?,?,?,?,?)",
                myPage.getId(), myPage.getUsername(), myPage.getPassword(),
                myPage.getAge(), myPage.getEmail(), myPage.getName(), myPage.getPhoneNumber()
        );
        return myPage.getUsername();
    }
}

class MyPageMapper implements RowMapper<MyPage> {

    @Override
    public MyPage mapRow(ResultSet rs, int count) throws SQLException {
        MyPage myPage = new MyPage();
        myPage.setId(rs.getLong("id"));
        myPage.setUsername(rs.getString("username"));
        myPage.setPassword(rs.getString("password"));
        myPage.setAge(rs.getInt("age"));
        myPage.setEmail(rs.getString("email"));
        myPage.setName(rs.getString("name"));
        myPage.setPhoneNumber(rs.getString("phoneNumber"));
        return myPage;
    }
}


