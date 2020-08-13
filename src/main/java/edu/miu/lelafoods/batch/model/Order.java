package edu.miu.lelafoods.batch.model;


import java.util.Date;

public class Order {

    private Long order_id;

    private Long order_quantity;

    private Long foodId;


    private Long total;

    public Long getTotal() {
        return total;
    }

    public void setTotal(Long total) {
        this.total = total;
    }

    public Long getOrder_id() {
        return order_id;
    }

    public void setOrder_id(Long order_id) {
        this.order_id = order_id;
    }

    public Long getOrder_quantity() {
        return order_quantity;
    }

    public void setOrder_quantity(Long order_quantity) {
        this.order_quantity = order_quantity;
    }

    public Long getFoodId() {
        return foodId;
    }

    public void setFoodId(Long foodId) {
        this.foodId = foodId;
    }
}
