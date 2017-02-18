package com.photobook.data;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Test;

import com.photobook.data.PaymentMethod.*;
import java.io.IOException;

import static org.junit.Assert.assertEquals;

/**
 * Created by masya on 2/9/17.
 */
public class UserTest {
    @Test
    public void serialisationTest() throws Exception {
        User user = new User (3, "aaa", "abc@utki.net", "91234567",
                "punggol central blk ...", "820192", PaymentMethod.VISA);

        ObjectMapper mapper = new ObjectMapper();
        String userString = mapper.writeValueAsString(user);

        User resultUser = mapper.readValue(userString, User.class);

        assertEquals(user.getId(), resultUser.getId());
        assertEquals(user.getName(), resultUser.getName());
        assertEquals(user.getEmail(), resultUser.getEmail());
        assertEquals(user.getPhone(), resultUser.getPhone());
        assertEquals(user.getDeliveryAddress(), resultUser.getDeliveryAddress());
        assertEquals(user.getPostalCode(), resultUser.getPostalCode());
        assertEquals(user.getPaymentMethod(), resultUser.getPaymentMethod());

    }

    @Test
    public void userUpdateTest() throws Exception {
        User user = new User (3, "aaa", "abc@utki.net", "91234567",
                "punggol central blk ...", "820192", PaymentMethod.VISA);
        final String updateStr = "{\"id\":3,\"email\":\"cbd@utki.net\",\"paymentMethod\":\"MASTER\"}";
        ObjectMapper mapper = new ObjectMapper();
        final User userUpdate = mapper.readValue(updateStr, User.class);

        User resultUser = user.merge(userUpdate);

        assertEquals(3, resultUser.getId());
        assertEquals("aaa", resultUser.getName());
        assertEquals("cbd@utki.net", resultUser.getEmail());
        assertEquals("91234567", resultUser.getPhone());
        assertEquals("punggol central blk ...", resultUser.getDeliveryAddress());
        assertEquals("820192", resultUser.getPostalCode());
        assertEquals(PaymentMethod.MASTER, resultUser.getPaymentMethod());
    }

    @Test(expected = Exception.class)
    public void invalidIdThrows() throws Exception {
        User user = new User (-3, "aaa", "abc@utki.net", "91234567",
                "punggol central blk ...", "820192", PaymentMethod.VISA);

    }
}
