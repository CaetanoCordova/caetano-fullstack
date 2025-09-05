package com.senac.AulaFullstack.service;

import com.senac.AulaFullstack.dto.LoginRequestDto;
import com.senac.AulaFullstack.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UsuarioService {

    @Autowired
    private UsuarioRepository usuarioRepository;

    public boolean validarSenha(LoginRequestDto login){
        return usuarioRepository.existsUsuarioByEmailContainingAndSenha(login.email(), login.senha());
    }
}
