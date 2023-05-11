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
        if ("".equals(veiculo.getModelo().getNome())){
            throw new RuntimeException(" Tem que inserir o nome do modelo de o veiculo");
        }
        if ("".equals(veiculo.getModelo().getMarca().getNome())){
            throw new RuntimeException(" Tem que inserir o nome da marca de o veiculo");
        }
        if ( veiculo.getCor() == null ){
            throw new RuntimeException(" Tem que colocar o cor de veiculo");
        }
        if ( veiculo.getTipo() == null ) {
            throw new RuntimeException("Tem que colocar o tipo de veiculo");
        }
        if ( veiculoRepository.findByPlaca(veiculo.getPlaca()) != null){
            throw new RuntimeException(" Placa de veiculo ja existe!");
        }
        if ( veiculo.getAno() < 1980){
            throw new RuntimeException(" Tem que colocar um veiculo mais novo");
        }
        if ( veiculo.getModelo().getNome().length() > 100) {
            throw new RuntimeException("O nome de o modelo debe conter menos de 100 carateres");
        }
        if ( veiculo.getModelo().getMarca().getNome().length() > 70){
            throw new RuntimeException(" O nome da marca tem que ser menor de 50 carateres");
        }
        if ( "".equals(veiculo.getModelo().getMarca().getNome())) {
            throw new RuntimeException("Debe conter um nome de marca");
        }
        if ( marcaRepository.findByNome(veiculo.getModelo().getMarca().getNome()) != null) {
            throw new RuntimeException(" O nome da marca ja existe");
        }
        if ( marcaRepository.findByNome(veiculo.getModelo().getNome()) != null){
            throw new RuntimeException(" O nome de o modelo ja existe");
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
        if ("".equals(veiculo.getModelo().getNome())){
            throw new RuntimeException(" Tem que inserir o nome do modelo de o veiculo");
        }
        if ( veiculo.getAno() == 0 ){
            throw new RuntimeException(" Tem que colocar um ano");
        }
        if ("".equals(veiculo.getModelo().getMarca().getNome())){
            throw new RuntimeException(" Tem que inserir o nome da marca de o veiculo");
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
        if ( veiculo.getModelo().getNome().length() > 100) {
            throw new RuntimeException("O nome de o modelo debe conter menos de 100 carateres");
        }
        if ( veiculo.getModelo().getMarca().getNome().length() > 70){
            throw new RuntimeException(" O nome da marca tem que ser menor de 50 carateres");
        }
        if ( "".equals(veiculo.getModelo().getMarca().getNome())) {
            throw new RuntimeException("Debe conter um nome de marca");
        }
        this.veiculoRepository.save(veiculo);
    }
}
