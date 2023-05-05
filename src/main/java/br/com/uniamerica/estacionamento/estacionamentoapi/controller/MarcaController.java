package br.com.uniamerica.estacionamento.estacionamentoapi.controller;

import br.com.uniamerica.estacionamento.estacionamentoapi.entity.Marca;
import br.com.uniamerica.estacionamento.estacionamentoapi.repository.MarcaRepository;
import br.com.uniamerica.estacionamento.estacionamentoapi.service.MarcaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping(value = "api/marca")
public class MarcaController {

    @Autowired
    private MarcaRepository marcaRepository;

    @Autowired
    private MarcaService marcaService;

    @GetMapping
    public ResponseEntity<?> findByRequest(
            @RequestParam("id") final Long id
    ){
        final Marca marca = this.marcaRepository.findById(id).orElse(null);
        return marca == null
                ? ResponseEntity.badRequest().body("ningun valor encontrado")
                : ResponseEntity.ok(marca);
    }


    @GetMapping("/lista")
    public ResponseEntity<?> findAll() {
        return ResponseEntity.ok(this.marcaRepository.findAll());
    }

    @GetMapping("/ativo")
    public ResponseEntity<?> findByAtivo() {
        return ResponseEntity.ok(this.marcaRepository.findByAtivo(true));
    }

    @PostMapping
    public ResponseEntity<?> cadastrar(@RequestBody final Marca marca) {
        try {
            this.marcaService.cadastrarMarca(marca);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("error" + e.getMessage());
        }
        return ResponseEntity.ok().body("Registro adicionado com exito");
    }

    @PutMapping
    public ResponseEntity<?> atualizar(
            @RequestParam("id") final Long id,
            @RequestBody final Marca marca
    ) {
        try {
            this.marcaService.atualizarMarca(id, marca);
        } catch (DataIntegrityViolationException e) {
            return ResponseEntity.internalServerError()
                    .body("Error:" + e.getCause().getCause().getMessage());
        } catch (RuntimeException e) {
            return ResponseEntity.internalServerError().body("error:" + e.getMessage());
        }
        return ResponseEntity.ok("Registro atualizado com sucesso");
    }

    @DeleteMapping
    public ResponseEntity<?> deletar(@RequestParam("id") final Long id){
        final Marca marca = this.marcaRepository.findById(id).orElse(null);
        try{
            this.marcaRepository.delete(marca);
            return ResponseEntity.ok("Registro deletado");
        }
        catch(DataIntegrityViolationException e){
            marca.setAtivo(false);
            this.marcaRepository.save(marca);
            return ResponseEntity.internalServerError().body("Erro " + e.getCause().getCause().getMessage());
        }
    }

}
