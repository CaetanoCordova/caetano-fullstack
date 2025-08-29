package com.senac.AulaFullstack.model;

import com.senac.AulaFullstack.enums.StatusConta;
import jakarta.persistence.*;

import java.util.Date;

@Entity
public class Conta {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;
    String descricao;
    float valor;
    Date dataVencimento;

    @Enumerated(EnumType.STRING)
    private StatusConta statusConta;
}
