package edu.miu.lelafoods.batch.config;

import edu.miu.lelafoods.batch.model.Cart;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class CartRowMapper implements RowMapper<Cart> {
    @Override
    public Cart mapRow(ResultSet resultSet, int i) throws SQLException {
        Cart cart = new Cart();
        cart.setId(resultSet.getLong("cart_id"));
        cart.setCustomerId(resultSet.getLong("customerId"));
        cart.setRestaurantId(resultSet.getLong("restaurantId"));
        cart.setStatus(resultSet.getString("status"));
        return cart;
    }
}
