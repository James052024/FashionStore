package com.fashionstore.dao.impl;

import com.fashionstore.dao.CartItemDAO;
import com.fashionstore.model.CartItem;
import com.fashionstore.util.DBConnection;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CartItemDAOImpl implements CartItemDAO {

    // 🔹 ADD ITEM
    @Override
    public boolean addItem(int cartId, int variantId, int quantity) {

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

    // 🔹 UPDATE ITEM
    @Override
    public boolean updateItem(int cartId, int variantId, int quantity) {

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
    public boolean removeItem(int cartId, int variantId) {

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

    // 🔹 GET ITEMS
    @Override
    public List<CartItem> getItemsByCartId(int cartId) {

        List<CartItem> list = new ArrayList<>();

        String sql = "SELECT ci.cart_id, ci.variant_id, ci.quantity, "
                   + "p.product_name, p.price, p.image_url, pv.size "
                   + "FROM cart_items ci "
                   + "JOIN product_variants pv ON ci.variant_id = pv.variant_id "
                   + "JOIN products p ON pv.product_id = p.product_id "
                   + "WHERE ci.cart_id=?";

        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, cartId);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {

                CartItem item = new CartItem();

                item.setCartId(rs.getInt("cart_id"));
                item.setVariantId(rs.getInt("variant_id"));
                item.setQuantity(rs.getInt("quantity"));
                item.setProductName(rs.getString("product_name"));
                item.setPrice(rs.getDouble("price"));
                item.setImageUrl(rs.getString("image_url"));
                item.setSize(rs.getString("size"));

                list.add(item);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return list;
    }
}