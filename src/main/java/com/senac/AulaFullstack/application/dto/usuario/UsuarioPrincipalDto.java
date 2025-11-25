package com.senac.AulaFullstack.application.dto.usuario;

import com.senac.AulaFullstack.domain.entity.Usuario;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.GrantedAuthoritiesContainer;

import java.util.Collection;

public record UsuarioPrincipalDto(Long id, String email, Collection<? extends GrantedAuthority> autorizacao) {

    public UsuarioPrincipalDto(Usuario usuario){
        this(
                usuario.getId(),
                usuario.getEmail(),
                usuario.getAuthorities()
        );
    }
}
