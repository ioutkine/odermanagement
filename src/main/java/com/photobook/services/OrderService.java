package com.photobook.services;

import com.photobook.data.Order;

/**
 * Created by masya on 2/9/17.
 */
public class OrderService {

    public void addOrder(Order order) {
        if (order.getUserId() <= 0) {
            throw new IllegalArgumentException("User ID must be greater than zero");
        }
        //todo add order to internal storage and generate new order id
    }

    public void modifyOrder (Order order){

    }

    public Order [] getOrders (int userId){
        return new Order [0];
    }

    public Order getOrder (String id) {
        return null;
    }

    public void deleteOrder (String id){

    }
}
