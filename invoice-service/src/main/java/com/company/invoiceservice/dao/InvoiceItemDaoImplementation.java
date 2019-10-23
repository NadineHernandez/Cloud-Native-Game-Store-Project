package com.company.invoiceservice.dao;

import com.company.invoiceservice.dto.InvoiceItem;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Repository
public class InvoiceItemDaoImplementation implements InvoiceItemDao{
    //prepared statements
    public static final String INSERT_INVOICE_ITEM_SQL =
            "INSERT INTO invoice_item (invoice_id, inventory_id, quantity, unit_price) VALUES (?, ?, ?, ?)";

    public static final String SELECT_INVOICE_ITEM_SQL =
            "SELECT * FROM invoice_item WHERE invoice_item_id = ?";

    public static final String SELECT_ALL_INVOICE_ITEMS_SQL =
            "SELECT * FROM invoice_item";

    public static final String SELECT_INVOICES_BY_INVOICE_ID_SQL =
            "SELECT * FROM invoice_item WHERE invoice_id =?";

    public static final String UPDATE_INVOICE_ITEM_SQL =
            "UPDATE invoice_item SET invoice_id = ?, inventory_id = ?, quantity = ?, unit_price = ? WHERE invoice_item_id = ?";

    public static final String DELETE_INVOICE_ITEM_SQL =
            "DELETE FROM invoice_item WHERE invoice_item_id = ?";

    private JdbcTemplate jdbcTemplate;

    public InvoiceItemDaoImplementation(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public InvoiceItem createInvoiceItem(InvoiceItem invoiceItem) {
        jdbcTemplate.update(INSERT_INVOICE_ITEM_SQL, invoiceItem.getInvoice_id(), invoiceItem.getInventory_id(),
                invoiceItem.getQuantity(), invoiceItem.getUnit_price());
        int id = jdbcTemplate.queryForObject("select last_insert_id()", Integer.class);
        invoiceItem.setInvoice_item_id(id);
        return invoiceItem;
    }

    @Override
    public InvoiceItem getInvoiceItem(int id) {
        try{
            return jdbcTemplate.queryForObject(SELECT_INVOICE_ITEM_SQL, this::mapToRowInvoiceItem, id);
        }catch (EmptyResultDataAccessException e){
            return null;
        }
    }

    @Override
    public List<InvoiceItem> getAllInvoiceItems() {
        return jdbcTemplate.query(SELECT_ALL_INVOICE_ITEMS_SQL, this::mapToRowInvoiceItem);
    }

    @Override
    public List<InvoiceItem> getInvoiceItemsByInvoiceId(int id) {
        return jdbcTemplate.query(SELECT_INVOICES_BY_INVOICE_ID_SQL, this::mapToRowInvoiceItem, id);
    }

    @Override
    public void updateInvoiceItem(InvoiceItem invoiceItem) {
        jdbcTemplate.update(UPDATE_INVOICE_ITEM_SQL, invoiceItem.getInvoice_id(), invoiceItem.getInventory_id(),
                invoiceItem.getQuantity(), invoiceItem.getUnit_price(), invoiceItem.getInvoice_item_id());
    }

    @Override
    public void deleteInvoiceItem(int id) {
        jdbcTemplate.update(DELETE_INVOICE_ITEM_SQL, id);

    }

    private InvoiceItem mapToRowInvoiceItem(ResultSet rs, int rowNum)throws SQLException{
        InvoiceItem invoiceItem = new InvoiceItem();
        invoiceItem.setInvoice_item_id(rs.getInt("invoice_item_id"));
        invoiceItem.setInvoice_id(rs.getInt("invoice_id"));
        invoiceItem.setInventory_id(rs.getInt("inventory_id"));
        invoiceItem.setQuantity(rs.getInt("quantity"));
        invoiceItem.setUnit_price(rs.getBigDecimal("unit_price"));

        return invoiceItem;
    }
}
