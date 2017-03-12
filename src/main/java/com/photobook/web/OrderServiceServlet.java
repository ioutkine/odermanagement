package com.photobook.web;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.photobook.data.Order;
import com.photobook.services.OrderNotFoundException;
import com.photobook.services.OrderService;
import com.photobook.services.OrderServiceException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import java.util.Map;

import static org.eclipse.jetty.http.MimeTypes.Type.APPLICATION_JSON;

/**
 * Created by masya on 3/4/17.
 */
public class OrderServiceServlet extends HttpServlet {

    private final OrderService orderService;
    private final ObjectMapper mapper = new ObjectMapper();

    public OrderServiceServlet(OrderService orderService) {
        this.orderService = orderService;
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response ) throws ServletException, IOException
    {
        final String pathInfo = request.getPathInfo();
        if (pathInfo == null || pathInfo.equals("/")) {
            final String[] userArguments = request.getParameterMap().get("user");
            if (userArguments == null) {
                response.sendError(HttpServletResponse.SC_BAD_REQUEST, "required parameter 'user' is not found.");
            } else {
                response.setContentType(APPLICATION_JSON.asString());
                List<Order> userOrders = orderService.getOrders(Integer.parseInt(userArguments[0]));

                mapper.writeValue(response.getOutputStream(), userOrders);
                response.setStatus(HttpServletResponse.SC_OK);
            }

        }
        else
        {
            final Order requestedOrder = orderService.getOrder(pathInfo.substring(1));
            if (requestedOrder == null) {
                response.sendError(HttpServletResponse.SC_NOT_FOUND, "Order with ID " + pathInfo.substring(1) + " is not found in the system.");
            }
            else
            {
                // set proper response header and response status
                response.setContentType(APPLICATION_JSON.asString());
                response.setStatus(HttpServletResponse.SC_OK);
                mapper.writeValue(response.getOutputStream(), requestedOrder);
            }
        }
        response.flushBuffer();
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
    {
        final Order newOrder = mapper.readValue(request.getInputStream(), Order.class);
        try {
            orderService.addOrder(newOrder);
            response.setStatus(HttpServletResponse.SC_OK);
            mapper.writeValue(response.getOutputStream(), newOrder.getId());
        } catch (OrderServiceException ex) {
            response.sendError(HttpServletResponse.SC_CONFLICT, ex.getMessage());
        }
    }

    @Override
    protected void doPut(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
    {
        final Order orderToModify = mapper.readValue(request.getInputStream(), Order.class);
        try {
            orderService.modifyOrder(orderToModify);
            response.setStatus(HttpServletResponse.SC_OK);
            mapper.writeValue(response.getOutputStream(), orderToModify.getId());
        }
        catch (OrderServiceException ex)
        {
            response.sendError(HttpServletResponse.SC_CONFLICT, ex.getMessage());
        }
        catch (OrderNotFoundException ex) {
            response.sendError(HttpServletResponse.SC_NOT_FOUND, "Order with ID " + orderToModify.getId() + " is not found in the system.");
        }
    }

    @Override
    protected void doDelete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        final String pathInfo = request.getPathInfo();
        if (pathInfo == null || pathInfo.equals("/")) {
            // set proper response header and response status
            response.sendError(HttpServletResponse.SC_NOT_FOUND, "Order ID must be provided.");
        }

        else {
            // set proper response header and response status
            final String orderId = pathInfo.substring(1);
            try
            {
                orderService.deleteOrder(orderId);
                response.setStatus(HttpServletResponse.SC_OK);
            }
            catch (OrderNotFoundException ex)
            {
                response.sendError(HttpServletResponse.SC_NOT_FOUND, "Order with ID " + orderId + " is not found in the system.");

            }
        }
    }
}
