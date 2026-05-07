package com.fashionstore.dao.impl;

import com.fashionstore.dao.OrderDAO;
import com.fashionstore.model.Order;
import com.fashionstore.util.DBConnection;
import com.fashionstore.dao.OrderDAO;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public abstract  class OrderDAOImpl implements OrderDAO {

    @Override
    public boolean createOrder(Order order) {

        String sql = "INSERT INTO orders(user_id, total_amount, payment_method, order_status, " +
                "delivery_name, delivery_phone, delivery_address_line1, delivery_address_line2, " +
                "delivery_city, delivery_state, delivery_pincode, delivery_country) " +
                "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

            ps.setInt(1, order.getUserId());
            ps.setDouble(2, order.getTotalAmount());
            ps.setString(3, order.getPaymentMethod());
            ps.setString(4, order.getOrderStatus());

            ps.setString(5, order.getDeliveryName());
            ps.setString(6, order.getDeliveryPhone());
            ps.setString(7, order.getDeliveryAddressLine1());
            ps.setString(8, order.getDeliveryAddressLine2());
            ps.setString(9, order.getDeliveryCity());
            ps.setString(10, order.getDeliveryState());
            ps.setString(11, order.getDeliveryPincode());
            ps.setString(12, order.getDeliveryCountry());

            int rows = ps.executeUpdate();

            if (rows > 0) {
                ResultSet rs = ps.getGeneratedKeys();
                if (rs.next()) {
                    order.setOrderId(rs.getInt(1)); // important
                }
                return true;
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return false;
    }

    @Override
    public Order getOrderById(int orderId) {

        String sql = "SELECT * FROM orders WHERE order_id = ?";
        Order order = null;

        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, orderId);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                order = mapOrder(rs);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return order;
    }

    @Override
    public List<Order> getOrdersByUserId(int userId) {

        List<Order> list = new ArrayList<>();

        String sql = "SELECT * FROM orders WHERE user_id = ? ORDER BY order_date DESC";

        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, userId);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                list.add(mapOrder(rs));
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return list;
    }

    @Override
    public boolean updateOrderStatus(int orderId, String status) {

        String sql = "UPDATE orders SET order_status = ? WHERE order_id = ?";

        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setString(1, status);
            ps.setInt(2, orderId);

            return ps.executeUpdate() > 0;

        } catch (Exception e) {
            e.printStackTrace();
        }

        return false;
    }

    @Override
    public boolean deleteOrder(int orderId) {

        String sql = "DELETE FROM orders WHERE order_id = ?";

        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, orderId);
            return ps.executeUpdate() > 0;

        } catch (Exception e) {
            e.printStackTrace();
        }

        return false;
    }

    // 🔥 Helper method (clean mapping)
    private Order mapOrder(ResultSet rs) throws SQLException {

        Order o = new Order();

        o.setOrderId(rs.getInt("order_id"));
        o.setUserId(rs.getInt("user_id"));
        o.setOrderDate(rs.getTimestamp("order_date"));
        o.setTotalAmount(rs.getDouble("total_amount"));
        o.setPaymentMethod(rs.getString("payment_method"));
        o.setOrderStatus(rs.getString("order_status"));

        o.setDeliveryName(rs.getString("delivery_name"));
        o.setDeliveryPhone(rs.getString("delivery_phone"));
        o.setDeliveryAddressLine1(rs.getString("delivery_address_line1"));
        o.setDeliveryAddressLine2(rs.getString("delivery_address_line2"));
        o.setDeliveryCity(rs.getString("delivery_city"));
        o.setDeliveryState(rs.getString("delivery_state"));
        o.setDeliveryPincode(rs.getString("delivery_pincode"));
        o.setDeliveryCountry(rs.getString("delivery_country"));

        return o;
    }
}