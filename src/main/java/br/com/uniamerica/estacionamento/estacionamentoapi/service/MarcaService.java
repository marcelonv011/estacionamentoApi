package br.com.uniamerica.estacionamento.estacionamentoapi.service;

import br.com.uniamerica.estacionamento.estacionamentoapi.entity.Marca;
import br.com.uniamerica.estacionamento.estacionamentoapi.repository.MarcaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class MarcaService {

    @Autowired
    private MarcaRepository marcaRepository;

    @Transactional
    public void cadastrarMarca(Marca marca){
        if(marca.getNome() == null || marca.getNome().isEmpty()){
            throw new RuntimeException(" Debe conter um nome");
        }
        if(marca.getNome().length() > 70) {
            throw new RuntimeException(" O nome de o modelo debe conter menos de 70 carateres");
        }
        this.marcaRepository.save(marca);
    }

}
