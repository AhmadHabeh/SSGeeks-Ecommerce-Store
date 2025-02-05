package com.techelevator.dao;

import com.techelevator.model.Wishlist;
import com.techelevator.model.WishlistItem;

import java.util.List;

public interface WishlistDao {

    List<Wishlist> getWishlists(int userId);

    Wishlist getWishlistByWishListIdAndUserId(int userId, int id);

    Wishlist createWishlist(Wishlist wishlist);

    int deleteWishlistById(int userId, int wishlistId);

    WishlistItem createWishListItem(WishlistItem wishlistItem);

    int deleteWishListItem(int userId, int wishlistId, int productId);

    WishlistItem getWishlistItemByWishlistIdAndProductId(int wishlistId, int productId);

    WishlistItem getWishlistItemById(int wishlistItemId);
}
