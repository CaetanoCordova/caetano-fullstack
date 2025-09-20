package com.senac.AulaFullstack.controller;

import com.senac.AulaFullstack.model.Usuario;
import com.senac.AulaFullstack.repository.UsuarioRepository;
import io.swagger.v3.oas.annotations.Operation;
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

    @GetMapping("/{id}")
    @Operation(summary = "usuarios.", description = "Método que retorna um usuário específico pelo iD.")
    public ResponseEntity<Usuario> consultaPorId(@PathVariable Long id) {
        var usuario = usuarioRepository.findById(id).orElse(null);

        if (usuario == null) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(usuario);
    }

    @GetMapping
    @Operation(summary = "usuarios.", description = "Método que Retorna todos os usuários registrados.")
    public ResponseEntity<?> consultaTodos(){

        return ResponseEntity.ok(usuarioRepository.findAll());
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



    @PutMapping
    @Operation(summary = "(PLACEHOLDER NÃO USE) Salva alterações e atualizações de usuários.", description = "Método que salva as alterações nos perfis de usuário.")
    public ResponseEntity<?> atualizaUsuario(@RequestBody Usuario usuario){
        try{
            var usuarioResponse = usuarioRepository.save(usuario);

            return ResponseEntity.ok(usuarioRepository);
        }catch(Exception e){
            return ResponseEntity.badRequest().build();
        }
    }

}
