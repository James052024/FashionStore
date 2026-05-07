package com.fashionstore.dao;

import com.fashionstore.model.CartItem;
import java.util.List;

public interface CartDAO {

    boolean addToCart(int userId, int variantId, int quantity);

    boolean updateItemQuantity(int cartId, int variantId, int quantity);

    boolean removeItemFromCart(int cartId, int variantId);

    List<CartItem> getCartItemsByUserId(int userId);

    int getCartIdByUserId(int userId);

    boolean createCart(int userId);
}