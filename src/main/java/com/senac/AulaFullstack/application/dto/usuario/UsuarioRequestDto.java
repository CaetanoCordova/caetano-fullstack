package com.senac.AulaFullstack.application.dto.usuario;

public record UsuarioRequestDto (Long id, String nome, String cpf, String email, String senha, String role){

}
