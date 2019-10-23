package com.company.customerservice.dao;


import com.company.customerservice.model.Customer;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class CustomerDaoJdbcTemplateImpl implements CustomerDao{

    //    customer_id int(11) not null auto_increment primary key,
//    first_name varchar(50) not null,
//    last_name varchar(50) not null,
//    street varchar(50) not null,
//    city varchar(50) not null,
//    zip varchar(10) not null,
//    email varchar(75) not null,
//    phone varchar(20) not null

    public static final String GET_CUSTOMER_SQL =
            "SELECT * FROM customer WHERE customer_id = ?";
    public static final String GET_ALL_CUSTOMERS_SQL =
            "SELECT * FROM customer";
    public static final String CREATE_CUSTOMER_SQL =
            "INSERT INTO customer (first_name, last_name, street, city, zip, email, phone) VALUES (?,?,?,?,?,?,?)";
    public static final String UPDATE_CUSTOMER_SQL =
            "UPDATE customer SET first_name = ?, last_name =?, street = ?, city = ?, zip = ?, email = ?, phone = ? WHERE customer_id = ?";
    public static final String DELETE_CUSTOMER_SQL =
            "DELETE FROM customer WHERE customer_id = ?";

    JdbcTemplate jdbcTemplate;

    public CustomerDaoJdbcTemplateImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public Customer getCustomer(Integer id) {
        try {
            return jdbcTemplate.queryForObject(GET_CUSTOMER_SQL, new BeanPropertyRowMapper<>(Customer.class), id);
        } catch (EmptyResultDataAccessException e) {
            return null;
        }
    }

    @Override
    public List<Customer> getAllCustomers() {
        return jdbcTemplate.query(GET_ALL_CUSTOMERS_SQL, new BeanPropertyRowMapper<>(Customer.class));
    }

    @Override
    public Customer createCustomer(Customer customer) {
        jdbcTemplate.update(CREATE_CUSTOMER_SQL,
                customer.getFirstName(),
                customer.getLastName(),
                customer.getStreet(),
                customer.getCity(),
                customer.getZip(),
                customer.getEmail(),
                customer.getPhone());

        int id = jdbcTemplate.queryForObject("SELECT last_insert_id()", Integer.class);
        customer.setCustomerId(id);
        return customer;
    }

    @Override
    public void deleteCustomer(Integer id) {
        jdbcTemplate.update(DELETE_CUSTOMER_SQL, id);

    }

    @Override
    public void updateCustomer(Customer customer) {
        jdbcTemplate.update(UPDATE_CUSTOMER_SQL,
                customer.getFirstName(),
                customer.getLastName(),
                customer.getStreet(),
                customer.getCity(),
                customer.getZip(),
                customer.getEmail(),
                customer.getPhone(),
                customer.getCustomerId());
    }
}
