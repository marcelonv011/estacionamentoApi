package br.com.uniamerica.estacionamento.estacionamentoapi.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.envers.AuditTable;
import org.hibernate.envers.Audited;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.temporal.ChronoUnit;

@NoArgsConstructor
@AllArgsConstructor
@Entity
@Audited
@Table(name = "tb_movimentacoes", schema = "estacionamento")
@AuditTable(value = "tb_movimentacoes_audit", schema = "audit")
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
    private LocalTime entrada;

    @Getter @Setter
    @Column(name = "saida", nullable = false)
    private LocalTime saida;

    @Getter @Setter
    @Column(name = "tempo", nullable = false)
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
