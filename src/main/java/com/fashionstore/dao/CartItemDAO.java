package com.fashionstore.dao;

import com.fashionstore.model.CartItem;
import java.util.List;

public interface CartItemDAO {

    boolean addItem(int cartId, int variantId, int quantity);

    boolean updateItem(int cartId, int variantId, int quantity);

    boolean removeItem(int cartId, int variantId);

    List<CartItem> getItemsByCartId(int cartId);
}