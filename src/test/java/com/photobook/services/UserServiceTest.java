package com.photobook.services;

import com.photobook.data.PaymentMethod;
import com.photobook.data.User;
import org.junit.Test;

import java.util.UUID;

import static org.junit.Assert.*;

/**
 * Created by masya on 2/13/17.
 */
public class UserServiceTest {

    @Test
    public void addUser() throws Exception {
        UserService service = new UserService();

        User u1 = new User(0, "n1", "email", "123", "Punggol", "123456", PaymentMethod.VISA);
        service.addUser(u1);
        User u2 = new User(0, "n1", "email2", "1232", "Punggol2", "123456", PaymentMethod.VISA);
        service.addUser(u2);

        assertEquals(0, service.getUser(0).getId());
        assertEquals(1, service.getUser(1).getId());

        //System.out.println(UUID.randomUUID().toString());
    }

    @Test(expected = UserServiceException.class)
    public void addUserNoEmail() throws Exception {
        UserService service = new UserService();

        User u1 = new User(0, "n1", null, "123", "Punggol", "123456", PaymentMethod.VISA);
        service.addUser(u1);
        assertEquals(2, service.getUsers().length);

        assertEquals(0, service.getUser(0).getId());
        assertEquals(1, service.getUser(1).getId());

        //System.out.println(UUID.randomUUID().toString());
    }

    @Test(expected = UserServiceException.class)
    public void addUserDuplicateEmail() throws Exception {
        UserService service = new UserService();

        User u1 = new User(0, "n1", "email", "123", "Punggol", "123456", PaymentMethod.VISA);
        service.addUser(u1);
        assertEquals(1, service.getUsers().length);

        User u2 = new User(0, "n1", "email", "1232", "Punggol2", "123456", PaymentMethod.VISA);
        service.addUser(u2);
    }

}