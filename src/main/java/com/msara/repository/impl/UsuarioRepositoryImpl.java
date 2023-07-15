package com.msara.repository.impl;

import com.msara.connection.Conexion;
import com.msara.entity.UsuarioEntity;
import com.msara.repository.UsuarioRepository;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import static com.msara.utils.Constantes.*;

public class UsuarioRepositoryImpl implements UsuarioRepository {
    private Conexion conexion;

    @Override
    public UsuarioEntity encontrarPorId(Long id) {
        UsuarioEntity usuario = null;
        String query = SELECT_ID;

        try (PreparedStatement statement = conexion.getConexion().prepareStatement(query)){
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
        String query = SELECT_USUARIOS;

        try(Statement statement = conexion.getConexion().createStatement()) {
            ResultSet resultSet = statement.executeQuery(query);

            while(resultSet.next()) {
                UsuarioEntity usuario = new UsuarioEntity();
                usuario.setId(resultSet.getLong("id"));
                usuario.setNombre(resultSet.getString("nombre"));
                usuario.setNombre(resultSet.getString("apellido"));
                usuario.setEdad(resultSet.getInt("edad"));
                usuario.setTelefono(resultSet.getString("telefono"));
                listaUsuarios.add(usuario);
            }

        }catch (SQLException e) {
            e.printStackTrace();
        }

        return listaUsuarios;
    }

    @Override
    public void guardarUsuario(UsuarioEntity usuario) {
        String query = INSERT_USUARIO;

        try(PreparedStatement statement = conexion.getConexion().prepareStatement(query)){
            statement.setString(1, usuario.getNombre());
            statement.setString(2, usuario.getApellido());
            statement.setInt(3, usuario.getEdad());
            statement.setString(4, usuario.getTelefono());
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void actualizarUsuario(UsuarioEntity usuario) {
        String query = UPDATE_USUARIO;

        try(PreparedStatement statement = conexion.getConexion().prepareStatement(query)) {
            statement.setString(1, usuario.getNombre());
            statement.setString(2, usuario.getApellido());
            statement.setInt(3, usuario.getEdad());
            statement.setString(4, usuario.getTelefono());
            statement.setLong(5, usuario.getId());
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void eliminarUsuario(Long id) {
        String query = DELETE_USUARIO;

        try(PreparedStatement statement = conexion.getConexion().prepareStatement(query)){
            statement.setLong(1, id);
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
