package com.senac.AulaFullstack.presentation;

import com.senac.AulaFullstack.application.dto.usuario.UsuarioResponseDto;
import com.senac.AulaFullstack.application.service.UsuarioService;
import com.senac.AulaFullstack.domain.entity.Usuario;
import com.senac.AulaFullstack.domain.repository.UsuarioRepository;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
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
    @Operation(summary = "usuarios.", description = "Método que retorna um usuário específico pelo iD.")
    public ResponseEntity<Usuario> consultaPorId(@PathVariable Long id) {
        var usuario = usuarioRepository.findById(id).orElse(null);

        if (usuario == null) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(usuario);
    }

//    @GetMapping
//    @Operation(summary = "usuarios.", description = "Método que Retorna todos os usuários registrados.")
//    public ResponseEntity<?> consultaTodos(){
//
//        return ResponseEntity.ok(usuarioRepository.findAll());
//    }

    @GetMapping
    @Operation(summary = "usuarios.", description = "Método que Retorna todos os usuários registrados.")
    public ResponseEntity<List<UsuarioResponseDto>> consultaTodos(
            @Parameter(description = "Parametro para a quantidade de registro por páginas.") @RequestParam Long take,
            @Parameter(description = "Parametro para a quantidade de páginas.") @RequestParam Long page,
            @Parameter(description = "Parametro do filtro.") @RequestParam(required = false) String filtro
    ){
        return ResponseEntity.ok(usuarioService.consultarPaginaDoFiltrado(take, page, filtro));
    }

    @PostMapping
    @Operation(summary = "Cria/Salva alterações de usuários.", description = "Método que cria os usuários e as alterações nas contas de[...] (Regra de negócio placeholder).")
    public ResponseEntity<?> salvaUsuario(@RequestBody Usuario usuario){
        try{
            var usuarioResponse = usuarioRepository.save(usuario);

            return ResponseEntity.ok(usuarioRepository);
        }catch(Exception e){
            return ResponseEntity.badRequest().build();
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
