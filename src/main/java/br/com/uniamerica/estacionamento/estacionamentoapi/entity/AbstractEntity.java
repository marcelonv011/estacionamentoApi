package br.com.uniamerica.estacionamento.estacionamentoapi.entity;


import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;


import java.time.LocalDateTime;

@MappedSuperclass
public abstract class AbstractEntity {

    @Id
    @Getter
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "id", nullable = false, unique = true)
    private Long id;

    @Getter @Setter
    @Column(name = "dtCadastro", nullable = false)
    private LocalDateTime cadastro;

    @Getter @Setter
    @Column(name = "dtAtualizacao")
    private LocalDateTime atualizacao;

    @Getter @Setter
    @Column(name = "ativo", nullable = false)
    private boolean ativo;


    public AbstractEntity(){}

    @PrePersist
    public void prePersist(){
        this.cadastro = LocalDateTime.now();
        this.ativo = true;
    }

    @PreUpdate
    public void preUpdate(){
        this.atualizacao = LocalDateTime.now();
    }

}
