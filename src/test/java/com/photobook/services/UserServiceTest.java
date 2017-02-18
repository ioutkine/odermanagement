package com.photobook.services;

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

        User u1 = new User(0, "n1", null, null, null, null, null);
        User u2 = new  User(0, "n2", null, null, null, null, null);
        service.addUser(u1);
        service.addUser(u2);
        assertEquals(2, service.getUsers().length);

        assertEquals(0, service.getUser(0).getId());
        assertEquals(1, service.getUser(1).getId());

        System.out.println(UUID.randomUUID().toString());
    }

}