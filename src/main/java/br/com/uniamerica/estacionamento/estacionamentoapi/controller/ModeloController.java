package br.com.uniamerica.estacionamento.estacionamentoapi.controller;


import br.com.uniamerica.estacionamento.estacionamentoapi.entity.Modelo;
import br.com.uniamerica.estacionamento.estacionamentoapi.repository.ModeloRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping(value = "api/modelo")
public class ModeloController {

    @Autowired
    private ModeloRepository modeloRepository;

    @GetMapping("{id}")
    public ResponseEntity<Optional <Modelo>> findAll(
            @PathVariable("id") final long id
    ){
        return ResponseEntity.ok().body(this.modeloRepository.findById(id));
    }

    @GetMapping

    @PostMapping

    @PutMapping

    @DeleteMapping

}
