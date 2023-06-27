package br.com.uniamerica.estacionamento.estacionamentoapi.controller;

import br.com.uniamerica.estacionamento.estacionamentoapi.entity.Condutor;
import br.com.uniamerica.estacionamento.estacionamentoapi.repository.CondutorRepository;
import br.com.uniamerica.estacionamento.estacionamentoapi.service.CondutorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping(value = "api/condutor")
public class CondutorController {

    @Autowired
    private CondutorRepository condutorRepository;

    @Autowired
    private CondutorService condutorService;

    @GetMapping("/{id}")
    public ResponseEntity<?> findByid(
            @PathVariable("id") final long id
    ) {
        final Condutor condutor = this.condutorRepository.findById(id).orElse(null);
        return condutor == null
                ? ResponseEntity.badRequest().body("ningun valor encontrado")
                : ResponseEntity.ok(condutor);
    }
    @GetMapping
    public ResponseEntity<?> findByRequest(
            @RequestParam("id") final long id
    ) {
        final Condutor condutor = this.condutorRepository.findById(id).orElse(null);
        return condutor == null
                ? ResponseEntity.badRequest().body("ningun valor encontrado")
                : ResponseEntity.ok(condutor);
    }

    @GetMapping("/lista")
    public ResponseEntity<?> findAll() {
        return ResponseEntity.ok(this.condutorRepository.findAll());
    }

    @GetMapping("/ativo")
    public ResponseEntity<?> findByAtivo() {
        return ResponseEntity.ok(this.condutorRepository.findByAtivo(true));
    }

    @PostMapping
    public ResponseEntity<?> cadastrar(@RequestBody final Condutor condutor) {
        try {
            this.condutorService.cadastrarCondutor(condutor);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("error" + e.getMessage());
        }
        return ResponseEntity.ok().body("Registro adicionado com exito");
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> atualizar(
            @PathVariable("id") final Long id,
            @RequestBody final Condutor condutor
    ) {
        try {
            this.condutorService.atualizarCondutor(id, condutor);
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
        final Condutor condutor = this.condutorRepository.findById(id).orElse(null);
        try{
            this.condutorRepository.delete(condutor);
            return ResponseEntity.ok("Registro deletado");
        }
        catch(RuntimeException e){
            condutor.setAtivo(false);
            this.condutorRepository.save(condutor);
            return ResponseEntity.badRequest().body("Nao pode excluir sim nao esta ativo");
        }
    }
}



