package com.senac.AulaFullstack.service;

import com.senac.AulaFullstack.dto.LoginRequestDto;
import com.senac.AulaFullstack.model.Usuario;
import com.senac.AulaFullstack.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UsuarioService {
    @Autowired
    private UsuarioRepository usuarioRepository;

    public Usuario login(LoginRequestDto login){
        var usuario = usuarioRepository.findByEmailAndSenha(login.email(), login.senha()).orElse(null);

        if (usuario==null){
            throw new RuntimeException("Usuario e senha inválidos. UsuarioService.");
        }
        return usuario;
    }
}
