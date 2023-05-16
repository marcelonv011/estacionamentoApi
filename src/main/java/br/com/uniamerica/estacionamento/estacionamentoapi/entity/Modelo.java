package br.com.uniamerica.estacionamento.estacionamentoapi.entity;


import jakarta.persistence.*;
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
@Table(name = "tb_modelos", schema = "estacionamento")
@AuditTable(value = "tb_modelo_audit", schema = "audit")
public class Modelo extends AbstractEntity{

    @Getter @Setter
    @Column(name = "nome", nullable = false, length = 100)
    private String nome;

    @Getter @Setter
    @JoinColumn(name = "id_marca", nullable = false)
    @ManyToOne(fetch = FetchType.EAGER, optional = false)
    private Marca marca;


}
