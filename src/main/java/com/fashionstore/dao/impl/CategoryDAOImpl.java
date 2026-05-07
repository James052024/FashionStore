package com.fashionstore.dao.impl;

import com.fashionstore.dao.CategoryDAO;
import com.fashionstore.model.Category;
import com.fashionstore.util.DBConnection;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CategoryDAOImpl implements CategoryDAO {

    private Connection con;

    public CategoryDAOImpl() {
        con = DBConnection.getConnection();
    }

    @Override
    public List<Category> getAllCategories() {
        List<Category> list = new ArrayList<>();

        try {
            String query = "SELECT * FROM categories";
            PreparedStatement ps = con.prepareStatement(query);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                Category c = new Category();
                c.setCategoryId(rs.getInt("category_id"));
                c.setCategoryName(rs.getString("category_name"));
                c.setDescription(rs.getString("description"));

                list.add(c);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return list;
    }

    @Override
    public Category getCategoryById(int categoryId) {
        Category c = null;

        try {
            String query = "SELECT * FROM categories WHERE category_id=?";
            PreparedStatement ps = con.prepareStatement(query);
            ps.setInt(1, categoryId);

            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                c = new Category();
                c.setCategoryId(rs.getInt("category_id"));
                c.setCategoryName(rs.getString("category_name"));
                c.setDescription(rs.getString("description"));
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return c;
    }

    @Override
    public Category getCategoryByName(String name) {
        Category c = null;

        try {
            String query = "SELECT * FROM categories WHERE category_name=?";
            PreparedStatement ps = con.prepareStatement(query);
            ps.setString(1, name);

            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                c = new Category();
                c.setCategoryId(rs.getInt("category_id"));
                c.setCategoryName(rs.getString("category_name"));
                c.setDescription(rs.getString("description"));
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return c;
    }

    @Override
    public boolean addCategory(Category category) {
        boolean rowInserted = false;

        try {
            String query = "INSERT INTO categories(category_name, description) VALUES (?, ?)";
            PreparedStatement ps = con.prepareStatement(query);

            ps.setString(1, category.getCategoryName());
            ps.setString(2, category.getDescription());

            rowInserted = ps.executeUpdate() > 0;

        } catch (Exception e) {
            e.printStackTrace();
        }

        return rowInserted;
    }

    @Override
    public boolean updateCategory(Category category) {
        boolean rowUpdated = false;

        try {
            String query = "UPDATE categories SET category_name=?, description=? WHERE category_id=?";
            PreparedStatement ps = con.prepareStatement(query);

            ps.setString(1, category.getCategoryName());
            ps.setString(2, category.getDescription());
            ps.setInt(3, category.getCategoryId());

            rowUpdated = ps.executeUpdate() > 0;

        } catch (Exception e) {
            e.printStackTrace();
        }

        return rowUpdated;
    }

    @Override
    public boolean deleteCategory(int categoryId) {
        boolean rowDeleted = false;

        try {
            String query = "DELETE FROM categories WHERE category_id=?";
            PreparedStatement ps = con.prepareStatement(query);
            ps.setInt(1, categoryId);

            rowDeleted = ps.executeUpdate() > 0;

        } catch (Exception e) {
            e.printStackTrace();
        }

        return rowDeleted;
    }
}