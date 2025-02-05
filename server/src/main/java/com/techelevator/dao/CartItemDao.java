package com.techelevator.dao;

import com.techelevator.model.CartItem;
import java.util.List;

public interface CartItemDao {

    CartItem getCartItemById(int cartItemId);
    CartItem getCartItemByProductIdAndUserId(int productId, int userId);
    List<CartItem> getCartItemsByUserId(int userId);
    CartItem createCartItem(CartItem item);
    CartItem updateCartItem(CartItem item);
    int deleteCartItemById(int cartItemId);
    int deleteCartItemsByUserId(int userId);
}
