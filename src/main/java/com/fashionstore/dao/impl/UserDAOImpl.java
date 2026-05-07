package com.fashionstore.dao.impl;

import com.fashionstore.dao.UserDAO;
import com.fashionstore.model.User;
import com.fashionstore.util.DBConnection;

import java.sql.*;

public class UserDAOImpl implements UserDAO {

    // ✅ REGISTER
    @Override
    public boolean registerUser(User user) {

        String sql = "INSERT INTO users(full_name, email, phone, password, address_line1, address_line2, city, state, pincode, country) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setString(1, user.getFullName().trim());
            ps.setString(2, user.getEmail().trim());
            ps.setString(3, user.getPhone().trim());
            ps.setString(4, user.getPassword().trim());
            ps.setString(5, user.getAddressLine1());
            ps.setString(6, user.getAddressLine2());
            ps.setString(7, user.getCity());
            ps.setString(8, user.getState());
            ps.setString(9, user.getPincode());
            ps.setString(10, user.getCountry());

            return ps.executeUpdate() > 0;

        } catch (Exception e) {
            e.printStackTrace();
        }

        return false;
    }

    // ✅ LOGIN (FIXED)
    @Override
    public User loginUser(String email, String password) {

        String sql = "SELECT * FROM users WHERE email=? AND password=?";
        User user = null;

        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            // 🔥 TRIM INPUT (IMPORTANT)
            ps.setString(1, email.trim());
            ps.setString(2, password.trim());

            // 🔥 DEBUG
            System.out.println("LOGIN EMAIL: " + email);
            System.out.println("LOGIN PASSWORD: " + password);

            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                user = mapUser(rs);
                System.out.println("USER FOUND IN DB");
            } else {
                System.out.println("NO USER FOUND (LOGIN FAILED)");
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return user;
    }

    // ✅ GET BY ID
    @Override
    public User getUserById(int userId) {

        String sql = "SELECT * FROM users WHERE user_id=?";
        User user = null;

        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, userId);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                user = mapUser(rs);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return user;
    }

    // ✅ GET BY EMAIL
    @Override
    public User getUserByEmail(String email) {

        String sql = "SELECT * FROM users WHERE email=?";
        User user = null;

        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setString(1, email.trim());
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                user = mapUser(rs);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return user;
    }

    // ✅ GET BY PHONE
    @Override
    public User getUserByPhone(String phone) {

        String sql = "SELECT * FROM users WHERE phone=?";
        User user = null;

        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setString(1, phone.trim());
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                user = mapUser(rs);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return user;
    }

    // ✅ EMAIL EXISTS
    @Override
    public boolean emailExists(String email) {

        String sql = "SELECT 1 FROM users WHERE email=?";

        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setString(1, email.trim());
            ResultSet rs = ps.executeQuery();

            return rs.next();

        } catch (Exception e) {
            e.printStackTrace();
        }

        return false;
    }

    // ✅ PHONE EXISTS
    @Override
    public boolean phoneExists(String phone) {

        String sql = "SELECT 1 FROM users WHERE phone=?";

        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setString(1, phone.trim());
            ResultSet rs = ps.executeQuery();

            return rs.next();

        } catch (Exception e) {
            e.printStackTrace();
        }

        return false;
    }

    // ✅ UPDATE USER
    @Override
    public boolean updateUser(User user) {

        String sql = "UPDATE users SET full_name=?, phone=?, address_line1=?, address_line2=?, city=?, state=?, pincode=?, country=? WHERE user_id=?";

        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setString(1, user.getFullName());
            ps.setString(2, user.getPhone());
            ps.setString(3, user.getAddressLine1());
            ps.setString(4, user.getAddressLine2());
            ps.setString(5, user.getCity());
            ps.setString(6, user.getState());
            ps.setString(7, user.getPincode());
            ps.setString(8, user.getCountry());
            ps.setInt(9, user.getUserId());

            return ps.executeUpdate() > 0;

        } catch (Exception e) {
            e.printStackTrace();
        }

        return false;
    }

    // ✅ UPDATE PASSWORD
    @Override
    public boolean updatePassword(int userId, String newPassword) {

        String sql = "UPDATE users SET password=? WHERE user_id=?";

        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setString(1, newPassword.trim());
            ps.setInt(2, userId);

            return ps.executeUpdate() > 0;

        } catch (Exception e) {
            e.printStackTrace();
        }

        return false;
    }

    // ✅ DELETE USER
    @Override
    public boolean deleteUser(int userId) {

        String sql = "DELETE FROM users WHERE user_id=?";

        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, userId);

            return ps.executeUpdate() > 0;

        } catch (Exception e) {
            e.printStackTrace();
        }

        return false;
    }

    // 🔥 COMMON MAPPER
    private User mapUser(ResultSet rs) throws SQLException {

        User u = new User();

        u.setUserId(rs.getInt("user_id"));
        u.setFullName(rs.getString("full_name"));
        u.setEmail(rs.getString("email"));
        u.setPhone(rs.getString("phone"));
        u.setPassword(rs.getString("password"));
        u.setAddressLine1(rs.getString("address_line1"));
        u.setAddressLine2(rs.getString("address_line2"));
        u.setCity(rs.getString("city"));
        u.setState(rs.getString("state"));
        u.setPincode(rs.getString("pincode"));
        u.setCountry(rs.getString("country"));
        u.setCreatedAt(rs.getTimestamp("created_at"));

        return u;
    }
}