package com.photobook.data;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Test;

import java.io.IOException;

import static org.junit.Assert.assertEquals;

public class OrderTest {
    @Test
    public void serialisationTest() throws IOException {
        Order order = new Order("id", 1, "aaa", "bbb","A4",
                CoverType.DUST_JACKET, PaperType.MATTE, 23, 123.33, OrderStatus.CANCELLED);
        ObjectMapper mapper = new ObjectMapper();
        String orderString = mapper.writeValueAsString(order);

        Order resultOrder = mapper.readValue(orderString, Order.class);

        assertEquals(order.getId(), resultOrder.getId());
        assertEquals(order.getUserId(), resultOrder.getUserId());
        assertEquals(order.getDateCreated(), resultOrder.getDateCreated());
        assertEquals(order.getDateModified(), resultOrder.getDateModified());
        assertEquals(order.getSize(), resultOrder.getSize());
        assertEquals(order.getCover(), resultOrder.getCover());
        assertEquals(order.getPaper(), resultOrder.getPaper());
        assertEquals(order.getPages(), resultOrder.getPages());
        assertEquals(order.getPrice(), resultOrder.getPrice(), 1e-100);
        assertEquals(order.getStatus(), resultOrder.getStatus());


    }


}