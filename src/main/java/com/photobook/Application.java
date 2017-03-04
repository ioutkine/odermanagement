package com.photobook;

import com.photobook.services.UserService;
import com.photobook.web.SimpleServer;
import com.photobook.web.UserServiceServlet;

/**
 * Created by duck on 29/1/17.
 */
public class Application {
    public static void main(String[] args) throws Exception {

        final UserService userService = new UserService();
        final UserServiceServlet userServiceServlet = new UserServiceServlet(userService);
        final SimpleServer webServer = new SimpleServer(8080);
        webServer.registerServlet(userServiceServlet, "/user/*");

        webServer.start();


        //final String userString = mapper.writeValue(user, User.class);
        // webServer.writeString(user);
        final String s = "{\"id\":\"123\"}";

    }
}
