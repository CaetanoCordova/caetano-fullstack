package com.senac.AulaFullstack.application.service;

import com.senac.AulaFullstack.application.dto.conta.ContaCriarDto;
import com.senac.AulaFullstack.application.dto.conta.ContaResponseDto;
import com.senac.AulaFullstack.domain.entity.Conta;
import com.senac.AulaFullstack.domain.enums.StatusConta;
import com.senac.AulaFullstack.domain.repository.ContaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ContaService {

    @Autowired
    private ContaRepository contaRepository;

    // Metodo para registro de contas
    public Conta registroConta(ContaCriarDto contaDto) {
        return contaRepository.save(new Conta(null, contaDto.titulo(), contaDto.descricao(), contaDto.valor(), contaDto.dataVencimento(), StatusConta.PENDENTE));
    }


    //TODO
    // Metodo responsavel para admin ver todas os status de contas
    //public List<Conta> visualizarContasGeral(){
        //return contaRepository.findAll();
    //}

    //TODO
    // Metodo responsavel para admin ver contas por id
    //public Optional<Conta> visualizarContasPorId(Long id){
        //return contaRepository.findById(id);
    //}

    //TODO
    // Metodo responsavel para admin e assinante ver o status de sua conta
    //public Optional<Conta> visualizarContas(Long usuarioId){
        //return contaRepository.findById(usuarioId);
    //}

    public List<ContaResponseDto> consultarPaginaDoFiltrado(Long take, Long page, String filtro) {
        return contaRepository.findAll()
                .stream()
                .skip((long)page*take)
                .limit(take)
                .map(ContaResponseDto::new)
                .collect(Collectors.toList());
    }

    //TODO Funções: Implementar a função para "pagar" uma conta e uma função que verifica e atualiza contas para o status VENCIDA.

    //pagaConta
    //if (conta paga){
    //status= paga
    //}

    //venceConta
    //if (contaData < hoje){
    //status= vencida
    //}

    //Bonus opcional: checar por status, notificar automaticamente quando a data de vencimento se aproxima.

    //alertaConta
    //if (contaData >= hoje && < hoje+3 dias){
    //status= vencida
    //}
}
