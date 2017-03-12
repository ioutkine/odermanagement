package com.photobook.services;

import com.fasterxml.jackson.databind.jsontype.impl.AsWrapperTypeDeserializer;
import com.photobook.data.CoverType;
import com.photobook.data.Order;
import com.photobook.data.OrderStatus;
import com.photobook.data.PaperType;
import org.junit.Test;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import static org.junit.Assert.*;

/**
 * Created by masya on 2/15/17.
 */
public class OrderServiceTest {
    @Test
    public void addOrder() throws Exception {
        SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
        final String sss = formatter.format(new Date());


        final Order testOrder = new Order("test id", 123, "", "", "", CoverType.DUST_JACKET,
                PaperType.MATTE, 123, 12.12, OrderStatus.CANCELLED);
        final Order testOrder2 = new Order("test id", 124, "", "", "", CoverType.SOFTCOVER,
                PaperType.STANDARD, 133, 412.12, OrderStatus.NEW);

        OrderService os = new OrderService();
        os.addOrder(testOrder);
        os.addOrder(testOrder2);

        List<Order> userOrders = os.getOrders(123);
        assertNotNull(userOrders);
        assertEquals(1, userOrders.size());

        // here we have just one order
        final String orderIdJustCreated = userOrders.get(0).getId();

        Order retrievedOrder = os.getOrder(orderIdJustCreated);
        assertEquals(testOrder.getUserId(), retrievedOrder.getUserId());
        assertEquals(testOrder.getCover(), retrievedOrder.getCover());

        retrievedOrder = os.getOrder("random id");
        assertNull(retrievedOrder);
    }


    @Test
    public void getOrders() throws Exception {
        SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
        final String sss = formatter.format(new Date());


        final Order testOrder = new Order("test id", 123, "", "", "", CoverType.DUST_JACKET,
                PaperType.MATTE, 123, 12.12, OrderStatus.CANCELLED);
        final Order testOrder2 = new Order("test id", 124, "", "", "", CoverType.SOFTCOVER,
                PaperType.STANDARD, 133, 412.12, OrderStatus.NEW);

        OrderService os1 = new OrderService();
        os1.addOrder(testOrder);
        os1.addOrder(testOrder2);

        OrderService os2 = new OrderService();
        os2.addOrder(testOrder);
        os2.addOrder(testOrder2);

        assertNotNull(os1);
        assertNotNull(os2);
        assertEquals(os1, os2);

    }

    @Test
    public void getOrder() throws Exception {
        SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
        final String sss = formatter.format(new Date());


        final Order testOrder = new Order("test id", 123, "", "", "", CoverType.DUST_JACKET,
                PaperType.MATTE, 123, 12.12, OrderStatus.CANCELLED);
        final Order testOrder2 = new Order("test id", 124, "", "", "", CoverType.SOFTCOVER,
                PaperType.STANDARD, 133, 412.12, OrderStatus.NEW);

        assertNotNull(testOrder);
        assertNotNull(testOrder2);
        assertEquals(testOrder, testOrder2);
    }

    @Test
    public void deleteOrder() throws Exception {
        SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
        final String sss = formatter.format(new Date());


        final Order testOrder = new Order("test id", 123, "", "", "", CoverType.DUST_JACKET,
                PaperType.MATTE, 123, 12.12, OrderStatus.CANCELLED);
        final Order testOrder2 = new Order("test id", 124, "", "", "", CoverType.SOFTCOVER,
                PaperType.STANDARD, 133, 412.12, OrderStatus.NEW);

        OrderService os = new OrderService();
        os.addOrder(testOrder);
        os.addOrder(testOrder2);

        //remove
        os.deleteOrder("test id");

        List<Order> userOrders = os.getOrders(123);
        assertNotNull(userOrders);
        assertEquals(1, userOrders.size());

        // here we have just one order
        final String orderIdJustCreated = userOrders.get(0).getId();

        Order retrievedOrder = os.getOrder(orderIdJustCreated);
        assertEquals(testOrder.getUserId(), retrievedOrder.getUserId());
        assertEquals(testOrder.getCover(), retrievedOrder.getCover());

        retrievedOrder = os.getOrder("random id");
        assertNull(retrievedOrder);

    }

}