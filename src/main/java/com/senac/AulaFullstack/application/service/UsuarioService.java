package com.senac.AulaFullstack.application.service;

import com.senac.AulaFullstack.application.dto.login.LoginRequestDto;
import com.senac.AulaFullstack.application.dto.usuario.UsuarioResponseDto;
import com.senac.AulaFullstack.domain.entity.Usuario;
import com.senac.AulaFullstack.domain.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

import static java.util.Arrays.stream;

@Service
public class UsuarioService {

    @Autowired
    private UsuarioRepository usuarioRepository;

    public Usuario login(LoginRequestDto login){
        var usuario = usuarioRepository.findByEmailAndSenha(login.email(), login.senha()).orElse(null);

        if (usuario==null){
            throw new RuntimeException("Usuario e senha inválidos.");
        }
        return usuario;
    }

    public List<UsuarioResponseDto> consultarPaginaDoFiltrado(Long take, Long page, String filtro) {
        return usuarioRepository.findAll()
                .stream()
                .skip((long)page*take)
                .limit(take)
                .map(UsuarioResponseDto::new)
                .collect(Collectors.toList());
    }
}
