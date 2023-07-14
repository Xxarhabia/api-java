package com.msara.connection;

import com.msara.handler.ServicioHandler;
import com.sun.net.httpserver.HttpHandler;
import com.sun.net.httpserver.HttpServer;

import java.io.IOException;
import java.net.InetSocketAddress;

public class Socket {
    Socket () throws IOException {

        HttpServer server = HttpServer.create(new InetSocketAddress(8000), 0);
        // en esta parte se hace la instancia y a la vez se crea el socket
        // server.createContext("/babel", new MyHandler());
        //aca definimos la direccion por la cual sera accedida y la clase que ejecutara
        server.createContext("/apibank", (HttpHandler) new ServicioHandler());



        // se da el contexto
        server.setExecutor(null); // creates a default executor
        server.start();


    }
}
