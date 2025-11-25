package com.senac.AulaFullstack.application.dto.conta;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.senac.AulaFullstack.domain.entity.Usuario;
import com.senac.AulaFullstack.domain.enums.StatusConta;

import java.util.Date;

public record ContaAtualizarDto(
        @JsonProperty("titulo") String titulo,
        @JsonProperty("descricao") String descricao,
        @JsonProperty("valor") Float valor,
        @JsonProperty("dataVencimento") Date dataVencimento,
        @JsonProperty("statusConta") StatusConta statusConta){
}

