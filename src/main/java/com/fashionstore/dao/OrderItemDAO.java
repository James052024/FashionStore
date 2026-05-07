package com.fashionstore.dao;

import java.util.List;
import com.fashionstore.model.OrderItem;

public interface OrderItemDAO {

    boolean addOrderItem(OrderItem item);

    List<OrderItem> getItemsByOrderId(int orderId);

    boolean deleteItemsByOrderId(int orderId);
}