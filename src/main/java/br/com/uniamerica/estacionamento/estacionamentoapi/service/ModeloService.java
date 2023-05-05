package br.com.uniamerica.estacionamento.estacionamentoapi.service;

import br.com.uniamerica.estacionamento.estacionamentoapi.entity.Modelo;
import br.com.uniamerica.estacionamento.estacionamentoapi.repository.ModeloRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

@Service
public class ModeloService {

    @Autowired
    private ModeloRepository modeloRepository;

    @Transactional
    public void cadastrarModelo(Modelo modelo){
        if (modelo.getNome() == null || modelo.getNome().isEmpty()){
            throw new RuntimeException("Debe conter um nome");
        }
        if (modelo.getNome().length() > 100){
            throw new RuntimeException("O nome de o modelo debe conter menos de 100 carateres");
        }
        if(modelo.getMarca().getNome() == null || modelo.getMarca().getNome().isEmpty()){
            throw new RuntimeException("Debe conter um nome de marca");
        }
        this.modeloRepository.save(modelo);
    }

    @Transactional
    public void updateModelo(final Long id, Modelo modelo){
        final Modelo modeloBanco = this.modeloRepository.findById(id).orElse(null);
        if (modeloBanco == null || !modeloBanco.getId().equals(modelo.getId())) {
            throw new RuntimeException("nao foi possivel identificar o registro informado.");
        }
        if (modelo.getNome() == null || modelo.getNome().isEmpty()) {
            throw new RuntimeException("Debe contener un nombre");
        }
        if(modelo.getMarca().getNome() == null || modelo.getMarca().getNome().isEmpty()){
            throw new RuntimeException("Debe conter um nome de marca");
        }
        this.modeloRepository.save(modelo);
    }


}
