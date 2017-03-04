package com.photobook.web;

import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.servlet.ServletHandler;
import org.eclipse.jetty.servlet.ServletHolder;

import javax.servlet.http.HttpServlet;

public class SimpleServer {
    private final Server server;
    private final ServletHandler handler;

    public SimpleServer(final int port) {
        this.server = new Server(port);
        handler = new ServletHandler();
        server.setHandler(handler);
    }

    public void registerServlet(final HttpServlet servlet, final String pathSpec) {
        handler.addServletWithMapping(new ServletHolder(servlet), pathSpec);
    }

    public void start() throws Exception {
        server.start();
        server.join();
    }

}
