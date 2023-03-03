package com.example.springhello;

import java.sql.*;

public class JDBCSampleApp {

    public static void main(String[] args) {
        try (
                Connection conn = DriverManager.getConnection(
                        "jdbc:mysql://localhost:3306",
                        "root",
                        "0000"
                );
                Statement stmt = conn.createStatement();
                ResultSet rs = stmt.executeQuery("show databases");
        ) {
            while (rs.next()) {
                System.out.println(rs.getString(1));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
