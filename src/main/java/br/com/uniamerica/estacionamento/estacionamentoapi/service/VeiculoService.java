package br.com.uniamerica.estacionamento.estacionamentoapi.service;

import br.com.uniamerica.estacionamento.estacionamentoapi.entity.Cor;
import br.com.uniamerica.estacionamento.estacionamentoapi.entity.Veiculo;
import br.com.uniamerica.estacionamento.estacionamentoapi.repository.MarcaRepository;
import br.com.uniamerica.estacionamento.estacionamentoapi.repository.VeiculoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class VeiculoService {

    @Autowired
    private VeiculoRepository veiculoRepository;
    @Autowired
    private MarcaRepository marcaRepository;

    @Transactional
    public void cadastrarVeiculo(Veiculo veiculo){
        if ("".equals(veiculo.getPlaca())){
            throw new RuntimeException(" Tem que inserir a placa do veiculo");
        }
        if (veiculo.getCor() == null){
            throw new RuntimeException(" Tem que colocar o cor de veiculo");
        }
        if (veiculo.getTipo() == null ) {
            throw new RuntimeException(" Tem que colocar o tipo de veiculo");
        }
        if ( veiculo.getPlaca() == null ) {
            throw new RuntimeException(" A placa de o veiculo nao pode ser nulo");
        }
        if ( veiculoRepository.findByPlaca(veiculo.getPlaca()) != null){
            throw new RuntimeException(" Placa de veiculo ja existe!");
        }
        if ( veiculo.getAno() < 1980){
            throw new RuntimeException(" Tem que colocar um veiculo mais novo");
        }
        if ( veiculo.getAno() > 2023) {
            throw new RuntimeException( " O ano de o veiculo nao pode ser maior a o ano atual ");
        }

        this.veiculoRepository.save(veiculo);
    }

    @Transactional
    public void atualizarVeiculo(final Long id, Veiculo veiculo){

        final Veiculo veiculoBanco = this.veiculoRepository.findById(id).orElse(null);

        if (veiculoBanco == null || !veiculoBanco.getId().equals(veiculo.getId())){
            throw new RuntimeException("nao foi possivel identificar o registro informado.");
        }
        if ("".equals(veiculo.getPlaca())){
            throw new RuntimeException(" Tem que inserir a placa do veiculo");
        }
        if ( veiculo.getCor() == null ){
            throw new RuntimeException(" Tem que colocar o cor de veiculo");
        }
        if ( veiculo.getTipo() == null ) {
            throw new RuntimeException("Tem que colocar o tipo de veiculo");
        }
        if ( veiculo.getAno() < 1980){
            throw new RuntimeException(" Tem que colocar um veiculo mais novo");
        }
        this.veiculoRepository.save(veiculo);
    }
}
