package com.company.invoiceservice.dao;

import com.company.invoiceservice.dto.Invoice;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Repository
public class InvoiceDaoImplementation implements InvoiceDao{
    //prepared statements
    public static final String INSERT_INVOICE_SQL =
            "INSERT INTO invoice (customer_id, purchase_date) VALUES (?, ?)";

    public static final String SELECT_INVOICE_SQL =
            "SELECT * FROM invoice WHERE invoice_id = ?";

    public static final String SELECT_ALL_INVOICES_SQL =
            "SELECT * FROM invoice";

    public static final String UPDATE_INVOICE_SQL =
            "UPDATE invoice SET customer_id = ?, purchase_date = ? WHERE invoice_id = ?";

    public static final String DELETE_INVOICE_SQL =
            "DELETE FROM invoice WHERE invoice_id = ?";

    private JdbcTemplate jdbcTemplate;

    public InvoiceDaoImplementation(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public Invoice createInvoice(Invoice invoice) {
        jdbcTemplate.update(INSERT_INVOICE_SQL, invoice.getCustomerId(), invoice.getPurchaseDate());
        int id = jdbcTemplate.queryForObject("select last_insert_id()", Integer.class);
        invoice.setInvoiceId(id);
        return invoice;
    }

    @Override
    public Invoice getInvoice(int id) {
        try{
            return jdbcTemplate.queryForObject(SELECT_INVOICE_SQL, this::mapToRowInvoice, id);
        }catch (EmptyResultDataAccessException e){
            return null;
        }
    }

    @Override
    public List<Invoice> getAllInvoices() {
        return jdbcTemplate.query(SELECT_ALL_INVOICES_SQL, this::mapToRowInvoice);
    }

    @Override
    public void updateInvoice(Invoice invoice) {
        jdbcTemplate.update(UPDATE_INVOICE_SQL, invoice.getCustomerId(), invoice.getPurchaseDate(), invoice.getInvoiceId());
    }

    @Override
    public void deleteInvoice(int id) {
        jdbcTemplate.update(DELETE_INVOICE_SQL, id);
    }

    private Invoice mapToRowInvoice(ResultSet rs, int rowNum)throws SQLException{
        Invoice invoice = new Invoice();
        invoice.setInvoiceId(rs.getInt("invoice_id"));
        invoice.setCustomerId(rs.getInt("customer_id"));
        invoice.setPurchaseDate(rs.getDate("purchase_date").toLocalDate());

        return invoice;
    }
}
