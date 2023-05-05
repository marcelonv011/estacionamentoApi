package br.com.uniamerica.estacionamento.estacionamentoapi.service;

import br.com.uniamerica.estacionamento.estacionamentoapi.entity.Movimentacao;
import br.com.uniamerica.estacionamento.estacionamentoapi.repository.MovimentacaoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class MovimentacaoService {

    @Autowired
    private MovimentacaoRepository movimentacaoRepository;

    @Transactional
    public void cadastrarMovimentacao(Movimentacao movimentacao){
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
        if ("".equals(movimentacao.getTempo())){
            throw new RuntimeException(" Deve colocar o tempo que fico o carro");
        }
        if("".equals(movimentacao.getValorTotal())){
            throw new RuntimeException(" Deve colocar o valor total");
        }
        if("".equals(movimentacao.getValorHora())){
            throw new RuntimeException(" Deve colocar o valor da hora");
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
        if ("".equals(movimentacao.getTempo())){
            throw new RuntimeException(" Deve colocar o tempo que fico o carro");
        }
        if("".equals(movimentacao.getValorTotal())){
            throw new RuntimeException(" Deve colocar o valor total");
        }
        if("".equals(movimentacao.getValorHora())){
            throw new RuntimeException(" Deve colocar o valor da hora");
        }
        this.movimentacaoRepository.save(movimentacao);
    }

}
