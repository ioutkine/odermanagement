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
    protected void doGet( HttpServletRequest request,
                          HttpServletResponse response ) throws ServletException, IOException
    {
        // parse query string
        //todo consider moving the code to a separate method
        final String queryString = request.getQueryString();
        final String[] queryVariables = queryString.split("&");
        String userIdString = null;

        for (String variable : queryVariables) {
            final String[] parsedVariable = variable.split("=");
            if (parsedVariable.length != 2) {
                break;
            }
            if (parsedVariable[0].equals("userId")) {
                userIdString = parsedVariable[1];
                break;
            }
        }

        // check if user id is present in the request and return error if not
        if (userIdString == null) {
            response.sendError(HttpServletResponse.SC_BAD_REQUEST, "userId is not specified in the request.");
            response.flushBuffer();
            return;
        }

        int userId = Integer.parseInt(userIdString);
        final User requestedUser = userService.getUser(userId);
        if (requestedUser == null) {
            //todo check with the specification what error code you should return if user is not found
            response.sendError(HttpServletResponse.SC_NO_CONTENT, "User with ID " + userIdString + " is not found in the system.");
            response.flushBuffer();
            return;
        }

        // set proper response header and response status
        response.setContentType(APPLICATION_JSON.asString());
        response.setStatus(HttpServletResponse.SC_OK);

        mapper.writeValue(response.getOutputStream(), requestedUser);
        response.flushBuffer();
    }
}

