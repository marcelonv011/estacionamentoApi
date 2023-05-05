package br.com.uniamerica.estacionamento.estacionamentoapi.controller;

import br.com.uniamerica.estacionamento.estacionamentoapi.entity.Configuracao;
import br.com.uniamerica.estacionamento.estacionamentoapi.repository.ConfiguracaoRepository;
import br.com.uniamerica.estacionamento.estacionamentoapi.service.ConfiguracaoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping(value = "api/configuracao")
public class ConfiguracaoController {

    @Autowired
    private ConfiguracaoRepository configuracaoRepository;

    @Autowired
    private ConfiguracaoService configuracaoService;

    @GetMapping
    public ResponseEntity<?> findByRequest(
            @RequestParam("id") final Long id
    ){
        final Configuracao configuracao = this.configuracaoRepository.findById(id).orElse(null);
        return configuracao == null
                ? ResponseEntity.badRequest().body("ningun valor encontrado")
                : ResponseEntity.ok(configuracao);
    }

    @PostMapping
    public ResponseEntity<?> cadastrar(
            @RequestBody final Configuracao configuracao
    ) {
        try {
            this.configuracaoService.cadastrarConfiguracao(configuracao);
        }
        catch (Exception e){
            return ResponseEntity.badRequest().body("error" + e.getMessage());
        }
        return ResponseEntity.ok().body("Registro adicionado com exito");
    }

    @PutMapping
    public  ResponseEntity<?> atualizar(
            @RequestParam("id") final Long id,
            @RequestBody final Configuracao configuracao
    ){
        try {
            this.configuracaoService.atualizarConfiguracao(id, configuracao);
        } catch (DataIntegrityViolationException e) {
            return ResponseEntity.internalServerError()
                    .body("Error:" + e.getCause().getCause().getMessage());
        } catch (RuntimeException e) {
            return ResponseEntity.internalServerError().body("error:" + e.getMessage());
        }
        return ResponseEntity.ok("Registro atualizado com sucesso");
    }

}
