package com.fashionstore.dao.impl;

import com.fashionstore.dao.OrderItemDAO;
import com.fashionstore.model.OrderItem;
import com.fashionstore.util.DBConnection;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class OrderItemDAOImpl implements OrderItemDAO {

    @Override
    public boolean addOrderItem(OrderItem item) {

        String sql = "INSERT INTO order_items(order_id, variant_id, quantity, price) VALUES (?, ?, ?, ?)";

        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, item.getOrderId());
            ps.setInt(2, item.getVariantId());
            ps.setInt(3, item.getQuantity());
            ps.setDouble(4, item.getPrice());

            return ps.executeUpdate() > 0;

        } catch (Exception e) {
            e.printStackTrace();
        }

        return false;
    }

    @Override
    public List<OrderItem> getItemsByOrderId(int orderId) {

        List<OrderItem> list = new ArrayList<>();

        String sql = "SELECT * FROM order_items WHERE order_id = ?";

        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, orderId);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                OrderItem item = new OrderItem();

                item.setOrderItemId(rs.getInt("order_item_id"));
                item.setOrderId(rs.getInt("order_id"));
                item.setVariantId(rs.getInt("variant_id"));
                item.setQuantity(rs.getInt("quantity"));
                item.setPrice(rs.getDouble("price"));

                list.add(item);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return list;
    }

    @Override
    public boolean deleteItemsByOrderId(int orderId) {

        String sql = "DELETE FROM order_items WHERE order_id = ?";

        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, orderId);
            return ps.executeUpdate() > 0;

        } catch (Exception e) {
            e.printStackTrace();
        }

        return false;
    }
}