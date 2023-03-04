package com.example.springhello.config;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConfig {

    public static Connection getMySqlConnection() {
        try {
            return DriverManager.getConnection(
                    "jdbc:mysql://localhost:3306/YGWAN",
                    "root",
                    "0000"
            );
        } catch (SQLException e) {
            // DB 설정 에러 정보를 외부로 throws싶지 않아서 일부로 정보 숨김
            throw new IllegalArgumentException("커넥션 오류");
        }
    }
}
