package com.company.productservice.dao;

import com.company.productservice.model.Product;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class ProductDaoJdbcTemplateImpl implements ProductDao{

    private static final String CREATE_PRODUCT_SQL =
            "INSERT INTO product (product_name, product_description, list_price, unit_cost) VALUES (?,?,?,?)";
    private static final String GET_PRODUCT_SQL =
            "SELECT * FROM product WHERE product_id = ?";
    private static final String GET_ALL_PRODUCTS_SQL =
            "SELECT * FROM product";
    private static final String UPDATE_PRODUCT_SQL =
            "UPDATE product SET product_name = ?, product_description = ?, list_price = ?, unit_cost = ? WHERE product_id = ?";
    private static final String DELETE_PRODUCT_SQL =
            "DELETE FROM product WHERE product_id = ?";

    JdbcTemplate jdbcTemplate;

    public ProductDaoJdbcTemplateImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public Product createProduct(Product product) {
        jdbcTemplate.update(CREATE_PRODUCT_SQL,
                product.getProductName(),
                product.getProductDescription(),
                product.getListPrice(),
                product.getUnitCost());

        int id = jdbcTemplate.queryForObject("SELECT last_insert_id()", Integer.class);
        product.setProductId(id);
        return product;
    }

    @Override
    public Product getProduct(Integer id) {
        try {
            return jdbcTemplate.queryForObject(GET_PRODUCT_SQL, new BeanPropertyRowMapper<>(Product.class), id);
        } catch( EmptyResultDataAccessException e) {
            return null;
        }
    }

    @Override
    public List<Product> getAllProducts() {
        return jdbcTemplate.query(GET_ALL_PRODUCTS_SQL, new BeanPropertyRowMapper<>(Product.class));
    }

    @Override
    public void updateProduct(Product product) {
        jdbcTemplate.update(UPDATE_PRODUCT_SQL,
                product.getProductName(),
                product.getProductDescription(),
                product.getListPrice(),
                product.getUnitCost(),
                product.getProductId());
    }

    @Override
    public void deleteProduct(Integer id) {
        jdbcTemplate.update(DELETE_PRODUCT_SQL, id);
    }
}
