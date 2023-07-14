package com.msara.repository.impl;

import com.msara.connection.Conexion;
import com.msara.entity.UsuarioEntity;
import com.msara.repository.UsuarioRepository;

import java.util.List;

import static com.msara.utils.Constantes.*;

public class UsuarioRepositoryImpl implements UsuarioRepository {
    private Conexion start = new Conexion();

    @Override
    public UsuarioEntity encontrarPorId(Long id) {
        UsuarioEntity usuario = null;
        String query = SELECT_ID;


       start.getConexion();

        return null;
    }

    @Override
    public List<UsuarioEntity> listarUsuario() {
        return null;
    }

    @Override
    public void guardarUsuario(UsuarioEntity usuario) {

    }

    @Override
    public void actualizarUsuario(UsuarioEntity usuario) {

    }

    @Override
    public void eliminarUsuario(Long id) {

    }
}
