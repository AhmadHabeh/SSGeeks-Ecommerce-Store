package com.techelevator.dao;

import com.techelevator.model.Product;

import java.util.List;

public interface ProductDao {

    List<Product> getProducts();

    Product getProductById(int productId);

    List<Product> getProductsByUserId(int userId);

    List<Product> getProductsByWishlistId(int wishlistId);

    List<Product> getProductsByOptionalSkuAndOrName(String productSku, String name, boolean useWildCard);
}
