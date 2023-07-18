package com.msara.connection;

import com.msara.handler.ServicioHandler;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import com.sun.net.httpserver.HttpServer;

import java.io.IOException;
import java.net.InetSocketAddress;

public class Socket {
    public Socket() throws IOException {
        HttpServer server = HttpServer.create(new InetSocketAddress("localhost",8000), 0);

        //muestra todos los usuarios
        server.createContext("/usuarios", (HttpHandler) new ServicioHandler());

        //muestra un usuario por id, para modificar y para eliminar
        server.createContext("/usuario", (HttpHandler) new ServicioHandler());

        //crea un usuario
        server.createContext("/usuarios/crear", (HttpHandler) new ServicioHandler());

        server.setExecutor(null);
        server.start();
    }
}
