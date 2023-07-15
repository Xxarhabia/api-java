package com.msara.handler;

import com.msara.entity.UsuarioEntity;
import com.msara.repository.impl.UsuarioRepositoryImpl;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;

import java.io.IOException;
import java.io.OutputStream;
import java.util.List;

public class ServicioHandler implements HttpHandler {

    private UsuarioRepositoryImpl inicio;
    private UsuarioEntity usuario;

    @Override
    public void handle(HttpExchange he) throws IOException {

        switch (he.getRequestMethod()){
            case("GET"):
                get(he);
                break;
            case("POST"):
                post(he);
                break;
            case("PUT"):
                put(he);
                break;
            case("DELETE"):
                delete(he);
                break;
            default:
                throw new IllegalStateException("Unexpected value: " + he.getRequestMethod());
        }

        /*
        OutputStream os = he.getResponseBody();
        String m ="hola soy la api";
        he.sendResponseHeaders(200,-0);
        os.write("esta es la api".getBytes());
        os.close();
        */

        }

        public void get(HttpExchange exchange) {

            if(exchange.getRequestMethod() == "/usuarios"){
                List<UsuarioEntity> usuarios = inicio.listarUsuario();
                OutputStream out = exchange.getResponseBody();

            }
            else if(exchange.getRequestMethod() == "/usuarios/{id}") {
                usuario = inicio.encontrarPorId(usuario.getId());
                OutputStream out = exchange.getResponseBody();
            }
        }

        public void post(HttpExchange exchange) throws IOException {
            usuario = inicio.guardarUsuario(usuario);
            OutputStream out = exchange.getResponseBody();
            exchange.sendResponseHeaders(200, usuario.toString().length());
            //out.write();
        }

        public void put(HttpExchange exchange) {
            usuario = inicio.actualizarUsuario(usuario);
        }

        public void delete(HttpExchange exchange) {
            inicio.eliminarUsuario(usuario.getId());
        }
    }

