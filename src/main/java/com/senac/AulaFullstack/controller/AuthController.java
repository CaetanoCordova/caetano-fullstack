package com.senac.AulaFullstack.controller;

import com.senac.AulaFullstack.dto.LoginRequestDto;
import com.senac.AulaFullstack.dto.LoginResponseDto;
import com.senac.AulaFullstack.service.TokenService;
import com.senac.AulaFullstack.service.UsuarioService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/auth")
@Tag(name = "Controlador de autenticação", description = "Responsável por controlar a autenticação dos usuários.")
public class AuthController {

    @Autowired
    private TokenService tokenService;

    @Autowired
    private UsuarioService usuarioService;

    @PostMapping("/login")
    @Operation(summary = "login", description = "Método responsável pelo login")
    public ResponseEntity<?> login(@RequestBody LoginRequestDto request){

        try {
            var usuario = usuarioService.login(request);

            var token = tokenService.gerarToken(usuario);

            return ResponseEntity.ok(new LoginResponseDto(token));
        } catch(Exception e){
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(e.getMessage());
        }
    }

}