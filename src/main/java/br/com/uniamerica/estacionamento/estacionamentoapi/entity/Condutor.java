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


import java.time.LocalTime;

@NoArgsConstructor
@AllArgsConstructor
@Audited
@Entity
@Table(name = "tb_condutores", schema = "estacionamento")
@AuditTable(value = "tb_condutores_audit", schema = "audit")
public class Condutor extends AbstractEntity{

    @Getter @Setter
    @Column(name = "nome", length = 100,nullable = false)
    private String nome;

    @Getter @Setter
    @Column(name = "cpf", length = 11,nullable = false, unique = true)
    private String cpf;

    @Getter @Setter
    @Column(name = "telefone", length = 17,nullable = false)
    private String telefone;

    @Getter @Setter
    @Column(name = "tempoPago")
    private LocalTime tempoPago;

    @Getter @Setter
    @Column(name = "tempoDesconto")
    private LocalTime tempoDesconto;


}
