package edu.miu.lelafoods.batch.processor;

import edu.miu.lelafoods.batch.model.Cart;
import org.springframework.batch.item.ItemProcessor;

public class CartItemProcessor implements ItemProcessor<Cart, Cart> {
    @Override
    public Cart process(Cart cart) throws Exception {

        return cart;
    }
}
