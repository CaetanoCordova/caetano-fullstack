package com.senac.AulaFullstack.application.service;

import com.senac.AulaFullstack.application.dto.login.AlterarSenhaDto;
import com.senac.AulaFullstack.application.dto.login.EsqueciMinhaSenhaDto;
import com.senac.AulaFullstack.application.dto.login.LoginRequestDto;
import com.senac.AulaFullstack.application.dto.usuario.UsuarioPrincipalDto;
import com.senac.AulaFullstack.application.dto.usuario.UsuarioRequestDto;
import com.senac.AulaFullstack.application.dto.usuario.UsuarioResponseDto;
import com.senac.AulaFullstack.domain.entity.Usuario;
import com.senac.AulaFullstack.domain.interfaces.IEnviaMail;
import com.senac.AulaFullstack.domain.repository.UsuarioRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.security.SecureRandom;
import java.time.LocalDateTime;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

import static java.util.Arrays.stream;

@Service
public class UsuarioService {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private IEnviaMail iEnviaMail;

    @Value("${spring.secretkey}")
    private String secret;

    public Usuario salvarUsuario(Usuario usuario) {
        return usuarioRepository.save(usuario);
    }

    public boolean validarSenha(LoginRequestDto login) {
        Usuario usuario = usuarioRepository.findByEmail(login.email()).orElse(null);

        if (usuario != null) {
            return login.senha().equals(usuario.getSenha());
        }

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
                    u.setEmail(usuarioRequest.email());
                    u.setCpf(usuarioRequest.cpf());
                    u.setSenha(usuarioRequest.senha());
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

    public void recuperarSenhaEnvio(UsuarioPrincipalDto usuarioLogado) {
        iEnviaMail.enviarEmailSimples(
                usuarioLogado.email(),
                "Codigo de recuperacao",
                "123456");

    }

    public String gerarCodigoAleatorio(int length) {

        final String CHARS = "ABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
        //abcdefghijklmnopqrstuvwxyz
        SecureRandom random = new SecureRandom();
        StringBuilder senha = new StringBuilder(length);
        for (int i = 0; i < length; i++) {
            int randomIndex = random.nextInt(CHARS.length());
            senha.append(CHARS.charAt(randomIndex));
        }
        return senha.toString();
    }

    public void EsqueciMinhaSenha(EsqueciMinhaSenhaDto esqueciMinhaSenhaDto) {

        var usuario = usuarioRepository.findByEmail(esqueciMinhaSenhaDto.email()).orElse(null);

        if(usuario != null){
            var codigo = gerarCodigoAleatorio(4);
            usuario.setTokenSenha(codigo);

            usuarioRepository.save(usuario);

            iEnviaMail.voidEnviarEmailComTemplate(esqueciMinhaSenhaDto.email(),
                    "Codigo de recuperacao",
                    usuario.getTokenSenha()
            );
        }
    }

    public void AlterarSenha(AlterarSenhaDto alterarSenhaDto) {

        var usuario = usuarioRepository
                .findByEmailAndTokenSenha(
                        alterarSenhaDto.email(),
                        alterarSenhaDto.token())
                .orElse(null);
        if(usuario != null){
            usuario.setSenha(alterarSenhaDto.senha());
            usuarioRepository.save(usuario);
        }
    }
}