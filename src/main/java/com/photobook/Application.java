package com.photobook;

import com.photobook.services.OrderService;
import com.photobook.services.UserService;
import com.photobook.web.OrderServiceServlet;
import com.photobook.web.SimpleServer;
import com.photobook.web.UserServiceServlet;

/**
 * Created by duck on 29/1/17.
 */
public class Application {
    public static void main(String[] args) throws Exception {

        final UserService userService = new UserService();
        final UserServiceServlet userServiceServlet = new UserServiceServlet(userService);
        final OrderService orderService = new OrderService();
        final OrderServiceServlet orderServiceServlet = new OrderServiceServlet(orderService);
        final SimpleServer webServer = new SimpleServer(8080);
        webServer.registerServlet(userServiceServlet, "/users/*");
        webServer.registerServlet(orderServiceServlet, "/orders/*");

        webServer.start();

    }
}
