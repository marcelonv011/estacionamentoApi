package br.com.uniamerica.estacionamento.estacionamentoapi.configs;

import br.com.uniamerica.estacionamento.estacionamentoapi.entity.Movimentacao;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.LocalTime;

@Component
public class Recibo {
    public static String gerar(LocalTime entrada, LocalTime saida, String condutor, String placa, LocalTime tempo, BigDecimal valorTotal){
        if(saida == null){
            return "Registro realizado";
        } else {
            return "-- RECIBO DE MOVIMENTAÇÃO --\n" +
                    "Entrada: " + entrada + "\n" +
                    "Saída: " + saida + "\n" +
                    "Nome do Condutor: " + condutor + "\n" +
                    "Placa do Veículo: " + placa + "\n" +
                    "Quantidade de horas no estacionamento: " + tempo + "\n" +
                    "Valor total: " + valorTotal + "\n" +
                    "Recibo gerado em: " + LocalDateTime.now();
        }
    }

    public static String gerarRecibo(Movimentacao movimentacao) {
        LocalTime entrada = movimentacao.getEntrada();
        LocalTime saida = movimentacao.getSaida();
        String condutor = movimentacao.getCondutor().getNome();
        String placa = movimentacao.getVeiculo().getPlaca();
        LocalTime tempo = movimentacao.getTempo();
        BigDecimal valorTotal = movimentacao.getValorTotal();

        return gerar(entrada, saida, condutor, placa, tempo, valorTotal);
    }
}
