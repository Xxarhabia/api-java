package com.msara.repository;

import com.msara.entity.UsuarioEntity;

import java.util.List;

public interface UsuarioRepository{

    UsuarioEntity encontrarPorId(Long id);

    List<UsuarioEntity> listarUsuario();

    UsuarioEntity guardarUsuario(UsuarioEntity usuario);

    UsuarioEntity actualizarUsuario(UsuarioEntity usuario);

    void eliminarUsuario(Long id);

}
