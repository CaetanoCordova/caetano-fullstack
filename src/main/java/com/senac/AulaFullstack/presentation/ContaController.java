package com.senac.AulaFullstack.presentation;

import com.senac.AulaFullstack.application.dto.conta.ContaResponseDto;
import com.senac.AulaFullstack.application.service.ContaService;
import com.senac.AulaFullstack.domain.entity.Conta;
import com.senac.AulaFullstack.domain.repository.ContaRepository;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
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
    private ContaService contaService;

    @Autowired
    private ContaRepository contaRepository;

    @GetMapping("/{id}")
    @Operation(summary = "Retorna uma conta pelo iD.", description = "Método que retorna uma conta específica através do iD.")
    public ResponseEntity<Conta> consultaPorId(@PathVariable Long id) {
        var conta = contaRepository.findById(id).orElse(null);

        if (conta == null) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(conta);
    }

    @GetMapping
    @Operation(summary = "Retorna todas as contas existentes.", description = "Método que retorna todas as contas no banco.")

    //@Operation(summary = "contas.", description = "Méthodo que calcula os custos da X e retorna o preço total da Y baseado em[...] (Regra de negócio).")
    public ResponseEntity<?> consultaTodos() {

        return ResponseEntity.ok(contaRepository.findAll());
    }

    @GetMapping("/grid")
    @Operation(summary = "Retorna contas em páginas.", description = "Método que Retorna todas as contas em páginas determinadas.")
    public ResponseEntity<List<ContaResponseDto>> consultaTodos(
            @Parameter(description = "Parametro para a quantidade de registro por páginas.") @RequestParam Long take,
            @Parameter(description = "Parametro para a quantidade de páginas.") @RequestParam Long page,
            @Parameter(description = "Parametro do filtro.") @RequestParam(required = false) String filtro
    ){
        return ResponseEntity.ok(contaService.consultarPaginaDoFiltrado(take, page, filtro));
    }

    @PostMapping
    @Operation(summary = "Cria uma conta nova.", description = "Método que cria as contas.")
    public ResponseEntity<?> salvaConta(@RequestBody Conta conta) {
        try {
            var contaResponse = contaRepository.save(conta);

            return ResponseEntity.ok(contaResponse);
        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
    }

    @PutMapping("/{id}")
    @Operation(summary = "Atualiza uma conta existente.", description = "Método que atualiza os dados de uma conta já registrada.")
    public ResponseEntity<?> atualizaConta(@PathVariable Long id, @RequestBody Conta contaAtualizada) {
        return contaRepository.findById(id)
                .map(conta -> {
                    conta.setTitulo(contaAtualizada.getTitulo());
                    conta.setDescricao(contaAtualizada.getDescricao());
                    conta.setValor(contaAtualizada.getValor());
                    conta.setDataVencimento(contaAtualizada.getDataVencimento());
                    conta.setStatusConta(contaAtualizada.getStatusConta());

                    var contaSalva = contaRepository.save(conta);
                    return ResponseEntity.ok(contaSalva);
                })
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Remove uma conta existente.", description = "Método que deleta uma conta já registrada pelo ID.")
    public ResponseEntity<?> deletaConta(@PathVariable Long id) {
        return contaRepository.findById(id)
                .map(conta -> {
                    contaRepository.delete(conta);
                    return ResponseEntity.noContent().build();
                })
                .orElse(ResponseEntity.notFound().build());
    }
//    use este json
//    {
//        "titulo": "Conta de testes",
//        "descricao": "uma conta pendente teste",
//        "valor": "1000",
//        "dataVencimento": "2025-12-31",
//        "statusConta": "PENDENTE"
//    }
}