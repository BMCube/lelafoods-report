package edu.miu.lelafoods.batch.model;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

public class Cart {

    private Long id;

    private Long customerId;

    private Long restaurantId;

    private Date orderDate;

    private String status;

    List<Order> order;

    public Cart() {

    }

    public Cart(Long customerId, Long restaurantId, Date orderDate, String status) {
        this.customerId = customerId;
        this.restaurantId = restaurantId;
        this.orderDate = orderDate;
        this.status = status;
    }

    public Cart(Long customerId, Long restaurantId, Date orderDate, String status, List<Order> orderList) {
        this.customerId = customerId;
        this.restaurantId = restaurantId;
        this.orderDate = orderDate;
        this.status = status;
        this.order = orderList;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getCustomerId() {
        return customerId;
    }

    public void setCustomerId(Long customerId) {
        this.customerId = customerId;
    }

    public Long getRestaurantId() {
        return restaurantId;
    }

    public void setRestaurantId(Long restaurantId) {
        this.restaurantId = restaurantId;
    }

    public List<Order> getOrder() {
        return order;
    }

    public void setOrder(List<Order> order) {
        this.order = order;
    }

    public Date getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(Date order) {
        this.orderDate = order;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }


    @Override
    public String toString() {
        return "Cart{" +
                "id=" + id +
                ", orderDate=" + orderDate +
                ", status='" + status + '\'' +
                ", orderList=" + order +
                '}';
    }
}