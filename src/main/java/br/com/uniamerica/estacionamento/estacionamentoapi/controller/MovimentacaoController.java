package br.com.uniamerica.estacionamento.estacionamentoapi.controller;

import br.com.uniamerica.estacionamento.estacionamentoapi.entity.Movimentacao;
import br.com.uniamerica.estacionamento.estacionamentoapi.repository.MovimentacaoRepository;
import br.com.uniamerica.estacionamento.estacionamentoapi.service.MovimentacaoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping(value = "api/movimentacao")
public class MovimentacaoController {

    @Autowired
    private MovimentacaoRepository movimentacaoRepository;

    @Autowired
    private MovimentacaoService movimentacaoService;

    @GetMapping
    public ResponseEntity<?> findByIdRequest(
            @RequestParam("id") final long id
    ) {
        final Movimentacao movimentacao = this.movimentacaoRepository.findById(id).orElse(null);
        return movimentacao == null
                ? ResponseEntity.badRequest().body("Ningun valor encontrado.")
                : ResponseEntity.ok(movimentacao);
    }

    @GetMapping("/lista")
    public ResponseEntity<?> listaCompleta() {
        return ResponseEntity.ok(this.movimentacaoRepository.findAll());
    }

    @GetMapping("/ativo")
    public ResponseEntity<?> findByAberta() {
        return ResponseEntity.ok(this.movimentacaoRepository.findByAberta());
    }

    @PostMapping
    public  ResponseEntity<?> cadastrar(@RequestBody final Movimentacao movimentacao){
        try {
            this.movimentacaoService.cadastrarMovimentacao(movimentacao);
        }
        catch (Exception e){
            return ResponseEntity.badRequest().body("Error: " + e.getMessage());
        }
        return ResponseEntity.ok().body("Registro adicionado con exito");
    }

    @PutMapping
    public ResponseEntity<?> atualizar(
            @RequestParam("id") final Long id,
            @RequestBody final Movimentacao movimentacao
    ) {
        try {
            this.movimentacaoService.atualizarMovimentacao(id, movimentacao);
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
        final Movimentacao movimentacao = this.movimentacaoRepository.findById(id).orElse(null);
        if(movimentacao==null){
            return ResponseEntity.badRequest().body("Não foi possivel desativar a flag");
        }
        movimentacao.setAtivo(false);
        movimentacaoRepository.save(movimentacao);
        return ResponseEntity.ok("Flag desativada com sucesso");
    }

}