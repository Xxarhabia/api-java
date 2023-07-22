package com.msara.utils;

public class Constantes {

    //TODO cambiar las consultas sql tradicionales por un call para llamar al procedimiento almacenado

    public static final String SELECT_ID = "SELECT * FROM usuarios WHERE id=?";
    public static final String SELECT_USUARIOS = "SELECT * FROM usuarios";
    public static final String INSERT_USUARIO = "INSERT INTO usuarios(nombre, apellido, edad, telefono) VALUES(?,?,?,?)";
    public static final String UPDATE_USUARIO = "UPDATE usuarios SET nombre=?, apellido=?, edad=?, telefono=? WHERE id=?";
    public static final String DELETE_USUARIO = "DELETE FROM usuarios WHERE id=?";

    private Constantes(){}
}
