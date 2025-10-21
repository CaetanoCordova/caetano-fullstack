package com.senac.AulaFullstack.application.dto.usuario;

import java.time.LocalDateTime;

public record UsuarioRequestDto (String nome, String cpf, String email, String senha
){

}
