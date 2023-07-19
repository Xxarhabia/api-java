package com.msara.handler;

import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;
import com.msara.entity.UsuarioEntity;
import com.msara.repository.impl.UsuarioRepositoryImpl;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;

import java.io.*;
import java.util.List;

public class ServicioHandler implements HttpHandler {

    private final UsuarioRepositoryImpl repository = new UsuarioRepositoryImpl();
    private UsuarioEntity usuario = new UsuarioEntity();
    private final Metodos metodo = new Metodos();
    private String path = "";

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

    public void get(HttpExchange exchange) throws IOException {
        path = exchange.getRequestURI().getPath();
        if(path.equals("/usuarios")){
            try {
                List<UsuarioEntity> listaUsuarios = repository.listarUsuario();
                String jsonUsuarios = new Gson().toJson(listaUsuarios);

                exchange.sendResponseHeaders(200, jsonUsuarios.length());
                metodo.out(jsonUsuarios, exchange);
            } catch (IOException e) {
                String respuesta = "Error al listar los usuarios";
                exchange.sendResponseHeaders(404, respuesta.length());
                metodo.out(respuesta, exchange);
            }
        }
        else if(path.startsWith("/usuario/")) {
            try {
                long idPath = metodo.obtenerIdPath(path);
                usuario = repository.encontrarPorId(idPath);
                String jsonUsuairo = new Gson().toJson(usuario);
                exchange.sendResponseHeaders(200, jsonUsuairo.length());
                metodo.out(jsonUsuairo, exchange);
            } catch (IOException e) {
                String respuesta = "Error en la solicitud";
                exchange.sendResponseHeaders(500, respuesta.length());
                metodo.out(respuesta, exchange);
            }
        }
    }

    public void post(HttpExchange exchange) throws IOException{
        path = exchange.getRequestURI().getPath();
        if(path.equals("/usuarios/crear")){
            try {
                String json = metodo.jsonToString(exchange);

                Gson gson = new Gson();
                usuario = gson.fromJson(json, UsuarioEntity.class);

                repository.guardarUsuario(usuario);

                String respuesta = "Usuarios agregado correctamente";
                exchange.sendResponseHeaders(200, respuesta.length());
                OutputStream out = exchange.getResponseBody();
                metodo.out(respuesta, exchange);
            } catch (JsonSyntaxException e) {
                String respuesta = "Error en el formato del JSON";
                exchange.sendResponseHeaders(400, respuesta.length());
                metodo.out(respuesta, exchange);
            }
        }
    }

    public void put(HttpExchange exchange) throws IOException {
        path = exchange.getRequestURI().getPath();
        if(path.startsWith("/usuario/")){
            try {
                long idPath = metodo.obtenerIdPath(path);

                String jsonString = metodo.jsonToString(exchange);

                Gson gson = new Gson();
                usuario = gson.fromJson(jsonString, UsuarioEntity.class);
                usuario.setId(idPath);
                repository.actualizarUsuario(usuario);

                String respuesta = "Usuario actualizado correctamente";
                exchange.sendResponseHeaders(200, respuesta.length());
                metodo.out(respuesta, exchange);
            } catch (JsonSyntaxException e) {
                String respuesta = "Error en el formato del JSON";
                exchange.sendResponseHeaders(400, respuesta.length());
                metodo.out(respuesta, exchange);
            }
        }
    }

    public void delete(HttpExchange exchange) throws IOException {
        path = exchange.getRequestURI().getPath();
        if(path.startsWith("/usuario/")){
            long idPath = metodo.obtenerIdPath(path);
            repository.eliminarUsuario(idPath);

            String respuesta = "El usuario fue eliminado correctamente";
            exchange.sendResponseHeaders(200, respuesta.length());
            metodo.out(respuesta, exchange);
        }
    }
}