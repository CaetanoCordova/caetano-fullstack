package com.senac.AulaFullstack.domain.repository;

import com.senac.AulaFullstack.domain.entity.Conta;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Repository
public interface ContaRepository extends JpaRepository<Conta,Long> {

    List<Conta> findByUsuarioId(Long id);

    Optional<Conta> findById(Long id);

    Optional<Conta> findByTitulo(String titulo);

    Optional<Conta> findByDataVencimento(Date dataVencimento);
}
