package com.senac.AulaFullstack.domain.entity;

import com.senac.AulaFullstack.application.dto.usuario.UsuarioRequestDto;
import com.senac.AulaFullstack.application.dto.usuario.UsuarioResponseDto;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.time.LocalDateTime;
import java.util.Collection;
import java.util.List;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "usuarios")
public class Usuario implements UserDetails {

    public Usuario (UsuarioRequestDto usuarioRequest){
        this.setCpf(usuarioRequest.cpf());
        this.setNome(usuarioRequest.nome());
        this.setEmail(usuarioRequest.email());
        this.setSenha(usuarioRequest.senha());
        this.setRole("ROLE_USER");

        if(this.getDataCadastro() == null){
            this.setDataCadastro(LocalDateTime.now());
        }
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nome;
    private String cpf;
    private String email;
    private String senha;
    private String cep;
    private String endereco;

    private String role;

    private LocalDateTime dataCadastro;

    private String tokenSenha;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        // se tiver mais de uma hierarquia cria um case (professor recomendou)

        if ("ROLE_ADMIN".equals(this.role)) {
            return List.of(new SimpleGrantedAuthority("ROLE_ADMIN"),
                    new SimpleGrantedAuthority("ROLE_USER"));
        } else {
            return List.of(new SimpleGrantedAuthority("ROLE_USER"));
        }
    }


    @Override
    public String getPassword() {
        return this.senha;
    }

    @Override
    public String getUsername() {
        return this.email;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

    public UsuarioResponseDto toDtoResponse() {
        return new UsuarioResponseDto(this);
    }
}
