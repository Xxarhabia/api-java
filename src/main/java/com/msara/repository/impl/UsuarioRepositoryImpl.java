package com.msara.repository.impl;

import com.msara.connection.Conexion;
import com.msara.entity.UsuarioEntity;
import com.msara.repository.UsuarioRepository;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import static com.msara.connection.Conexion.getConexion;
import static com.msara.utils.Constantes.*;

public class UsuarioRepositoryImpl implements UsuarioRepository {
    private Conexion conexion;
    String query;

    @Override
    public UsuarioEntity encontrarPorId(Long id) {
        UsuarioEntity usuario = null;
        query = SELECT_ID;

        try (PreparedStatement statement = getConexion().prepareStatement(query)){
            statement.setLong(1, id);
            ResultSet resultSet = statement.executeQuery();

            if(resultSet.next()){
                usuario = new UsuarioEntity();
                usuario.setId(resultSet.getLong("id"));
                usuario.setNombre(resultSet.getString("nombre"));
                usuario.setApellido(resultSet.getString("apellido"));
                usuario.setEdad(resultSet.getInt("edad"));
                usuario.setTelefono(resultSet.getString("telefono"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return usuario;
    }

    @Override
    public List<UsuarioEntity> listarUsuario() {
        List<UsuarioEntity> listaUsuarios = new ArrayList<>();
        query = SELECT_USUARIOS;

        try(PreparedStatement statement = getConexion().prepareStatement(query)) {
            ResultSet resultSet = statement.executeQuery();

            while(resultSet.next()) {
                UsuarioEntity usuario = new UsuarioEntity();
                usuario.setId(resultSet.getLong("id"));
                usuario.setNombre(resultSet.getString("nombre"));
                usuario.setApellido(resultSet.getString("apellido"));
                usuario.setEdad(resultSet.getInt("edad"));
                usuario.setTelefono(resultSet.getString("telefono"));
                listaUsuarios.add(usuario);
            }
        }catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return listaUsuarios;
    }

    @Override
    public UsuarioEntity guardarUsuario(UsuarioEntity usuario) {
        query = INSERT_USUARIO;
        try(PreparedStatement statement = getConexion().prepareStatement(query)){
            statement.setString(1, usuario.getNombre());
            statement.setString(2, usuario.getApellido());
            statement.setInt(3, usuario.getEdad());
            statement.setString(4, usuario.getTelefono());

            int filasAfectadas = statement.executeUpdate();

            if(filasAfectadas > 0){
                System.out.println("Insercion exitosa");
            } else {
                System.out.println("No se insertaron filas");
            }

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return usuario;
    }

    @Override
    public UsuarioEntity actualizarUsuario(UsuarioEntity usuario) {
        query = UPDATE_USUARIO;

        try(PreparedStatement statement = getConexion().prepareStatement(query)) {
            statement.setString(1, usuario.getNombre());
            statement.setString(2, usuario.getApellido());
            statement.setInt(3, usuario.getEdad());
            statement.setString(4, usuario.getTelefono());
            statement.setLong(5, usuario.getId());
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return usuario;
    }

    @Override
    public void eliminarUsuario(Long id) {
        query = DELETE_USUARIO;

        try(PreparedStatement statement = getConexion().prepareStatement(query)){
            statement.setLong(1, id);
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
