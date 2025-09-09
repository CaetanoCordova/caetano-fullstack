package com.senac.AulaFullstack.controller;

import com.senac.AulaFullstack.model.Conta;
import com.senac.AulaFullstack.repository.ContaRepository;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/contas")
@Tag(name = "Controlador de contas bancárias", description = "Responsável por controlar os registros das contas (Criar, ler, atualizar).")
public class ContaController {

    @Autowired
    private ContaRepository contaRepository;

    @GetMapping("/{id}")
    @Operation(summary = "contas.", description = "Método que retorna uma conta específica pelo iD.")
    public ResponseEntity<Conta> consultaPorId(@PathVariable Long id) {
        var conta = contaRepository.findById(id).orElse(null);

        if (conta == null) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(conta);
    }

    @GetMapping
    @Operation(summary = "contas.", description = "Método que retorna todas as contas registradas.")

    //@Operation(summary = "contas.", description = "Método que calcula os custos da X e retorna o preço total da Y baseado em[...] (Regra de negócio).")
    public ResponseEntity<?> consultaTodos(){

        return ResponseEntity.ok(contaRepository.findAll());
    }

    @PostMapping
    @Operation(summary = "Cria/Salva alterações de contas.", description = "Método que cria as contas e as alterações em[...] (Regra de negócio placeholder).")
    public ResponseEntity<?> salvaConta(@RequestBody Conta conta){
        try{
            var contaResponse = contaRepository.save(conta);

            return ResponseEntity.ok(contaRepository);
        }catch(Exception e){
            return ResponseEntity.badRequest().build();
        }
    }

}
