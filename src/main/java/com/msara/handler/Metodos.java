package com.msara.handler;

import com.sun.net.httpserver.HttpExchange;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.nio.charset.StandardCharsets;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Metodos {

    public long obtenerIdPath(String path){
        Pattern pattern = Pattern.compile("/usuario/(\\d+)");
        Matcher matcher = pattern.matcher(path);

        if(matcher.find()){
            String idString = matcher.group(1);
            return Long.parseLong(idString);
        }
        return -1;
    }

    public void out(String respuesta, HttpExchange exchange){
        try {
            OutputStream out = exchange.getResponseBody();
            out.write(respuesta.getBytes());
            out.flush();
            out.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public String jsonToString(HttpExchange exchange){
        try{
            InputStreamReader isr = new InputStreamReader(exchange.getRequestBody(), StandardCharsets.UTF_8);
            BufferedReader br = new BufferedReader(isr);
            StringBuilder jsonBuilder = new StringBuilder();
            String linea;

            while((linea = br.readLine()) != null){
                jsonBuilder.append(linea);
            }

            String json = jsonBuilder.toString();
            return json;

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
