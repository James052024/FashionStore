package com.fashionstore.dao.impl;

import com.fashionstore.dao.ProductDAO;
import com.fashionstore.model.Product;
import com.fashionstore.util.DBConnection;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ProductDAOImpl implements ProductDAO {

    @Override
    public List<Product> getAllProducts() {

        List<Product> list = new ArrayList<>();
        String query = "SELECT * FROM products WHERE is_active = TRUE";

        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(query);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                list.add(mapProduct(rs));
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return list;
    }

    @Override
    public Product getProductById(int productId) {

        Product product = null;
        String query = "SELECT * FROM products WHERE product_id=?";

        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(query)) {

            ps.setInt(1, productId);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                product = mapProduct(rs);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return product;
    }

    @Override
    public List<Product> getProductsByCategory(int categoryId) {

        List<Product> list = new ArrayList<>();
        String query = "SELECT * FROM products WHERE category_id=? AND is_active=TRUE";

        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(query)) {

            ps.setInt(1, categoryId);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                list.add(mapProduct(rs));
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return list;
    }

    @Override
    public List<Product> searchProducts(String keyword) {

        List<Product> list = new ArrayList<>();
        String query = "SELECT * FROM products WHERE product_name LIKE ? OR brand LIKE ?";

        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(query)) {

            String key = "%" + keyword + "%";

            ps.setString(1, key);
            ps.setString(2, key);

            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                list.add(mapProduct(rs));
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return list;
    }

    @Override
    public List<Product> filterProducts(int categoryId, String brand, double minPrice, double maxPrice) {

        List<Product> list = new ArrayList<>();

        String query = "SELECT * FROM products WHERE category_id=? AND brand=? AND price BETWEEN ? AND ?";

        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(query)) {

            ps.setInt(1, categoryId);
            ps.setString(2, brand);
            ps.setDouble(3, minPrice);
            ps.setDouble(4, maxPrice);

            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                list.add(mapProduct(rs));
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return list;
    }

    @Override
    public boolean addProduct(Product product) {

        String query = "INSERT INTO products(category_id, product_name, brand, description, price, image_url, is_active) VALUES (?, ?, ?, ?, ?, ?, ?)";

        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(query)) {

            ps.setInt(1, product.getCategoryId());
            ps.setString(2, product.getProductName());
            ps.setString(3, product.getBrand());
            ps.setString(4, product.getDescription());
            ps.setDouble(5, product.getPrice());
            ps.setString(6, product.getImageUrl());
            ps.setBoolean(7, product.isActive());

            return ps.executeUpdate() > 0;

        } catch (Exception e) {
            e.printStackTrace();
        }

        return false;
    }

    @Override
    public boolean updateProduct(Product product) {

        String query = "UPDATE products SET category_id=?, product_name=?, brand=?, description=?, price=?, image_url=?, is_active=? WHERE product_id=?";

        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(query)) {

            ps.setInt(1, product.getCategoryId());
            ps.setString(2, product.getProductName());
            ps.setString(3, product.getBrand());
            ps.setString(4, product.getDescription());
            ps.setDouble(5, product.getPrice());
            ps.setString(6, product.getImageUrl());
            ps.setBoolean(7, product.isActive());
            ps.setInt(8, product.getProductId());

            return ps.executeUpdate() > 0;

        } catch (Exception e) {
            e.printStackTrace();
        }

        return false;
    }

    @Override
    public boolean deleteProduct(int productId) {

        String query = "DELETE FROM products WHERE product_id=?";

        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(query)) {

            ps.setInt(1, productId);
            return ps.executeUpdate() > 0;

        } catch (Exception e) {
            e.printStackTrace();
        }

        return false;
    }

    // 🔥 Helper Method (VERY IMPORTANT)
    private Product mapProduct(ResultSet rs) throws SQLException {

        Product p = new Product();

        p.setProductId(rs.getInt("product_id"));
        p.setCategoryId(rs.getInt("category_id"));
        p.setProductName(rs.getString("product_name"));
        p.setBrand(rs.getString("brand"));
        p.setDescription(rs.getString("description"));
        p.setPrice(rs.getDouble("price"));
        p.setImageUrl(rs.getString("image_url"));
        p.setActive(rs.getBoolean("is_active"));
        p.setCreatedAt(rs.getTimestamp("created_at"));

        return p;
    }
}