package com.senac.AulaFullstack.presentation;

import com.senac.AulaFullstack.application.dto.usuario.*;
import com.senac.AulaFullstack.application.service.UsuarioService;
import com.senac.AulaFullstack.domain.entity.Usuario;
import com.senac.AulaFullstack.domain.repository.UsuarioRepository;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/usuarios")
@Tag(name = "Controlador de usuários", description = "Responsável por controlar os registros dos usuários (Criar, ver, editar)")
public class UsuarioController {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private UsuarioService usuarioService;

    @GetMapping("/{id}")
    @Operation(summary = "Retorna um usuário.", description = "Método que retorna um usuário específico pelo iD.")
    //@PreAuthorize("hasAnyRole('ROLE_ADMIN')")
    public ResponseEntity<UsuarioResponseDto> consultarUsuarioPorId(@PathVariable Long id) {
        var usuario = usuarioService.consultarPorId(id);

        SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        if (usuario == null) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(usuario);
    }

    @GetMapping
    @Operation(summary = "Retorna todos os usuarios.", description = "Método que Retorna todos os usuários registrados.")
    //@PreAuthorize("hasAnyRole('ROLE_ADMIN')")
    public ResponseEntity<List<UsuarioResponseDto>> consultarTodosUsuarios() {
        return ResponseEntity.ok(usuarioService.consultarTodosSemFiltro());
    }

    //Listagem de usuários com filtro
    @GetMapping("/grid")
    @Operation(summary = "Retorna usuários em páginas.", description = "Método que Retorna todos os usuários registrados.")
    public ResponseEntity<List<UsuarioResponseDto>> consultaTodos(
            @Parameter(description = "Parametro para a quantidade de registro por páginas.") @RequestParam Long take,
            @Parameter(description = "Parametro para a quantidade de páginas.") @RequestParam Long page,
            @Parameter(description = "Parametro do filtro.") @RequestParam(required = false) String filtro
    ){
        return ResponseEntity.ok(usuarioService.consultarPaginaDoFiltrado(take, page, filtro));
    }

    @PostMapping("/cadastro")
    @Operation(summary = "Cria um usuario novo.")
    public ResponseEntity<?> salvaUsuario(@RequestBody UsuarioRequestDto usuarioDto) {
        try {
            if (usuarioRepository.existsByCpf(usuarioDto.cpf())) {
                return ResponseEntity.badRequest().body("CPF já cadastrado");
            }
            if (usuarioRepository.existsByEmail(usuarioDto.email())) {
                return ResponseEntity.badRequest().body("Email já cadastrado");
            }
            Usuario usuario = new Usuario(usuarioDto);
            usuario.setNome(usuarioDto.nome());
            usuario.setEmail(usuarioDto.email());
            usuario.setCpf(usuarioDto.cpf());
            usuario.setSenha(usuarioDto.senha());
            usuario.setRole("ROLE_USER");
            usuario.setDataCadastro(LocalDateTime.now());
            Usuario usuarioSalvo = usuarioRepository.save(usuario);
            return ResponseEntity.ok(usuarioSalvo.toDtoResponse());
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Erro ao criar usuário: " + e.getMessage());
        }
    }

    @PutMapping("/{id}")
    @Operation(summary = "Atualiza um usuario existente.", description = "Método que atualiza os dados de um usuario já registrada.")
    public ResponseEntity<?> atualizaUsuario(@PathVariable Long id, @RequestBody UsuarioUpdateDto usuarioAtualizado) {
        return usuarioRepository.findById(id)
                .map(usuario -> {
                    usuario.setNome(usuarioAtualizado.nome());
                    usuario.setEmail(usuarioAtualizado.email());
                    usuario.setCpf(usuarioAtualizado.cpf());
                    //usuario.setSenha(usuarioAtualizado.getSenha());
                    //usuario.setRole(usuarioAtualizado.getRole());

                    var usuarioSalvo = usuarioRepository.save(usuario);
                    return ResponseEntity.ok(usuarioSalvo);
                })
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping("/adm")
    @Operation(summary = "Criação de usuário ADM puxando do DeskTop", description = "Método responsável por criação de usuário adm")
//@PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_ASSINANTE')")
    public ResponseEntity<?> criarAdm(@RequestBody AdmRequestDto admRequest){

        try {
            var admResponse = usuarioService.salvarAdm(admRequest);
            return ResponseEntity.ok(admResponse);

        } catch (IllegalArgumentException e){
            return ResponseEntity.badRequest().body(e.getMessage());

        } catch (Exception e) {
            return ResponseEntity.internalServerError().body("Ocorreu um erro, tente novamente mais tarde.");
        }
    }


//    use este json
//    {
//        "nome": "Caetano",
//        "email": "caetano@email.com",
//        "senha": "123",
//        "role": "ROLE_ADMIN",
//        "cpf": "12345678900"
//    }

}
