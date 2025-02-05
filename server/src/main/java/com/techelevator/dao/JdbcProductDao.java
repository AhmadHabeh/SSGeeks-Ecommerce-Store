package com.techelevator.dao;

import com.techelevator.exception.DaoException;
import com.techelevator.model.Product;
import org.springframework.jdbc.CannotGetJdbcConnectionException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class JdbcProductDao implements ProductDao {

    private final JdbcTemplate jdbcTemplate;

    public JdbcProductDao(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public List<Product> getProducts() {
        List<Product> products = new ArrayList<>();
        String sql = "SELECT * FROM product ORDER BY product_id";
        try {
            SqlRowSet results = jdbcTemplate.queryForRowSet(sql);
            while (results.next()) {
                Product product = mapRowToProduct(results);
                products.add(product);
            }
        }
        catch (CannotGetJdbcConnectionException e) {
            throw new DaoException("Unable to connect to server or database", e);
        }
        return products;
    }

    @Override
    public Product getProductById(int productId) {
        Product product = null;
        String sql = "SELECT * FROM product WHERE product_id = ?";
        try {
            SqlRowSet results = jdbcTemplate.queryForRowSet(sql, productId);
            if (results.next()) {
                return mapRowToProduct(results);
            }
        }
        catch (CannotGetJdbcConnectionException e) {
            throw new DaoException("Unable to connect to server or database", e);
        }
        return product;
    }

    @Override
    public List<Product> getProductsByUserId(int userId) {
        List<Product> products = new ArrayList<>();
        String sql = "SELECT * FROM product WHERE product_id IN (SELECT product_id FROM cart_item WHERE user_id = ?)";
        try {
            SqlRowSet results = jdbcTemplate.queryForRowSet(sql, userId);
            while (results.next()) {
                Product product = mapRowToProduct(results);
                products.add(product);
            }
        }
        catch (CannotGetJdbcConnectionException e) {
            throw new DaoException("Unable to connect to server or database", e);
        }
        return products;
    }

    @Override
    public List<Product> getProductsByWishlistId(int wishlistId) {
        List<Product> products = new ArrayList<>();
        String sql = "SELECT * FROM product WHERE product_id IN (SELECT product_id FROM wishlist_item WHERE wishlist_id = ?)";
        try {
            SqlRowSet results = jdbcTemplate.queryForRowSet(sql, wishlistId);
            while (results.next()) {
                Product product = mapRowToProduct(results);
                products.add(product);
            }
        }
        catch (CannotGetJdbcConnectionException e) {
            throw new DaoException("Unable to connect to server or database", e);
        }
        return products;
    }

    @Override
    public List<Product> getProductsByOptionalSkuAndOrName(String productSku, String name, boolean useWildcard) {
        List<Product> list = new ArrayList<>();

        if (useWildcard) {
            name = "%" + (name == null ? "" : name) + "%";
        }
        // Note: This is how to do this with a single SQL command. But since we don't teach COALESCE, we're doing
        // this a more laborious way. But lookup COALESCE -- it's very valuable!
        //String sql = "SELECT * FROM product WHERE product_sku = COALESCE(?, product_sku) AND name like ? ORDER BY product_id";

        // If the sku is null or empty, don't include it in the WHERE
        boolean checkSku = productSku != null && productSku.trim().length() > 0;

        String sql =
                "SELECT * FROM product WHERE name ILIKE ? " +
                (checkSku ? "AND product_sku = ? " : "") +
                "ORDER BY product_id";
        try {
            SqlRowSet results;
            if (checkSku) {
                results = jdbcTemplate.queryForRowSet(sql, name, productSku);
            } else {
                results = jdbcTemplate.queryForRowSet(sql, name);
            }
            while (results.next()) {
                Product product = mapRowToProduct(results);
                list.add(product);
            }
        }
        catch (CannotGetJdbcConnectionException e) {
            throw new DaoException("Unable to connect to server or database", e);
        }
        return list;
    }

    private Product mapRowToProduct(SqlRowSet rs) {
        Product product = new Product();
        product.setProductId(rs.getInt("product_id"));
        product.setProductSku(rs.getString("product_sku"));
        product.setName(rs.getString("name"));
        product.setDescription(rs.getString("description"));
        product.setPrice(rs.getBigDecimal("price"));
        product.setImageName(rs.getString("image_name"));
        return product;
    }
}
