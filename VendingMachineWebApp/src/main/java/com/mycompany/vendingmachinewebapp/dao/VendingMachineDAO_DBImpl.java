/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.vendingmachinewebapp.dao;

import com.mycompany.vendingmachinewebapp.dto.Change;
import com.mycompany.vendingmachinewebapp.dto.Item;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author apprentice
 */
public class VendingMachineDAO_DBImpl implements VendingMachineDAO {

    private static final String SQL_INSERT_ITEM
            = "INSERT INTO item (itemID, name, cost, quantity, info) VALUES (null, ?, ?, ?, ?)";
    private static final String SQL_DELETE_ITEM
            = "delete from item where itemID = ?";
    private static final String SQL_SELECT_ITEM
            = "select * from item where itemID = ?";
    private static final String SQL_UPDATE_ITEM
            = "update item set name = ?, cost = ?, quantity = ?, info = ? where itemID = ?";
    private static final String SQL_SELECT_ALL_ITEM
            = "select * from item";
    private static final String SQL_SELECT_MENU_ITEMS
            = "select * from item where quantity > 0";
    private static final String SQL_UPDATE_TRANSACTION
            = "INSERT INTO Transaction (orderDate, quantity, itemID) VALUES (now(), 1, ?)";

    private double balance;
    private JdbcTemplate jdbcTemplate;

    public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED, readOnly = false)
    public Item addItem(Item x) {
        jdbcTemplate.update(SQL_INSERT_ITEM,
                x.getName(), x.getCost(), x.getQuantity(), x.getInfo());
        return x;
    }

    @Override
    public Item getItemByID(int itemID) {
        try {
            return jdbcTemplate.queryForObject(SQL_SELECT_ITEM,
                    new ItemMapper(), itemID);
        } catch (EmptyResultDataAccessException ex) {
            return null;
        }
    }

    @Override
    public List<Item> returnAll() {
        return jdbcTemplate.query(SQL_SELECT_MENU_ITEMS, new ItemMapper());
    }

    @Override
    public void updateItem(Item x) {
        jdbcTemplate.update(SQL_UPDATE_ITEM,
                x.getName(), x.getCost(), x.getQuantity(), x.getInfo(), x.getItemID());
    }

    @Override
    public Change purchase(int item) {
        Item x = getItemByID(item);

        if (x.getCost() > balance) {
            Change y = new Change();
            y.setIsEnough(false);
            return y;
        } else {

            x.setQuantity(x.getQuantity() - 1);
            updateItem(x);
            jdbcTemplate.update(SQL_UPDATE_TRANSACTION, item);

            int p = (int) ((balance - x.getCost() + .005) * 100);
            int quarters = p / 25;
            int dimes = (p - quarters * 25) / 10;
            int nickels = (p - quarters * 25 - dimes * 10) / 5;
            int pennies = p - quarters * 25 - dimes * 10 - nickels * 5;
            balance = 0;
            return new Change(quarters, dimes, nickels, pennies);
        }
    }

    /**
     * @return the balance
     */
    @Override
    public double getBalance() {
        return balance;
    }

    /**
     * @param balance the balance to set
     */
    @Override
    public void setBalance(double balance) {
        this.balance = balance;
    }

    private static final class ItemMapper implements RowMapper<Item> {

        @Override
        public Item mapRow(ResultSet rs, int rowNum) throws SQLException {
            Item x = new Item();
            x.setItemID(rs.getInt("itemID"));
            x.setName(rs.getString("name"));
            x.setCost(rs.getDouble("cost"));
            x.setQuantity(rs.getInt("quantity"));
            x.setInfo(rs.getString("info"));
            return x;
        }
    }
}
