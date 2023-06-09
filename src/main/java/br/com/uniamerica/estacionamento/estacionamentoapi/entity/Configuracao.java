package br.com.uniamerica.estacionamento.estacionamentoapi.entity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.envers.AuditTable;
import org.hibernate.envers.Audited;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.time.LocalTime;

@NoArgsConstructor
@AllArgsConstructor
@Audited
@Entity
@Component
@Table(name = "tb_configuracoes", schema = "estacionamento")
@AuditTable(value = "tb_configuracoes_audit", schema = "audit")
public class Configuracao extends AbstractEntity{

    @Getter @Setter
    @Column(name = "valorHora", nullable = false)
    private BigDecimal valorHora;

    @Getter @Setter
    @Column(name = "valorMinutoMulta")
    private BigDecimal valorMinutoMulta;

    @Getter @Setter
    @Column(name = "inicioExpediente")
    private LocalTime inicioExpediente;

    @Getter @Setter
    @Column(name = "fimExpediente")
    private LocalTime fimExpediente;

    @Getter @Setter
    @Column(name = "tempoParaDesconto")
    private LocalTime tempoParaDesconto;

    @Getter @Setter
    @Column(name = "tempoDeDesconto")
    private LocalTime tempoDeDesconto;

    @Getter @Setter
    @Column(name = "gerarDesconto")
    private boolean gerarDesconto;

    @Getter @Setter
    @Column(name = "vagasMoto", nullable = false)
    private int vagasMoto;

    @Getter @Setter
    @Column(name = "vagasCarro", nullable = false)
    private int vagasCarro;

    @Getter @Setter
    @Column(name = "vagasVam", nullable = false)
    private int vagasVam;

}
