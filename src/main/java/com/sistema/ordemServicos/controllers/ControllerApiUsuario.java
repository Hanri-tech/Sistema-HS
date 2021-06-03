package com.sistema.ordemServicos.controllers;

import com.sistema.ordemServicos.models.Cargo;
import com.sistema.ordemServicos.models.Usuario;
import com.sistema.ordemServicos.repository.CargoRepository;
import com.sistema.ordemServicos.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api")
public class ControllerApiUsuario {

    @Autowired
    UsuarioRepository usuRepo;
    @Autowired
    CargoRepository cr;

    @GetMapping("/usuarios")
    public ResponseEntity<List<Usuario>> getAllUser(){
        List<Usuario> usuarios = usuRepo.findAll();
        if (usuarios.isEmpty()){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }else
            return new ResponseEntity<List<Usuario>>(usuarios, HttpStatus.OK);
    }

    @PostMapping("usuarios/cadastro")
    public ResponseEntity<Usuario> saveUser(@RequestBody Usuario usuario){
        return new ResponseEntity<Usuario>(usuRepo.save(usuario), HttpStatus.CREATED);
    }

    @PutMapping("usuarios/editar/{id}")
    public ResponseEntity<Usuario> editUser(@PathVariable("id") int id, @RequestBody Usuario usuarioObj){
        Cargo cargo = new Cargo();
        Optional<Usuario> usuario = usuRepo.findById(id);
        Iterable<Cargo> cargos = cr.findAll();
        if (usuario.isPresent()){
            usuarioObj.setId(usuario.get().getId());
            usuarioObj.setCargo(cargos.iterator().next());
            return new ResponseEntity<Usuario>(usuRepo.save(usuarioObj), HttpStatus.OK);
        }else
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @DeleteMapping("usuarios/excluir/{id}")
    public ResponseEntity<Usuario> deleteUser(@PathVariable("id") int id){
        Optional<Usuario> usuario = usuRepo.findById(id);
        if (usuario.isPresent()){
            usuRepo.deleteById(id);
            return new ResponseEntity<Usuario>(HttpStatus.OK);
        }else
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }


}
