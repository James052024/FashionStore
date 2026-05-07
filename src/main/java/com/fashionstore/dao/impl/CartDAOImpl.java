package com.fashionstore.dao.impl;

import com.fashionstore.dao.CartDAO;
import com.fashionstore.model.CartItem;
import com.fashionstore.util.DBConnection;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CartDAOImpl implements CartDAO {

    // 🔹 GET CART ID
    @Override
    public int getCartIdByUserId(int userId) {

        String sql = "SELECT cart_id FROM cart WHERE user_id=?";

        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, userId);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                return rs.getInt("cart_id");
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return -1;
    }

    // 🔹 CREATE CART
    @Override
    public boolean createCart(int userId) {

        String sql = "INSERT INTO cart(user_id) VALUES(?)";

        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, userId);
            return ps.executeUpdate() > 0;

        } catch (Exception e) {
            e.printStackTrace();
        }

        return false;
    }

    // 🔹 ADD TO CART
    @Override
    public boolean addToCart(int userId, int variantId, int quantity) {

        int cartId = getCartIdByUserId(userId);

        if (cartId == -1) {
            createCart(userId);
            cartId = getCartIdByUserId(userId);
        }

        String sql = "INSERT INTO cart_items(cart_id, variant_id, quantity) VALUES (?, ?, ?) "
                   + "ON DUPLICATE KEY UPDATE quantity = quantity + ?";

        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, cartId);
            ps.setInt(2, variantId);
            ps.setInt(3, quantity);
            ps.setInt(4, quantity);

            return ps.executeUpdate() > 0;

        } catch (Exception e) {
            e.printStackTrace();
        }

        return false;
    }

    // 🔹 UPDATE QUANTITY
    @Override
    public boolean updateItemQuantity(int cartId, int variantId, int quantity) {

        String sql = "UPDATE cart_items SET quantity=? WHERE cart_id=? AND variant_id=?";

        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, quantity);
            ps.setInt(2, cartId);
            ps.setInt(3, variantId);

            return ps.executeUpdate() > 0;

        } catch (Exception e) {
            e.printStackTrace();
        }

        return false;
    }

    // 🔹 REMOVE ITEM
    @Override
    public boolean removeItemFromCart(int cartId, int variantId) {

        String sql = "DELETE FROM cart_items WHERE cart_id=? AND variant_id=?";

        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, cartId);
            ps.setInt(2, variantId);

            return ps.executeUpdate() > 0;

        } catch (Exception e) {
            e.printStackTrace();
        }

        return false;
    }

    // 🔹 GET CART ITEMS
    @Override
    public List<CartItem> getCartItemsByUserId(int userId) {

        List<CartItem> list = new ArrayList<>();

        String sql = "SELECT ci.cart_id, ci.variant_id, ci.quantity, "
                   + "p.product_name, p.price, p.image_url, pv.size "
                   + "FROM cart_items ci "
                   + "JOIN cart c ON ci.cart_id = c.cart_id "
                   + "JOIN product_variants pv ON ci.variant_id = pv.variant_id "
                   + "JOIN products p ON pv.product_id = p.product_id "
                   + "WHERE c.user_id=?";

        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, userId);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {

                CartItem item = new CartItem();

                item.setCartId(rs.getInt("cart_id"));
                item.setVariantId(rs.getInt("variant_id"));
                item.setQuantity(rs.getInt("quantity"));   // ✅ FIXED (removed comma)
                item.setProductName(rs.getString("product_name"));
                item.setPrice(rs.getDouble("price"));
                item.setImageUrl(rs.getString("image_url")); // ✅ MUST match model
                item.setSize(rs.getString("size"));

                list.add(item);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return list;
    }
}