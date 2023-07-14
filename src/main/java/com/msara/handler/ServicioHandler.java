package com.msara.handler;

import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;

import java.io.IOException;

public class ServicioHandler implements HttpHandler {

    @Override
    public void handle(HttpExchange he) throws IOException {
        he.getRequestMethod();
    }
}
