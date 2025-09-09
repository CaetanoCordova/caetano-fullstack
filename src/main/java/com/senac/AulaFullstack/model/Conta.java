package com.senac.AulaFullstack.model;

import com.senac.AulaFullstack.enums.StatusConta;
import jakarta.persistence.*;

import java.util.Date;

@Entity
public class Conta {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;
    String titulo;
    String descricao;
    float valor;
    Date dataVencimento;
    //adiconar status ativado para simular exclusão

    @Enumerated(EnumType.STRING)
    private StatusConta statusConta;
}
