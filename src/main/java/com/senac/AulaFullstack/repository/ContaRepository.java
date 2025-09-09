package com.senac.AulaFullstack.repository;

import com.senac.AulaFullstack.model.Conta;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ContaRepository extends JpaRepository<Conta,Long> {
    /*
    placeholders
    Optional<Conta> findByDataVencimento(Date dataVencimento);

    IDK boolean existsContaByPlaceholder(String descricao);
    */
}
