package com.senac.AulaFullstack.presentation;

import com.senac.AulaFullstack.application.dto.usuario.UsuarioRequestDto;
import com.senac.AulaFullstack.application.dto.usuario.UsuarioResponseDto;
import com.senac.AulaFullstack.application.service.UsuarioService;
import com.senac.AulaFullstack.domain.entity.Usuario;
import com.senac.AulaFullstack.domain.repository.UsuarioRepository;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

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

    @PostMapping
    @Operation(summary = "Cria/Salva alterações de usuários.", description = "Método que cria os usuários.")
    //@PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_ASSINANTE')")
    public ResponseEntity<UsuarioResponseDto> criarUsuario(@RequestBody UsuarioRequestDto usuario){

        try{
            var usuarioResponse = usuarioService.salvarUsuario(usuario);

            return ResponseEntity.ok(usuarioResponse);

        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
    }

    //Criação de usuários ADM
    //TODO NÃO SEI O QUE FAZ, COPIEI DO AFONSO LMAO
//    @PostMapping("/adm")
//    @Operation(summary = "Criação de usuário ADM puxando do DeskTop", description = "Método responsável por criação de usuário adm")
//    //@PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_USER')")
//    public ResponseEntity<UsuarioResponseDto> criarAdm(@RequestBody UsuarioRequestDto usuario){
//
//        try{
//            var usuarioResponse = usuarioService.salvarUsuario(usuario);
//
//            return ResponseEntity.ok(usuarioResponse);
//
//        } catch (Exception e) {
//            return ResponseEntity.badRequest().build();
//        }
//    }

//    use este json
//    {
//        "nome": "Caetano",
//        "email": "caetano@email.com",
//        "senha": "123",
//        "role": "ROLE_ADMIN",
//        "cpf": "12345678900"
//    }

}
