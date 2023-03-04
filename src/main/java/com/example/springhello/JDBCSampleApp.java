package com.example.springhello;

import com.example.springhello.repository.User;
import com.example.springhello.repository.Users;

import java.sql.*;


public class JDBCSampleApp {

    public static void main(String[] args) throws SQLException {

        Users users = new Users();
        User user = new User(1L, "ygwan", 25);
        users.insert(user);

        try (
                Connection conn = DriverManager.getConnection(
                        "jdbc:mysql://localhost:3306/YGWAN",
                        "root",
                        "0000"
                );
                Statement stmt = conn.createStatement();
                ResultSet rs = stmt.executeQuery( "SELECT * FROM PERSON")
//                int a = stmt.executeUpdate("INSERT INTO PERSON VALUES (1,'YGWAN',26)");
                ) {
            while (rs.next()) {
                System.out.println(rs.getString(1));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}

/*
1. User 정보(이름,나이) 를 담을 DB 테이블을 만들어라
2. User 정보(이름, 나이)를 담을 자바 class를 만들어라
3. Statement, PreparedStatement를 둘 다 사용해보고 차이점을 찾아본다
4.  User CRUD를 구현한다.
*/