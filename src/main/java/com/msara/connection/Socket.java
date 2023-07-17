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
        //muestra un usuario por id
        server.createContext("/usuarios/id", (HttpHandler) new ServicioHandler());
        //crea un usuario
        server.createContext("/usuarios/crear", (HttpHandler) new ServicioHandler());
        //modifica un usuario
        server.createContext("/usuarios/modificar", (HttpHandler) new ServicioHandler());
        //elimina un usuario mediante su id
        server.createContext("/usuarios/eliminar/id", (HttpHandler) new ServicioHandler());

        server.setExecutor(null);
        server.start();
    }
}
