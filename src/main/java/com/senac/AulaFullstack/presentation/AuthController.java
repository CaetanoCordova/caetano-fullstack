package com.senac.AulaFullstack.presentation;

import com.senac.AulaFullstack.application.dto.login.LoginRequestDto;
import com.senac.AulaFullstack.application.dto.login.LoginResponseDto;
import com.senac.AulaFullstack.application.service.TokenService;
import com.senac.AulaFullstack.application.service.UsuarioService;
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
@Tag(name = "Controlador de autenticação", description = "Controlador responsável pela autenticação do usuário na API com geração de um token")
public class AuthController {

    @Autowired
    private TokenService tokenService;

    @Autowired
    private UsuarioService usuarioService;

    @PostMapping("/login")
    @Operation(summary = "Login", description = "Método responsável pelo login de usuário com geração de token")
    public ResponseEntity<?> login(@RequestBody LoginRequestDto request){

        if (!usuarioService.validarSenha(request)){
            return ResponseEntity.badRequest().body("Usuário e/ou senha inválido!");
        }

        var token = tokenService.gerarToken(request);

        return ResponseEntity.ok(new LoginResponseDto(token));
    }
}
