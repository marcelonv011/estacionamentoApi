package br.com.uniamerica.estacionamento.estacionamentoapi.service;

import br.com.uniamerica.estacionamento.estacionamentoapi.configs.ValCpf;
import br.com.uniamerica.estacionamento.estacionamentoapi.configs.ValTelefone;
import br.com.uniamerica.estacionamento.estacionamentoapi.entity.Movimentacao;
import br.com.uniamerica.estacionamento.estacionamentoapi.repository.CondutorRepository;
import br.com.uniamerica.estacionamento.estacionamentoapi.repository.MarcaRepository;
import br.com.uniamerica.estacionamento.estacionamentoapi.repository.MovimentacaoRepository;
import br.com.uniamerica.estacionamento.estacionamentoapi.repository.VeiculoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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
    private VeiculoRepository veiculoRepository;
    @Autowired
    private CondutorRepository condutorRepository;
    @Autowired
    private MarcaRepository marcaRepository;

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
        if(movimentacao.getSaida() != null){
            LocalTime tempo = movimentacao.getSaida()
                    .minusHours(movimentacao.getEntrada().getHour())
                    .minusMinutes(movimentacao.getEntrada().getMinute())
                    .minusSeconds(movimentacao.getEntrada().getSecond())
                    .minusNanos(movimentacao.getEntrada().getNano());
            movimentacao.setTempo(tempo);
        }
        if("".equals(movimentacao.getValorTotal())){
            throw new RuntimeException(" Deve colocar o valor total");
        }
        if("".equals(movimentacao.getValorHora())){
            throw new RuntimeException(" Deve colocar o valor da hora");
        }
        if( this.valTelefone.ValTelefone(movimentacao.getCondutor().getTelefone()) == false) {
            throw new RuntimeException(" o telefone de o condutor nao é valido");
        }
        if (this.valCpf.valCpf(movimentacao.getCondutor().getCpf()) == false){
            throw new RuntimeException(" Seu CPF nao é valido");
        }
        if ( "".equals(movimentacao.getVeiculo().getPlaca())){
            throw new RuntimeException(" Tem que inserir a placa do veiculo");
        }
        if ( "".equals(movimentacao.getVeiculo().getModelo().getNome())){
            throw new RuntimeException(" Tem que inserir o nome do modelo de o veiculo");
        }
        if ( "".equals(movimentacao.getVeiculo().getModelo().getMarca().getNome())){
            throw new RuntimeException(" Tem que inserir o nome da marca de o veiculo");
        }
        if ( movimentacao.getVeiculo().getCor() == null) {
            throw new RuntimeException(" Tem que colocar o cor de veiculo");
        }
        if ( movimentacao.getVeiculo().getTipo() == null) {
            throw new RuntimeException(" Tem que colocar o tipo de veiculo");
        }

        if ( movimentacao.getVeiculo().getAno() < 1900){
            throw new RuntimeException(" Tem que colocar o ano de o veiculo mais novo");
        }
        if ( movimentacao.getVeiculo().getModelo().getNome().length() > 100){
            throw new RuntimeException("O nome de o modelo debe conter menos de 100 carateres");
        }
        if ( movimentacao.getVeiculo().getModelo().getMarca().getNome().length() > 70) {
            throw new RuntimeException(" O nome da marca tem que ser menor de 50 carateres");
        }
        if ( "".equals(movimentacao.getVeiculo().getModelo().getMarca().getNome())){
            throw new RuntimeException("Debe conter um nome de marca");
        }
        if ( "".equals(movimentacao.getCondutor().getNome())){
            throw new RuntimeException(" Debe conter um nome de o condutor");
        }
        if ( movimentacao.getCondutor().getNome().length() > 100) {
            throw new RuntimeException("Maximo 100 carateres de o nome de condutor");
        }
        if ( "".equals(movimentacao.getCondutor().getTempoPago())){
            throw new RuntimeException(" Tempo pago de o condutor nao pode ser nulo");
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
        if(movimentacao.getSaida() != null){
            LocalTime tempo = movimentacao.getSaida()
                    .minusHours(movimentacao.getEntrada().getHour())
                    .minusMinutes(movimentacao.getEntrada().getMinute())
                    .minusSeconds(movimentacao.getEntrada().getSecond())
                    .minusNanos(movimentacao.getEntrada().getNano());
            movimentacao.setTempo(tempo);
        }
        if("".equals(movimentacao.getValorTotal())){
            throw new RuntimeException(" Deve colocar o valor total");
        }
        if("".equals(movimentacao.getValorHora())){
            throw new RuntimeException(" Deve colocar o valor da hora");
        }
        if( this.valTelefone.ValTelefone(movimentacao.getCondutor().getTelefone()) == false) {
            throw new RuntimeException(" Seu telefone nao é valido");
        }
        if (this.valCpf.valCpf(movimentacao.getCondutor().getCpf()) == false){
            throw new RuntimeException(" Seu CPF nao é valido");
        }
        if ( "".equals(movimentacao.getVeiculo().getPlaca())){
            throw new RuntimeException(" Tem que inserir a placa do veiculo");
        }
        if ( "".equals(movimentacao.getVeiculo().getModelo().getNome())){
            throw new RuntimeException(" Tem que inserir o nome do modelo de o veiculo");
        }
        if ( "".equals(movimentacao.getVeiculo().getModelo().getMarca().getNome())){
            throw new RuntimeException(" Tem que inserir o nome da marca de o veiculo");
        }
        if ( movimentacao.getVeiculo().getCor() == null) {
            throw new RuntimeException(" Tem que colocar o cor de veiculo");
        }
        if ( movimentacao.getVeiculo().getTipo() == null) {
            throw new RuntimeException(" Tem que colocar o tipo de veiculo");
        }
        if ( movimentacao.getVeiculo().getAno() < 1980){
            throw new RuntimeException(" Tem que colocar o ano de o veiculo mais novo");
        }
        if ( movimentacao.getVeiculo().getModelo().getNome().length() > 100){
            throw new RuntimeException("O nome de o modelo debe conter menos de 100 carateres");
        }
        if ( movimentacao.getVeiculo().getModelo().getMarca().getNome().length() > 70) {
            throw new RuntimeException(" O nome da marca tem que ser menor de 50 carateres");
        }
        if ( "".equals(movimentacao.getVeiculo().getModelo().getMarca().getNome())){
            throw new RuntimeException("Debe conter um nome de marca");
        }
        if ( "".equals(movimentacao.getCondutor().getNome())){
            throw new RuntimeException(" Debe conter um nome");
        }
        if ( movimentacao.getCondutor().getNome().length() > 100) {
            throw new RuntimeException("Maximo 100 carateres");
        }
        if ( "".equals(movimentacao.getCondutor().getTempoPago())){
            throw new RuntimeException(" Tempo pago nao pode ser nulo");
        }

        this.movimentacaoRepository.save(movimentacao);
    }

}
