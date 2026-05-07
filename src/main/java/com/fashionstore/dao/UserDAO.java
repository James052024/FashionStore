package com.fashionstore.dao;

import com.fashionstore.model.User;

public interface UserDAO {

    // Auth
    User loginUser(String email, String password);

    // Getters
    User getUserById(int userId);
    User getUserByEmail(String email);
    User getUserByPhone(String phone);

    // Register
    boolean registerUser(User user);

    // Updates
    boolean updateUser(User user);
    boolean updatePassword(int userId, String newPassword);

    // Validation
    boolean emailExists(String email);
    boolean phoneExists(String phone);

    // Admin
    boolean deleteUser(int userId);
}