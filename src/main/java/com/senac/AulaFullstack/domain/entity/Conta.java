package com.senac.AulaFullstack.domain.entity;

import com.senac.AulaFullstack.domain.enums.StatusConta;
import jakarta.persistence.*;
import jakarta.persistence.criteria.Fetch;
import lombok.*;

import java.util.Date;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Conta {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String titulo;
    private String descricao;
    private Float valor;
    private Date dataVencimento;

    @Enumerated(EnumType.STRING)
    private StatusConta statusConta;

    @ManyToOne//(fetch=FetchType.LAZY)
    @JoinColumn(name = "usuario_id")
    private Usuario usuario;
}
