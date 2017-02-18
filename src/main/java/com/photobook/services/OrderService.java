package com.photobook.services;

import com.photobook.data.CoverType;
import com.photobook.data.Order;
import com.photobook.data.OrderStatus;
import com.photobook.data.PaperType;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

/**
 * Created by masya on 2/9/17.
 */
public class OrderService {

    private final Map<String, Order> ordersMap = new HashMap<>();

    public void addOrder(Order order) {
            final String newOrderId = UUID.randomUUID().toString();
            Order newOrder = new Order (newOrderId, order.getUserId(), order.getDateCreated(),order.getDateModified(), order.getSize(),
                    order.getCover(), order.getPaper(), order.getPages(), order.getPrice(), order.getStatus());

        ordersMap.put(newOrderId, newOrder);
        }

    public void modifyOrder (Order order){

    }

    public Order [] getOrders (int userId){
        return ordersMap.values().toArray(new Order[ordersMap.values().size()]);

    }

    public Order getOrder (String id) {

        return ordersMap.get(id);
    }

    public void deleteOrder (String id){
        ordersMap.remove(id);
    }
}
