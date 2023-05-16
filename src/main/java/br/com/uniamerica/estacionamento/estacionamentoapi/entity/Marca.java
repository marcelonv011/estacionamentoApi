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

@NoArgsConstructor
@AllArgsConstructor
@Audited
@Entity
@Table(name = "tb_marcas", schema = "estacionamento")
@AuditTable(value = "tb_marcas_audit", schema = "audit")
public class Marca extends AbstractEntity {

    @Getter @Setter
    @Column(name = "nome", nullable = false, length = 70)
    private String nome;


}