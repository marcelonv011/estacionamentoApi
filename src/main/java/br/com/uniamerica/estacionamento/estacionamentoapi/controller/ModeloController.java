package br.com.uniamerica.estacionamento.estacionamentoapi.controller;


import br.com.uniamerica.estacionamento.estacionamentoapi.entity.Modelo;
import br.com.uniamerica.estacionamento.estacionamentoapi.repository.ModeloRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;


@Controller
@RequestMapping(value = "api/modelo")
public class ModeloController {

    @Autowired
    private ModeloRepository modeloRepository;

    @GetMapping("{id}")
    public ResponseEntity<?> findByIdPath(
            @PathVariable("id") final long id
    ){
        final Modelo modelo = this.modeloRepository.findById(id).orElse(null);
        return modelo == null
                ? ResponseEntity.badRequest().body("Ningun valor encontrado.")
                : ResponseEntity.ok(modelo);
    }

    @GetMapping
    public ResponseEntity<?> findByIdRequest(
            @RequestParam("id") final long id
    ){
        final Modelo modelo = this.modeloRepository.findById(id).orElse(null);
        return modelo == null
                ? ResponseEntity.badRequest().body("Ningun valor encontrado.")
                : ResponseEntity.ok(modelo);
    }

    @GetMapping("/lista")
    public ResponseEntity<?> listaCompleta(){
        return ResponseEntity.ok(this.modeloRepository.findAll());
    }

    @GetMapping("/ativo")
    public ResponseEntity<?> findByAtivo(){
        return ResponseEntity.ok(this.modeloRepository.findByAtivo(true));
    }

    @PostMapping
    public  ResponseEntity<?> cadastrar(@RequestBody final Modelo modelo){
        try {
            this.modeloRepository.save(modelo);
            return ResponseEntity.ok().body("Registro adicionado con exito");
        }
        catch (Exception e){
            return ResponseEntity.badRequest().body("Error: " + e.getMessage());
        }
    }

    @PutMapping
    public ResponseEntity<?> atualizar(
            @RequestParam("id") final Long id,
            @RequestBody final Modelo modelo
    ) {
        try {
            final Modelo modeloBanco = this.modeloRepository.findById(id).orElse(null);

            if (modeloBanco == null || !modeloBanco.getId().equals(modelo.getId())){
                throw new RuntimeException("nao foi possivel identificar o registro informado.");
            }

            this.modeloRepository.save(modelo);
            return ResponseEntity.ok("Registro atualizado com sucesso");
        }
        catch (DataIntegrityViolationException e){
            return ResponseEntity.internalServerError()
                    .body("Error:" + e.getCause().getCause().getMessage());
        }
        catch (RuntimeException e){
            return ResponseEntity.internalServerError().body("error:" + e.getMessage());
        }
    }

    @DeleteMapping
    public ResponseEntity<?> deletar(@RequestParam("id") final Long id){
        final Modelo modelo = this.modeloRepository.findById(id).orElse(null);
        try{
            this.modeloRepository.delete(modelo);
            return ResponseEntity.ok("Registro deletado");
        }
        catch(DataIntegrityViolationException e){
            modelo.setAtivo(false);
            this.modeloRepository.save(modelo);
            return ResponseEntity.internalServerError().body("Erro " + e.getCause().getCause().getMessage());
        }
    }

}


