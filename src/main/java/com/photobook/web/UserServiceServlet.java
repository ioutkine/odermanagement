package com.photobook.web;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.photobook.data.User;
import com.photobook.services.UserService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static org.eclipse.jetty.http.MimeTypes.Type.APPLICATION_JSON;

public class UserServiceServlet extends HttpServlet {

    private final UserService userService;
    private final ObjectMapper mapper = new ObjectMapper();

    public UserServiceServlet(UserService userService) {
        this.userService = userService;
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response ) throws ServletException, IOException
    {
        final String pathInfo = request.getPathInfo();
        if (pathInfo == null || pathInfo.equals("/")) {
            //todo implement getAllUsers logic
            response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Not implemented.");
        } else {
            final int userId = Integer.parseInt(pathInfo.substring(1));
            final User requestedUser = userService.getUser(userId);
            if (requestedUser == null) {
                response.sendError(HttpServletResponse.SC_NOT_FOUND, "User with ID " + userId + " is not found in the system.");
            } else {
                // set proper response header and response status
                response.setContentType(APPLICATION_JSON.asString());
                response.setStatus(HttpServletResponse.SC_OK);
                mapper.writeValue(response.getOutputStream(), requestedUser);
            }
        }
        response.flushBuffer();
    }
}

