package br.com.uniamerica.estacionamento.estacionamentoapi.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
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
    @JsonFormat(pattern = "HH:mm:ss")
    private LocalTime entrada;

    @Getter @Setter
    @JsonFormat(pattern = "HH:mm:ss")
    @Column(name = "saida")
    private LocalTime saida;

    @Getter @Setter
    @JsonFormat(pattern = "HH:mm:ss")
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
    @Column(name = "valorTotal")
    private BigDecimal valorTotal;

    @Getter @Setter
    @Column(name = "valorHora")
    private BigDecimal valorHora;

    @Getter @Setter
    @Column(name = "valorMinutoMulta")
    private BigDecimal valorMinutoMulta;

}
