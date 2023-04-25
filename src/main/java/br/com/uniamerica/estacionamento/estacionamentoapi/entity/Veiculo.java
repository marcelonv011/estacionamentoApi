package br.com.uniamerica.estacionamento.estacionamentoapi.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "tb_veiculos", schema = "estacionamento")
public class Veiculo extends AbstractEntity{

    @Getter @Setter
    @Column(name = "placa", nullable = false, unique = true)
    private String placa;

    @Getter @Setter
    @JoinColumn(name = "id_modelo", nullable = false)
    @ManyToOne(fetch = FetchType.EAGER, optional = false)
    private Modelo modelo;

    @Enumerated(EnumType.STRING)
    private Cor cor;

    @Enumerated(EnumType.STRING)
    private Tipo tipo;

    @Getter @Setter
    @Column(name = "ano", length = 4)
    private int ano;

}
