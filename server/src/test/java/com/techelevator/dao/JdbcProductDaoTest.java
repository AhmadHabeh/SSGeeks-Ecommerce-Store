package com.techelevator.dao;

import com.techelevator.model.Product;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.jdbc.core.JdbcTemplate;

import java.math.BigDecimal;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class JdbcProductDaoTest extends BaseDaoTest {
    protected static final Product PRODUCT_1 = new Product(1, "SKU-001", "Product 1", "Product description 1.", new BigDecimal("14.99"), "Product001.jpg");
    protected static final Product PRODUCT_2 = new Product(2, "SKU-002", "Product 2", "Product description 2.", new BigDecimal("21.99"), "Product002.jpg");
    protected static final Product PRODUCT_3 = new Product(3, "SKU-003", "Product name 3", "Product description 3.", new BigDecimal("3.59"), "Product003.jpg");

    private JdbcProductDao dao;

    @BeforeEach
    public void setup() {
        JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
        dao = new JdbcProductDao(jdbcTemplate);
    }

    @Test
    public void getAll_returns_all_products() {
        List<Product> products = dao.getProducts();

        assertNotNull(products);
        assertEquals(7, products.size());
        assertEquals(PRODUCT_1, products.get(0));
    }

    @Test
    public void getById_returns_valid_user() {
        Product product = dao.getProductById(1);
        assertEquals(PRODUCT_1, product);
    }

    @Test
    public void getById_bad_id_returns_null() {
        Product product = dao.getProductById(-1);
        assertNull(product, "Call to getById with invalid id should return NULL");
    }

    @Test
    public void find_all_null_returns_all_products() {
        List<Product> products = dao.getProductsByOptionalSkuAndOrName(null, null, true);

        assertNotNull(products);
        assertEquals(7, products.size());
        assertEquals(PRODUCT_1, products.get(0));
        assertEquals(PRODUCT_2, products.get(1));
        assertEquals(PRODUCT_3, products.get(2));
    }

    @Test
    public void find_sku_returns_correct_products() {
        List<Product> products = dao.getProductsByOptionalSkuAndOrName("SKU-001", null, true);

        assertNotNull(products);
        assertEquals(1, products.size());
        assertEquals(PRODUCT_1, products.get(0));
    }

    @Test
    public void find_blank_sku_returns_all_products() {
        List<Product> products = dao.getProductsByOptionalSkuAndOrName("", null, true);

        assertNotNull(products);
        assertEquals(7, products.size());
    }

    @Test
    public void find_partial_sku_doesnt_match() {
        List<Product> products = dao.getProductsByOptionalSkuAndOrName("SKU", null, true);

        assertNotNull(products);
        assertEquals(0, products.size());
    }

    @Test
    public void find_name_returns_correct_products() {
        List<Product> products = dao.getProductsByOptionalSkuAndOrName(null, "Product name 3", true);

        assertNotNull(products);
        assertEquals(1, products.size());
        assertEquals(PRODUCT_3, products.get(0));
    }

    @Test
    public void find_partial_name_does_match() {
        List<Product> products = dao.getProductsByOptionalSkuAndOrName(null, "Product name", true);

        assertNotNull(products);
        assertEquals(5, products.size());
        assertEquals(PRODUCT_3, products.get(0));
    }

    @Test
    public void find_sku_and_name_returns_intersection() {
        List<Product> products = dao.getProductsByOptionalSkuAndOrName("SKU-003", "Product name", true);

        assertNotNull(products);
        assertEquals(1, products.size());
        assertEquals(PRODUCT_3, products.get(0));
    }

    @Test
    public void find_sku_and_name_returns_intersection_empty() {
        List<Product> products = dao.getProductsByOptionalSkuAndOrName("SKU-003", "kwd", true);

        assertNotNull(products);
        assertEquals(0, products.size());
    }
}
