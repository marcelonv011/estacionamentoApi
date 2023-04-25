package br.com.uniamerica.estacionamento.estacionamentoapi.entity;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "tb_modelos", schema = "estacionamento")
public class Modelo extends AbstractEntity{

    @Getter @Setter
    @Column(name = "nome", nullable = false, length = 100)
    private String nome;

    @Getter @Setter
    @JoinColumn(name = "id_marca", nullable = false)
    @ManyToOne(fetch = FetchType.EAGER, optional = false)
    private Marca marca;


}
