package com.example.springhello.repository;

// 1. User라는 테이블을 만드세요.
// 2. User 정보를 10개 기입하세요. (executeQuery())

import java.sql.*;

public class Users {

    public void insert(User user) throws SQLException {
        Long id = user.getId();
        String name = user.getName();
        Integer age = user.getAge();

        String sql = "INSERT INTO PERSON ("
                + "    id,"
                + "    name,"
                + "    age "
                + ") VALUES (?,?,?)";

        Connection conn = DriverManager.getConnection(
                "jdbc:mysql://localhost:3306/YGWAN",
                "root",
                "0000"
        );
        PreparedStatement st = conn.prepareStatement(sql);

        st.close();
        conn.close();
    }
}
