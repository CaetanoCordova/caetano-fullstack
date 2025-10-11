package com.senac.AulaFullstack.application.dto.conta;

import com.senac.AulaFullstack.domain.entity.Conta;

import java.util.Date;

public record ContaResponseDto (Long id, String titulo, String descricao, Float valor, Date dataVencimento){

    public ContaResponseDto(Conta conta){
        this(
                conta.getId(),
                conta.getTitulo(),
                conta.getDescricao(),
                conta.getValor(),
                conta.getDataVencimento()
        );
    }
}
