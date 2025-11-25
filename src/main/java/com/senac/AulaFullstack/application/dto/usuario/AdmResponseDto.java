package com.senac.AulaFullstack.application.dto.usuario;

import com.senac.AulaFullstack.domain.entity.Usuario;

public record AdmResponseDto (String nome, String cpf, String email, String senha) {

    public AdmResponseDto(Usuario usuario){
        this(
                usuario.getNome(),
                usuario.getCpf(),
                usuario.getEmail(),
                usuario.getSenha()
        );
    }
}