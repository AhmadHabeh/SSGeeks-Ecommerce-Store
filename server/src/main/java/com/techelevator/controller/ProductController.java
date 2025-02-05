package com.techelevator.controller;

import com.techelevator.dao.ProductDao;
import com.techelevator.exception.DaoException;
import com.techelevator.model.Product;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("/products")
public class ProductController {

    private ProductDao productDao;

    public ProductController(ProductDao productDao) {
        this.productDao = productDao;
    }

    @RequestMapping(path = "", method = RequestMethod.GET)
    public List<Product> getList(@RequestParam(required = false) String sku, @RequestParam(required = false) String name) {
        try {
            if (sku == null && name == null) {
                return productDao.getProducts();
            } else {
                return productDao.getProductsByOptionalSkuAndOrName(sku, name, true);
            }
        }
        catch (DaoException e) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "DAO error - " + e.getMessage());
        }
    }

    @RequestMapping(path = "/{id}", method = RequestMethod.GET)
    public Product getById(@PathVariable int id) {
        try {
            Product product = productDao.getProductById(id);
            if (product == null) {
                throw new ResponseStatusException(HttpStatus.NOT_FOUND, String.format("The product with id=%d was not found.", id));
            }
            return product;
        }
        catch (DaoException e) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "DAO error - " + e.getMessage());
        }
    }

}
