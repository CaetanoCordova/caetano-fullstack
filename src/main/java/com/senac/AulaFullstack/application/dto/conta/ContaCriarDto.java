package com.senac.AulaFullstack.application.dto.conta;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.senac.AulaFullstack.domain.entity.Usuario;

import java.util.Date;

public record ContaCriarDto(
    @JsonProperty("titulo") String titulo,
    @JsonProperty("descricao") String descricao,
    @JsonProperty("valor") Float valor,
    @JsonProperty("dataVencimento") Date dataVencimento,
    @JsonProperty("usuario_id") Usuario usuario){
}
