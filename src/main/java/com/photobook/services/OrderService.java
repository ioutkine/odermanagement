package com.photobook.services;

import com.photobook.data.CoverType;
import com.photobook.data.Order;
import com.photobook.data.OrderStatus;
import com.photobook.data.PaperType;

import java.text.SimpleDateFormat;
import java.util.*;

/**
 * Created by masya on 2/9/17.
 */
public class OrderService {

    private final Map<String, Order> ordersMap = new HashMap<>();
    private SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");

    public void addOrder(Order order) throws OrderServiceException {
            final String newOrderId = UUID.randomUUID().toString();
        if (order.getSize() == null && order.getSize().isEmpty())
        {
            throw new OrderServiceException("Order size should be provided");
        }
        if (order.getCover() == null )
        {
            throw new OrderServiceException("Order cover type should be provided");
        }
        if (order.getPaper() == null )
        {
            throw new OrderServiceException("Order paper type should be provided");
        }
        if (order.getPages() <= 0 )
        {
            throw new OrderServiceException("Order pages should not be less than or equal to 0");
        }
        if (order.getPrice() <= 0 )
        {
            throw new OrderServiceException("Order price should not be less than or equal to 0");
        }
        if (order.getStatus() == null )
        {
            throw new OrderServiceException("Order status type should be provided");
        }

        final String createdDateString  = formatter.format(new Date());
        final String modifiedDateString  = formatter.format(new Date());

            Order newOrder = new Order (newOrderId, order.getUserId(), order.getDateCreated(),order.getDateModified(),
                    order.getSize(), order.getCover(), order.getPaper(), order.getPages(), order.getPrice(), order.getStatus());

        ordersMap.put(newOrderId, newOrder);
        }

        private boolean checkStatusTransition (OrderStatus oldState, OrderStatus newState)
        {
            switch(oldState) {
                case NEW:
                    return newState == OrderStatus.PAID || newState == OrderStatus.CANCELLED;
                case PAID:
                    return newState == OrderStatus.IN_PRODUCTION || newState == OrderStatus.CANCELLED;
                case IN_PRODUCTION:
                    return newState == OrderStatus.DELIVERED || newState == OrderStatus.CANCELLED;
                case DELIVERED:
                    return false;
                case CANCELLED:
                    return false;
                default:
                    return false;
            }

        }

    public void modifyOrder (Order orderWithModifiedFields)
            throws OrderServiceException, OrderNotFoundException
    {
        final Order existingOrder = ordersMap.get(orderWithModifiedFields.getId());
        if (existingOrder == null) {
            throw new OrderNotFoundException();
        }

        if (!checkStatusTransition(existingOrder.getStatus(), orderWithModifiedFields.getStatus()))
        {
            throw new OrderServiceException("Transition from " + existingOrder.getStatus() + " to " +
                    orderWithModifiedFields.getStatus() + " is not possible.");
        }

        final Order modifiedOrder = existingOrder.merge(orderWithModifiedFields);
        ordersMap.put(existingOrder.getId(), modifiedOrder);

    }

    public List<Order> getOrders (int userId){
        List<Order> orderList = new ArrayList<>();
        for (Order o : ordersMap.values()) {
            if (userId == o.getUserId()) {
                orderList.add(o);
            }
        }
        return orderList;
    }

    public Order getOrder (String id) {

        return ordersMap.get(id);
    }

    public void deleteOrder (String id) throws OrderNotFoundException
    {
        if (ordersMap.remove(id) == null)
        {
            throw new OrderNotFoundException();
        }
    }
}

