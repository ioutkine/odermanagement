package com.photobook.web;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.photobook.data.User;
import com.photobook.services.UserNotFoundException;
import com.photobook.services.UserService;
import com.photobook.services.UserServiceException;

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
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        final String pathInfo = request.getPathInfo();
        if (pathInfo == null || pathInfo.equals("/")) {
            // set proper response header and response status
            response.setContentType(APPLICATION_JSON.asString());
            response.setStatus(HttpServletResponse.SC_OK);
            mapper.writeValue(response.getOutputStream(), userService.getUsers());
        }
        else
        {
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

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
    {
        final User newUser = mapper.readValue(request.getInputStream(), User.class);
        try {
            userService.addUser(newUser);
            response.setStatus(HttpServletResponse.SC_OK);
            mapper.writeValue(response.getOutputStream(), newUser.getId());
        } catch (UserServiceException ex) {
            response.sendError(HttpServletResponse.SC_CONFLICT, ex.getMessage());
        }
    }

    @Override
    protected void doPut(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
    {
        final User userToModify = mapper.readValue(request.getInputStream(), User.class);
        try {
            userService.modifyUser(userToModify);
            response.setStatus(HttpServletResponse.SC_OK);
            mapper.writeValue(response.getOutputStream(), userToModify.getId());
        }
        catch (UserServiceException ex)
        {
            response.sendError(HttpServletResponse.SC_CONFLICT, ex.getMessage());
        }
        catch (UserNotFoundException ex) {
            response.sendError(HttpServletResponse.SC_NOT_FOUND, "User with ID " + userToModify.getId() + " is not found in the system.");
        }
    }

    @Override
    protected void doDelete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        final String pathInfo = request.getPathInfo();
        if (pathInfo == null || pathInfo.equals("/")) {
            // set proper response header and response status
            response.sendError(HttpServletResponse.SC_NOT_FOUND, "User ID must be provided.");
        }

        else {
            // set proper response header and response status
            final int userId = Integer.parseInt(pathInfo.substring(1));
            try
            {
                userService.deleteUser(userId);
                response.setStatus(HttpServletResponse.SC_OK);
            }
            catch (UserNotFoundException ex)
            {
                response.sendError(HttpServletResponse.SC_NOT_FOUND, "User with ID " + userId + " is not found in the system.");

                }
            }
        }
}
