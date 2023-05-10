package br.com.uniamerica.estacionamento.estacionamentoapi.service;

import br.com.uniamerica.estacionamento.estacionamentoapi.configs.ValCpf;
import br.com.uniamerica.estacionamento.estacionamentoapi.configs.ValTelefone;
import br.com.uniamerica.estacionamento.estacionamentoapi.entity.Condutor;
import br.com.uniamerica.estacionamento.estacionamentoapi.repository.CondutorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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
        if(condutor.getNome() == null){
            throw new RuntimeException("Debe conter um nome");
        }
        if(condutor.getNome().length() > 100){
            throw new RuntimeException("Maximo 100 carateres");
        }
        if("".equals(condutor.getCpf()) || condutor.getCpf().length() > 14){
            throw new RuntimeException(" CPF nao pode ser nulo e deve conter menos de 15 carateres");
        }
        if("".equals(condutor.getTelefone()) || condutor.getTelefone().length() > 17){
            throw new RuntimeException(" TELEFONE nao pode ser nulo e deve conter menos de 17 carateres");
        }
        if("".equals(condutor.getTempoPago())){
            throw new RuntimeException(" Tempo pago nao pode ser nulo");
        }
        if( this.valTelefone.ValTelefone(condutor.getTelefone()) == false) {
            throw new RuntimeException(" Seu telefone nao é valido");
        }
        if (this.valCpf.valCpf(condutor.getCpf()) == false){
            throw new RuntimeException(" Seu CPF nao é valido");
        }
        this.condutorRepository.save(condutor);
    }

    @Transactional
    public void atualizarCondutor(final Long id, Condutor condutor){
        final Condutor condutorBanco = this.condutorRepository.findById(id).orElse(null);

        if (condutorBanco == null || !condutorBanco.getId().equals(condutor.getId())) {
            throw new RuntimeException("nao foi possivel identificar o registro informado.");
        }
        if(condutor.getNome().isEmpty()){
            throw new RuntimeException("Debe conter um nome");
        }
        if(condutor.getNome().length() > 100){
            throw new RuntimeException("Maximo 100 carateres");
        }
        if("".equals(condutor.getCpf()) || condutor.getCpf().length() > 14){
            throw new RuntimeException(" CPF nao pode ser nulo e deve conter menos de 15 carateres");
        }
        if("".equals(condutor.getTelefone()) || condutor.getTelefone().length() > 17){
            throw new RuntimeException(" TELEFONE nao pode ser nulo e deve conter menos de 17 carateres");
        }
        if( this.valTelefone.ValTelefone(condutor.getTelefone()) == false) {
            throw new RuntimeException(" Seu telefone nao é valido");
        }
        if (this.valCpf.valCpf(condutor.getCpf()) == false){
            throw new RuntimeException(" Seu CPF nao é valido");
        }
        this.condutorRepository.save(condutor);
    }
}
