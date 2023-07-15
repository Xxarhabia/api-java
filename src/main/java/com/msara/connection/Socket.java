package com.msara.connection;

import com.msara.handler.ServicioHandler;
import com.sun.net.httpserver.HttpHandler;
import com.sun.net.httpserver.HttpServer;

import java.io.IOException;
import java.net.InetSocketAddress;

public class Socket {
    Socket () throws IOException {

        HttpServer server = HttpServer.create(new InetSocketAddress(8000), 0);

        server.createContext("/apibank", (HttpHandler) new ServicioHandler());

        server.setExecutor(null);
        server.start();
    }
}
