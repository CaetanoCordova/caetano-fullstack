package com.senac.AulaFullstack.application.dto.usuario;

public record AdmRequestDto (String secret, String nome, String cpf, String email, String senha, String cep, String endereco) {
}