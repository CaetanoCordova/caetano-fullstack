package com.senac.AulaFullstack.domain.repository;

import com.senac.AulaFullstack.domain.entity.Conta;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.Optional;

@Repository
public interface ContaRepository extends JpaRepository<Conta,Long> {

    Optional<Conta> findById(Long id);

    Optional<Conta> findByTitulo(String titulo);

    Optional<Conta> findByDataVencimento(Date dataVencimento);

    /*
    placeholders
    Optional<Conta> findByDataVencimento(Date dataVencimento);

    IDK boolean existsContaByPlaceholder(String descricao);
    */
}
