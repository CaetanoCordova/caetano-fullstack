package com.senac.AulaFullstack.application.service;

import com.senac.AulaFullstack.application.dto.login.LoginRequestDto;
import com.senac.AulaFullstack.application.dto.usuario.UsuarioRequestDto;
import com.senac.AulaFullstack.application.dto.usuario.UsuarioResponseDto;
import com.senac.AulaFullstack.domain.entity.Usuario;
import com.senac.AulaFullstack.domain.repository.UsuarioRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

import static java.util.Arrays.stream;

@Service
public class UsuarioService {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Value("${spring.secretkey}")
    private String secret;

    // salvará o usuário com senha criptografada
    public Usuario salvarUsuario(Usuario usuario) {
        return usuarioRepository.save(usuario);
    }

    // irá validar o login usando a DTO
    public boolean validarSenha(LoginRequestDto login) {
        Usuario usuario = usuarioRepository.findByEmail(login.email()).orElse(null);

        // se o usuario existir, vai comparar a senha informada com a criptografada no banco
        if (usuario != null) {
            return login.senha().equals(usuario.getSenha());
        }

        // se não retorna falso
        return false;
    }

    public UsuarioResponseDto consultarPorId(Long id) {
        return usuarioRepository.findById(id)
                .map(UsuarioResponseDto::new)
                .orElse(null);
    }

    public List<UsuarioResponseDto> consultarTodosSemFiltro() {
        return usuarioRepository.findAll()
                .stream()
                .map(UsuarioResponseDto::new)
                .collect(Collectors.toList());
    }

    @Transactional
    public UsuarioResponseDto salvarUsuario(UsuarioRequestDto usuarioRequest) {
        var usuario = usuarioRepository.findByCpf(usuarioRequest.cpf())
                .map(u -> {
                    u.setNome(usuarioRequest.nome());
                    u.setSenha(usuarioRequest.senha());
                    u.setRole(usuarioRequest.role());
                    u.setEmail(usuarioRequest.email());
                    return u;
                })
                .orElse(new Usuario(usuarioRequest));

        usuarioRepository.save(usuario);

        return usuario.toDtoResponse();
    }

    public List<UsuarioResponseDto> consultarPaginaDoFiltrado(Long take, Long page, String filtro) {

        return usuarioRepository.findAll()
                .stream()
                .sorted(Comparator.comparing(Usuario::getId).reversed())
                .filter(p -> p.getDataCadastro().isAfter(LocalDateTime.now().plusDays(-7)))
                .filter(a -> filtro != null ? a.getNome().contains(filtro) : true)
                .skip((long) page * take)
                .limit(take)
                .map(UsuarioResponseDto::new)
                .collect(Collectors.toList());
    }
}