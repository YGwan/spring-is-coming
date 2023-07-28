package com.example.jdbc.dao;

import com.example.jdbc.entity.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UserDao {

    private final Connection conn;

    private UserDao(Connection conn) {
        this.conn = conn;
    }

    public static UserDao connect(Connection conn) {
        return new UserDao(conn);
    }

    public User selectById(Long id) throws SQLException {
        String sql = "SELECT * FROM USER WHERE id = ? ";
        PreparedStatement pstmt = conn.prepareStatement(sql);
        pstmt.setLong(1, id);
        ResultSet rs = pstmt.executeQuery();

        Long idValue = rs.getLong("id");
        String name = rs.getString("name");
        Integer age = rs.getInt("age");
        pstmt.close();
        return new User(idValue, name, age);
    }

    public void insert(User user) throws SQLException {
        String sql = "INSERT INTO USER VALUES (?, ?, ?)";
        PreparedStatement pstmt = conn.prepareStatement(sql);
        pstmt.setLong(1, user.getId());
        pstmt.setString(2, user.getName());
        pstmt.setInt(3, user.getAge());
        pstmt.executeUpdate();
        pstmt.close();
    }

    public void delete(Long id) throws SQLException {
        String sql = "DELETE FROM USER WHERE ID=?";
        PreparedStatement pstmt = conn.prepareStatement(sql);
        pstmt.setLong(1, id);
        pstmt.executeUpdate();
        pstmt.close();
    }

    public void updateById(Long id, User user) throws SQLException {
        String sql = "UPDATE USER SET NAME = ?, AGE =? WHERE ID = ?";
        PreparedStatement pstmt = conn.prepareStatement(sql);
        pstmt.setString(1, user.getName());
        pstmt.setLong(2, user.getAge());
        pstmt.setLong(3, id);
        pstmt.executeUpdate();
        pstmt.close();
    }
}
