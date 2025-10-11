package com.senac.AulaFullstack.application.dto.conta;

import java.util.Date;

public record ContaRequestDto (Long id, String titulo, String descricao, Float valor, Date dataVencimento){

}
