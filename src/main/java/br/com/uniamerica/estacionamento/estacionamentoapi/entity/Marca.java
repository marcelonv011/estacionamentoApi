package br.com.uniamerica.estacionamento.estacionamentoapi.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "tb_marcas", schema = "estacionamento")
public class Marca extends AbstractEntity {

    @Getter @Setter
    @Column(name = "nome", nullable = false, length = 70)
    private String nome;


}