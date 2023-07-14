package com.msara.repository;

import com.msara.entity.UsuarioEntity;

import java.util.List;

public interface UsuarioRepository{

    UsuarioEntity encontrarPorId(Long id);

    List<UsuarioEntity> listarUsuario();

    void guardarUsuario(UsuarioEntity usuario);

    void actualizarUsuario(UsuarioEntity usuario);

    void eliminarUsuario(Long id);

}
