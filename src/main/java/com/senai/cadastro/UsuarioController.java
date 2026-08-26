package com.senai.cadastro;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping("/usuario")
@RequiredArgsConstructor
public class UsuarioController {

    final UsuarioRepository usuarioRepository;

    @GetMapping
    public List<Usuario> listarTodosUsuarios() {
        return usuarioRepository.findAll();
    }

    @GetMapping("/{id}")
    public Usuario buscarUsuarioPorId(@PathVariable UUID id) {
        Optional<Usuario> usuarioOpt = usuarioRepository.findById(id);
         if(usuarioOpt.isPresent()) {
             return  usuarioOpt.get();
        } else {
            throw new RuntimeException("Usuário não encontrado");
        }
    }
    @PostMapping("/list")
    public void cadastrarUsuarios(@RequestBody Usuario[] usuario) {
        //Salva uma lista
        usuarioRepository.saveAll(Arrays.asList(usuario));
    }

    @PostMapping()
    public void cadastrarUsuarios(@RequestBody Usuario usuario) {
        usuarioRepository.save(usuario);
    }

    @PutMapping("/{id}")
    public Usuario atualizarUsuario(@PathVariable UUID id, @RequestBody Usuario usuario) {
        Usuario usuarioExistente = buscarUsuarioPorId(id);
        usuarioExistente.setNome(usuario.getNome());
        usuarioExistente.setCpf(usuario.getCpf());
        usuarioExistente.setEmail(usuario.getEmail());

        return usuarioRepository.save(usuarioExistente);
    }
    @DeleteMapping("/{id}")
    public void deletarUsuario(@PathVariable UUID id) {
        usuarioRepository.delete(buscarUsuarioPorId(id));
    }
}
