package com.senac.AulaFullstack.application.dto.usuario;

import com.fasterxml.jackson.annotation.JsonProperty;

public record UsuarioCriarDto (
    @JsonProperty("nome") String nome,
    @JsonProperty("email") String email,
    @JsonProperty("cpf") String cpf,
    @JsonProperty("senha") String senha,
    @JsonProperty("role") String role) {
    }
