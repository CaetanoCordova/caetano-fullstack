package com.senac.AulaFullstack.controller;

import com.senac.AulaFullstack.dto.LoginRequestDto;
import com.senac.AulaFullstack.service.TokenService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
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


    @PostMapping("/login")
    @Operation(summary = "login", description = "Método responsável pelo login")
    public ResponseEntity<?> login(@RequestBody LoginRequestDto request){


        var token = tokenService.gerarToken(request);

        return ResponseEntity.ok(token);
    }

}
