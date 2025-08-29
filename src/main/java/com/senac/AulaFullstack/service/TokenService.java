package com.senac.AulaFullstack.service;
import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneOffset;

@Service
public class TokenService {
    @Value("${spring.secretkey}")
    private String secret;

    @Value("${spring.tempo_expiracao}")
    private Long tempo_expiracao;

    private String emissor = "GERENCIACONTAS";

    public String gerarToken(String usuario, String senha){
        Algorithm algorithm = Algorithm.HMAC256(secret);
        String token = JWT.create()
                .withIssuer(emissor)
                .withSubject(usuario)
                .withExpiresAt(this.gerarDataExpiracao())
                .sign(algorithm);
                //estudar isso
        return token;
    }

    private Instant gerarDataExpiracao(){
        var dataAtual = LocalDateTime.now();
        dataAtual = dataAtual.plusMinutes(tempo_expiracao);

        return dataAtual.toInstant(ZoneOffset.of("-03:00"));
    }

    //public String validarToken(String token){

    //}
}
