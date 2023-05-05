package br.com.uniamerica.estacionamento.estacionamentoapi.service;

import br.com.uniamerica.estacionamento.estacionamentoapi.entity.Configuracao;
import br.com.uniamerica.estacionamento.estacionamentoapi.repository.ConfiguracaoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class ConfiguracaoService {

    @Autowired
    private ConfiguracaoRepository configuracaoRepository;

    @Transactional
    public void cadastrarConfiguracao(Configuracao configuracao){
        if("".equals(configuracao.getValorHora())){
            throw new RuntimeException(" O valor da hora nao pode ser vacio");
        }
        if("".equals(configuracao.getVagasMoto()) || "".equals(configuracao.getVagasCarro()) || "".equals(configuracao.getVagasVam())){
            throw new RuntimeException(" Inserir a cantidades de vagas para carro, moto e vam");
        }
        this.configuracaoRepository.save(configuracao);
    }
    @Transactional
    public void atualizarConfiguracao(final Long id, Configuracao configuracao){
        final Configuracao configuracaoBanco = this.configuracaoRepository.findById(id).orElse(null);

        if (configuracaoBanco == null || !configuracaoBanco.getId().equals(configuracao.getId())) {
            throw new RuntimeException("nao foi possivel identificar o registro informado.");
        }
        if("".equals(configuracao.getValorHora())){
            throw new RuntimeException(" O valor da hora nao pode ser vacio");
        }
        if("".equals(configuracao.getVagasMoto()) || "".equals(configuracao.getVagasCarro()) || "".equals(configuracao.getVagasVam())){
            throw new RuntimeException(" Inserir a cantidades de vagas para carro, moto e vam");
        }
        this.configuracaoRepository.save(configuracao);
    }


}
