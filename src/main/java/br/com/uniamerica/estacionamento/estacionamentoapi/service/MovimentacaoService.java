package br.com.uniamerica.estacionamento.estacionamentoapi.service;

import br.com.uniamerica.estacionamento.estacionamentoapi.configs.ValCpf;
import br.com.uniamerica.estacionamento.estacionamentoapi.configs.ValTelefone;
import br.com.uniamerica.estacionamento.estacionamentoapi.entity.Configuracao;
import br.com.uniamerica.estacionamento.estacionamentoapi.entity.Movimentacao;
import br.com.uniamerica.estacionamento.estacionamentoapi.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


import java.math.BigDecimal;
import java.time.Duration;
import java.time.LocalDateTime;
import java.time.LocalTime;

@Service
public class MovimentacaoService {

    @Autowired
    private MovimentacaoRepository movimentacaoRepository;

    @Autowired
    private ValCpf valCpf;

    @Autowired
    private ValTelefone valTelefone;

   @Autowired
   private ConfiguracaoRepository configuracaoRepository;

    @Transactional
    public void cadastrarMovimentacao(Movimentacao movimentacao){
        if ("".equals(movimentacao.getVeiculo())){
            throw new RuntimeException(" Deve colocar um veiculo");
        }
        if ("".equals(movimentacao.getCondutor())){
            throw new RuntimeException(" Deve colocar um condutor");
        }
        if (movimentacao.getEntrada() == null){
            throw new RuntimeException(" Deve colocar um horario de entrada");
        }
        if (movimentacao.getSaida() == null){
            throw new RuntimeException(" Deve colocar um horario de saida");
        }
        if("".equals(movimentacao.getValorTotal())){
            throw new RuntimeException(" Deve colocar o valor total");
        }
        if("".equals(movimentacao.getValorHora())){
            throw new RuntimeException(" Deve colocar o valor da hora");
        }
        if(movimentacao.getSaida() != null){
            LocalTime tempo = movimentacao.getSaida()
                    .minusHours(movimentacao.getEntrada().getHour())
                    .minusMinutes(movimentacao.getEntrada().getMinute())
                    .minusSeconds(movimentacao.getEntrada().getSecond())
                    .minusNanos(movimentacao.getEntrada().getNano());
            movimentacao.setTempo(tempo);
        }
        if(movimentacao.getTempo() != null){
            movimentacao.setValorHora(configuracaoRepository.findByHora());

            BigDecimal valorTotal = configuracaoRepository.findByHora().multiply(new BigDecimal(movimentacao.getTempo().getHour()));

            movimentacao.setValorTotal(valorTotal);
        }
        this.movimentacaoRepository.save(movimentacao);
    }

    @Transactional
    public void atualizarMovimentacao(final Long id, Movimentacao movimentacao){

        final Movimentacao movimentacaoBanco = this.movimentacaoRepository.findById(id).orElse(null);

        if (movimentacaoBanco == null || !movimentacaoBanco.getId().equals(movimentacao.getId())){
            throw new RuntimeException("nao foi possivel identificar o registro informado.");
        }
        if ("".equals(movimentacao.getVeiculo())){
            throw new RuntimeException(" Deve colocar um veiculo");
        }
        if ("".equals(movimentacao.getCondutor())){
            throw new RuntimeException(" Deve colocar um condutor");
        }
        if ("".equals(movimentacao.getEntrada())){
            throw new RuntimeException(" Deve colocar um horario de entrada");
        }
        if ("".equals(movimentacao.getSaida())){
            throw new RuntimeException(" Deve colocar um horario de saida");
        }

        if("".equals(movimentacao.getValorTotal())){
            throw new RuntimeException(" Deve colocar o valor total");
        }
        if("".equals(movimentacao.getValorHora())){
            throw new RuntimeException(" Deve colocar o valor da hora");
        }
        if(movimentacao.getSaida() != null){
            LocalTime tempo = movimentacao.getSaida()
                    .minusHours(movimentacao.getEntrada().getHour())
                    .minusMinutes(movimentacao.getEntrada().getMinute())
                    .minusSeconds(movimentacao.getEntrada().getSecond())
                    .minusNanos(movimentacao.getEntrada().getNano());
            movimentacao.setTempo(tempo);
        }
        if(movimentacao.getTempo() != null){
            movimentacao.setValorHora(configuracaoRepository.findByHora());

            BigDecimal valorTotal = configuracaoRepository.findByHora().multiply(new BigDecimal(movimentacao.getTempo().getHour()));

            movimentacao.setValorTotal(valorTotal);
        }
        this.movimentacaoRepository.save(movimentacao);
    }

}
