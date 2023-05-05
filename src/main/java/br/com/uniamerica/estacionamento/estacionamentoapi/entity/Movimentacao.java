package br.com.uniamerica.estacionamento.estacionamentoapi.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.LocalTime;

@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "tb_movimentacoes", schema = "estacionamento")
public class Movimentacao extends AbstractEntity{

    @Getter @Setter
    @JoinColumn(name = "id_veiculo", nullable = false)
    @ManyToOne(fetch = FetchType.EAGER, optional = false)
    private Veiculo veiculo;

    @Getter @Setter
    @JoinColumn(name = "id_condutor", nullable = false)
    @ManyToOne(fetch = FetchType.EAGER, optional = false)
    private Condutor condutor;

    @Getter @Setter
    @Column(name = "entrada", nullable = false)
    private LocalDateTime entrada;

    @Getter @Setter
    @Column(name = "saida")
    private LocalDateTime saida;

    @Getter @Setter
    @Column(name = "tempo")
    private LocalTime tempo;

    @Getter @Setter
    @Column(name = "tempoDesconto")
    private LocalTime tempoDesconto;

    @Getter @Setter
    @Column(name = "tempoMulta")
    private LocalTime tempoMulta;

    @Getter @Setter
    @Column(name = "valorDesconto")
    private BigDecimal valorDesconto;

    @Getter @Setter
    @Column(name = "valorMulta")
    private BigDecimal valorMulta;

    @Getter @Setter
    @Column(name = "valorTotal", nullable = false)
    private BigDecimal valorTotal;

    @Getter @Setter
    @Column(name = "valorHora", nullable = false)
    private BigDecimal valorHora;

    @Getter @Setter
    @Column(name = "valorHoraMulta")
    private BigDecimal valorHoraMulta;

}
