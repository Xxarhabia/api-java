package com.msara.handler;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
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

        }

        public void get(HttpExchange exchange) {

            if(exchange.getRequestMethod() == "/usuarios"){
                try {
                    List<UsuarioEntity> listaUsuarios = inicio.listarUsuario();
                    Gson gson = new Gson();
                    String jsonUsuarios = gson.toJson(listaUsuarios);
                    OutputStream out = exchange.getResponseBody();
                    exchange.sendResponseHeaders(200, jsonUsuarios.length());
                    out.write(jsonUsuarios.getBytes());
                    out.flush();
                    out.close();
                } catch (IOException e) {
                    System.out.println(e.getMessage());
                }
            }
            else if(exchange.getRequestMethod() == "/usuarios/{id}") {
                usuario = inicio.encontrarPorId(usuario.getId());
                OutputStream out = exchange.getResponseBody();
            }
        }

        public void post(HttpExchange exchange) throws IOException {
            usuario = inicio.guardarUsuario(usuario);
            Gson gson = new Gson();
            String json = gson.toJson(usuario);
            OutputStream out = exchange.getResponseBody();
            exchange.sendResponseHeaders(200, json.length());
            out.write("Usuario guardado".getBytes());
            out.flush();
            out.close();
        }

        public void put(HttpExchange exchange) {
            usuario = inicio.actualizarUsuario(usuario);
        }

        public void delete(HttpExchange exchange) {
            inicio.eliminarUsuario(usuario.getId());
        }
    }

