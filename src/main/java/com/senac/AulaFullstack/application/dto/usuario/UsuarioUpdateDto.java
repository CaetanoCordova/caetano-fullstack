package com.senac.AulaFullstack.application.dto.usuario;

import com.fasterxml.jackson.annotation.JsonProperty;

public record UsuarioUpdateDto (
        @JsonProperty("nome") String nome,
        @JsonProperty("email") String email,
        //@JsonProperty("senha") String senha,
        @JsonProperty("cpf") String cpf){
}
