package com.msara.handler;

import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;
import com.msara.entity.UsuarioEntity;
import com.msara.repository.impl.UsuarioRepositoryImpl;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import com.sun.org.apache.xerces.internal.impl.xpath.regex.Match;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ServicioHandler implements HttpHandler {

    private UsuarioRepositoryImpl inicio = new UsuarioRepositoryImpl();
    private UsuarioEntity usuario = new UsuarioEntity();
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
                    List<UsuarioEntity> listaUsuarios = inicio.listarUsuario();
                    String jsonUsuarios = new Gson().toJson(listaUsuarios);
                    OutputStream out = exchange.getResponseBody();
                    exchange.sendResponseHeaders(200, jsonUsuarios.length());
                    out.write(jsonUsuarios.getBytes());
                    out.flush();
                    out.close();
                } catch (IOException e) {
                    String respuesta = "Error al listar los usuarios";
                    exchange.sendResponseHeaders(404, respuesta.length());
                    OutputStream out = exchange.getResponseBody();
                    out.write(respuesta.getBytes());
                    out.flush();
                    out.close();
                }
            }
            else if(path.startsWith("/usuario/")) {
                try {
                    long idPath = obtenerIdPath(path);
                    usuario = inicio.encontrarPorId(idPath);
                    String jsonUsuairo = new Gson().toJson(usuario);
                    OutputStream out = exchange.getResponseBody();
                    exchange.sendResponseHeaders(200, jsonUsuairo.length());
                    out.write(jsonUsuairo.getBytes());
                    out.flush();
                    out.close();

                } catch (IOException e) {
                    String respuesta = "Error en la solicitud";
                    exchange.sendResponseHeaders(500, respuesta.length());
                    OutputStream out = exchange.getResponseBody();
                    out.write(respuesta.getBytes());
                    out.flush();
                    out.close();
                }
            }
        }

        public void post(HttpExchange exchange) throws IOException{
            path = exchange.getRequestURI().getPath();
            if(path.equals("/usuarios/crear")){
                try {
                    InputStreamReader isr = new InputStreamReader(exchange.getRequestBody(), StandardCharsets.UTF_8);
                    BufferedReader br = new BufferedReader(isr);
                    StringBuilder jsonBuilder = new StringBuilder();
                    String linea;

                    while ((linea = br.readLine()) != null){
                        jsonBuilder.append(linea);
                    }

                    String json = jsonBuilder.toString();

                    Gson gson = new Gson();
                    usuario = gson.fromJson(json, UsuarioEntity.class);

                    inicio.guardarUsuario(usuario);

                    String respuesta = "Usuarios agregado correctamente";
                    exchange.sendResponseHeaders(200, respuesta.length());
                    OutputStream out = exchange.getResponseBody();
                    out.write(respuesta.getBytes());
                    out.flush();
                    out.close();
                } catch (JsonSyntaxException e) {
                    String respuesta = "Error en el formato del JSON";
                    exchange.sendResponseHeaders(400, respuesta.length());
                    OutputStream out = exchange.getResponseBody();
                    out.write(respuesta.getBytes());
                    out.flush();
                    out.close();
                }
            }
        }

        public void put(HttpExchange exchange) {
            usuario = inicio.actualizarUsuario(usuario);
        }

        public void delete(HttpExchange exchange) {
            inicio.eliminarUsuario(usuario.getId());
        }

        //Con este metodo obtenemos el path id del usuario
        private long obtenerIdPath(String path){
            Pattern pattern = Pattern.compile("/usuario/(\\d+)");
            Matcher matcher = pattern.matcher(path);

            if(matcher.find()){
                String idString = matcher.group(1);
                return Long.parseLong(idString);
            }
            return -1;
        }
    }

