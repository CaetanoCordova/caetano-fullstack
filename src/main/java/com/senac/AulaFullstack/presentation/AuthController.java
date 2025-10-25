package com.senac.AulaFullstack.presentation;

import com.senac.AulaFullstack.application.dto.login.AlterarSenhaDto;
import com.senac.AulaFullstack.application.dto.login.EsqueciMinhaSenhaDto;
import com.senac.AulaFullstack.application.dto.login.LoginRequestDto;
import com.senac.AulaFullstack.application.dto.login.LoginResponseDto;
import com.senac.AulaFullstack.application.dto.usuario.UsuarioPrincipalDto;
import com.senac.AulaFullstack.application.service.TokenService;
import com.senac.AulaFullstack.application.service.UsuarioService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
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

    @PostMapping("/recuperarsenha/envio")
    @Operation(summary = "Recupera senha", description = "Método responsável pelos emails de recuperação de senha")
    public ResponseEntity<?> recuperarSenhaEnvio(@AuthenticationPrincipal UsuarioPrincipalDto usuarioLogado) {

        usuarioService.recuperarSenhaEnvio(usuarioLogado);

        return ResponseEntity.ok("Código enviado com sucesso.");
    }

    @PostMapping("/esquecisenha")
    @Operation(summary = "Esqueci a senha", description = "Método responsável pela recuperação de senha esquecida")
    public ResponseEntity<?> EsqueciMinhaSenha(@RequestBody EsqueciMinhaSenhaDto esqueciMinhaSenhaDto) {
        try{
            usuarioService.EsqueciMinhaSenha(esqueciMinhaSenhaDto);
        } catch (Exception e){
            throw new RuntimeException("Deu paaaau cara");
        }

        return ResponseEntity.ok("Código enviado com sucesso.");
    }

    @PostMapping("/alterarsenha")
    @Operation(summary = "Nova senha", description = "Método responsável pela alteração de senhas com código de recuperação")
    public ResponseEntity<?> AlterarSenha(@RequestBody AlterarSenhaDto alterarSenhaDto) {
        try{
            usuarioService.AlterarSenha(alterarSenhaDto);
            return ResponseEntity.ok().build();
        }catch (Exception e){
            return ResponseEntity.badRequest().build();
        }
    }
}