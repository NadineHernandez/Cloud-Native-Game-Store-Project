package com.company.inventoryservice.dao;

import com.company.inventoryservice.dto.Inventory;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Repository
public class InventoryDaoJdbcImpl implements InventoryDao{
    //prepared statements
    public static final String INSERT_INVENTORY_SQL =
            "INSERT INTO inventory (product_id, quantity) VALUES (?, ?)";

    public static final String SELECT_INVENTORY_SQL =
            "SELECT * FROM inventory WHERE inventory_id = ?";

    public static final String SELECT_ALL_INVENTORIES_SQL =
            "SELECT * FROM inventory";

    public static final String UPDATE_INVENTORY =
            "UPDATE inventory SET product_id = ?, quantity = ? WHERE inventory_id = ?";

    public static final String DELETE_INVENTORY =
            "DELETE FROM inventory WHERE inventory_id = ?";

    private JdbcTemplate jdbcTemplate;

    public InventoryDaoJdbcImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public Inventory createInventory(Inventory inventory) {
        jdbcTemplate.update(INSERT_INVENTORY_SQL, inventory.getProduct_id(), inventory.getQuantity());
        int id = jdbcTemplate.queryForObject("select last_insert_id()", Integer.class);
        inventory.setInventory_id(id);
        return inventory;
    }

    @Override
    public Inventory getInventory(int id) {
        try{
            return jdbcTemplate.queryForObject(SELECT_INVENTORY_SQL,this::mapToRowInventory, id);
        } catch (EmptyResultDataAccessException e) {
            return null;
        }
    }

    @Override
    public List<Inventory> getAllInventories() {
        return jdbcTemplate.query(SELECT_ALL_INVENTORIES_SQL, this::mapToRowInventory);
    }

    @Override
    public void updateInventory(Inventory inventory) {
        jdbcTemplate.update(UPDATE_INVENTORY, inventory.getProduct_id(), inventory.getQuantity(), inventory.getInventory_id());
    }

    @Override
    public void deleteInventory(int id) {
        jdbcTemplate.update(DELETE_INVENTORY, id);
    }

    private Inventory mapToRowInventory(ResultSet rs, int rowNum) throws SQLException{
        Inventory inventory = new Inventory();
        inventory.setInventory_id(rs.getInt("inventory_id"));
        inventory.setProduct_id(rs.getInt("product_id"));
        inventory.setQuantity(rs.getInt("quantity"));

        return inventory;
    }
}
