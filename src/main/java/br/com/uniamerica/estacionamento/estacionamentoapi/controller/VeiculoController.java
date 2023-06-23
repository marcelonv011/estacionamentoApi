package br.com.uniamerica.estacionamento.estacionamentoapi.controller;

import br.com.uniamerica.estacionamento.estacionamentoapi.entity.Cor;
import br.com.uniamerica.estacionamento.estacionamentoapi.entity.Tipo;
import br.com.uniamerica.estacionamento.estacionamentoapi.entity.Veiculo;
import br.com.uniamerica.estacionamento.estacionamentoapi.repository.VeiculoRepository;
import br.com.uniamerica.estacionamento.estacionamentoapi.service.VeiculoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping(value = "api/veiculo")
public class VeiculoController {

    @Autowired
    private VeiculoRepository veiculoRepository;

    @Autowired
    private VeiculoService veiculoService;

    @GetMapping
    public ResponseEntity<?> findByIdRequest(
            @RequestParam("id") final long id
    ){
        final Veiculo veiculo = this.veiculoRepository.findById(id).orElse(null);
        return veiculo == null
                ? ResponseEntity.badRequest().body("Ningun valor encontrado.")
                : ResponseEntity.ok(veiculo);
    }

    @GetMapping("/lista")
    public ResponseEntity<?> listaCompleta(){
        return ResponseEntity.ok(this.veiculoRepository.findAll());
    }

    @GetMapping("/ativo")
    public ResponseEntity<?> findByAtivo(){
        return ResponseEntity.ok(this.veiculoRepository.findByAtivo(true));
    }

    @GetMapping("/colores")
    public ResponseEntity<Cor[]> getColores() {
        return ResponseEntity.ok().body(Cor.values());
    }
    @GetMapping("/tipo")
    public ResponseEntity<Tipo[]> getTipo() {
        return ResponseEntity.ok().body(Tipo.values());
    }

    @PostMapping
    public  ResponseEntity<?> cadastrar(@RequestBody final Veiculo veiculo){
        try {
            this.veiculoService.cadastrarVeiculo(veiculo);
        }
         catch (Exception e){
            return ResponseEntity.badRequest().body("Error: " + e.getMessage());
        }
        return ResponseEntity.ok().body("Registro adicionado con exito");
    }

    @PutMapping
    public ResponseEntity<?> atualizar(
            @RequestParam("id") final Long id,
            @RequestBody final Veiculo veiculo
    ) {
        try {
            this.veiculoService.atualizarVeiculo(id, veiculo);
        }
        catch (DataIntegrityViolationException e){
            return ResponseEntity.internalServerError()
                    .body("Error:" + e.getCause().getCause().getMessage());
        }
        catch (RuntimeException e){
            return ResponseEntity.internalServerError().body("error:" + e.getMessage());
        }
        return ResponseEntity.ok("Registro atualizado com sucesso");
    }

    @DeleteMapping
    public ResponseEntity<?> deletar(@RequestParam("id") final Long id){
        final Veiculo veiculo = this.veiculoRepository.findById(id).orElse(null);
        try{
            this.veiculoRepository.delete(veiculo);
            return ResponseEntity.ok("Registro deletado");
        }
        catch(DataIntegrityViolationException e){
            veiculo.setAtivo(false);
            this.veiculoRepository.save(veiculo);
            return ResponseEntity.internalServerError().body("Erro " + e.getCause().getCause().getMessage());
        }
    }

}
