package br.com.uniamerica.estacionamento.estacionamentoapi.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


import java.time.LocalTime;

@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "tb_condutores", schema = "estacionamento")
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
    @Column(name = "tempoPago", nullable = false)
    private LocalTime tempoPago;

    @Getter @Setter
    @Column(name = "tempoDesconto")
    private LocalTime tempoDesconto;


}
