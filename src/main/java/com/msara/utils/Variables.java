package com.msara.utils;


import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;

public final class Variables {
    private static final String CONFIG_TEXT_FILE = "src\\main\\resources\\variables.properties";

    private Properties properties;

    public Variables(){
        properties = new Properties();
        cargarPropiedades();
    }

    private void cargarPropiedades() {
        try {
            properties.load(new FileReader(CONFIG_TEXT_FILE));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    //Metodos de error
    public String errorBadList(){
        return properties.getProperty("ERROR_BAD_LIST");
    }
    public String errorBadRequest(){
        return properties.getProperty("ERROR_BAD_REQUEST");
    }
    public String errorBadData(){
        return properties.getProperty("ERROR_BAD_DATA");
    }
    public String errorBadJsonFormat(){
        return properties.getProperty("ERROR_BAD_JSON_FORMAT");
    }


    public String okSuccessfulInsert(){
        return properties.getProperty("OK_SUCCESSFUL_INSERT");
    }
    public String okSuccessfulUpdate() { return properties.getProperty("OK_SUCCESSFUL_UPDATE"); }
    public String okSuccessfulDelete(){
        return properties.getProperty("OK_SUCCESSFUL_DELETE");
    }
}
