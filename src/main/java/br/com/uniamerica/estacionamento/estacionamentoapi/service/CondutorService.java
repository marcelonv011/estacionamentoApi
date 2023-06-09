package br.com.uniamerica.estacionamento.estacionamentoapi.service;

import br.com.uniamerica.estacionamento.estacionamentoapi.configs.ValCpf;
import br.com.uniamerica.estacionamento.estacionamentoapi.configs.ValTelefone;
import br.com.uniamerica.estacionamento.estacionamentoapi.entity.Condutor;
import br.com.uniamerica.estacionamento.estacionamentoapi.repository.CondutorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

@Service
public class CondutorService {

    @Autowired
    private CondutorRepository condutorRepository;

    @Autowired
    private ValTelefone valTelefone;

    @Autowired
    private ValCpf valCpf;


    @Transactional
    public void cadastrarCondutor(Condutor condutor){
        if(condutor.getNome() == null || condutor.getNome().isEmpty()){
            throw new RuntimeException(" Debe conter um nome");
        }
        if(condutor.getCpf() == null){
            throw new RuntimeException(" Seu cpf nao pode ser nulo");
        }
        if(condutor.getTelefone() == null){
            throw new RuntimeException(" Seu telefone nao pode ser nulo");
        }
        if(condutor.getNome().length() > 100 || condutor.getNome().length() < 2){
            throw new RuntimeException(" O nome é maximo 100 carateres e deve ser maior a 2");
        }
        if( this.valTelefone.ValTelefone(condutor.getTelefone()) == false) {
            throw new RuntimeException(" Seu telefone nao é valido");
        }
        if (this.valCpf.valCpf(condutor.getCpf()) == false){
            throw new RuntimeException(" Seu CPF nao é valido");
        }
        if(condutorRepository.findByCpf(condutor.getCpf())!=null){
            throw new RuntimeException(" O CPF já existe");
        }
        if (condutorRepository.findByTelefone(condutor.getTelefone())!=null){
            throw new RuntimeException(" O telefone já existe");
        }
        this.condutorRepository.save(condutor);
    }

    @Transactional
    public void atualizarCondutor(final Long id, Condutor condutor){
        final Condutor condutorBanco = this.condutorRepository.findById(id).orElse(null);

        if (condutorBanco == null || !condutorBanco.getId().equals(condutor.getId())) {
            throw new RuntimeException("nao foi possivel identificar o registro informado.");
        }
        if(condutor.getNome() == null || condutor.getNome().isEmpty()){
            throw new RuntimeException("Debe conter um nome");
        }
        if(condutor.getNome().length() > 100){
            throw new RuntimeException("Maximo 100 carateres");
        }
        if( this.valTelefone.ValTelefone(condutor.getTelefone()) == false) {
            throw new RuntimeException(" Seu telefone nao é valido");
        }
        if (this.valCpf.valCpf(condutor.getCpf()) == false){
            throw new RuntimeException(" Seu CPF nao é valido");
        }
        if(!condutor.isAtivo()){
            condutor.setAtivo(true);
        }
        if(condutor.getCadastro()==null || "".equals(condutor.getCadastro())){
            condutor.setCadastro(condutorRepository.findById(condutor.getId()).get().getCadastro());
        }

        this.condutorRepository.save(condutor);
    }
}
