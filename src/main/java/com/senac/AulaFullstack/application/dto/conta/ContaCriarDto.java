package com.senac.AulaFullstack.application.dto.conta;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Date;

public record ContaCriarDto(
    @JsonProperty("titulo") String titulo,
    @JsonProperty("descricao") String descricao,
    @JsonProperty("valor") String valor,
    @JsonProperty("dataVencimento") Date dataVencimento){
}
