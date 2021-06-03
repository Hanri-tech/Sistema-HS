package com.sistema.ordemServicos.controllers;

import com.sistema.ordemServicos.models.Cargo;
import com.sistema.ordemServicos.models.Colaborador;
import com.sistema.ordemServicos.repository.CargoRepository;
import com.sistema.ordemServicos.repository.ColaboradorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api")
public class ControllerApiColaborador {

    @Autowired
    ColaboradorRepository colabRepo;
    @Autowired
    CargoRepository cr;

    @GetMapping("/colaboradores")
    public ResponseEntity<List<Colaborador>> getAllCol(){
        List<Colaborador> colaborador = colabRepo.findAll();
        if (colaborador.isEmpty()){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }else
            return new ResponseEntity<List<Colaborador>>(colaborador, HttpStatus.OK);
    }

    @PostMapping("colaborador/cadastro")
    public ResponseEntity<Colaborador> saveCol(@RequestBody Colaborador colaborador){
        return new ResponseEntity<Colaborador>(colabRepo.save(colaborador), HttpStatus.CREATED);
    }

    @PutMapping("colaborador/editar/{id}")
    public ResponseEntity<Colaborador> editCol(@PathVariable("id") int id, @RequestBody Colaborador colaboradorObj){
        Optional<Colaborador> colaborador = colabRepo.findById(id);
        Iterable<Cargo> cargos = cr.findAll();
        if (colaborador.isPresent()){
            colaboradorObj.setId(colaborador.get().getId());
            colaboradorObj.setCargo(cargos.iterator().next());
            return new ResponseEntity<Colaborador>(colabRepo.save(colaboradorObj), HttpStatus.OK);
        }else
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @DeleteMapping("colaborador/excluir/{id}")
    public ResponseEntity<Colaborador> deleteCol(@PathVariable("id") int id){
        Optional<Colaborador> colaborador = colabRepo.findById(id);
        if (colaborador.isPresent()){
            colabRepo.deleteById(id);
            return new ResponseEntity<Colaborador>(HttpStatus.OK);
        }else
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

}
