package br.com.uniamerica.estacionamento.estacionamentoapi.repository;

import br.com.uniamerica.estacionamento.estacionamentoapi.entity.Configuracao;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.time.LocalTime;

@Repository
public interface ConfiguracaoRepository extends JpaRepository<Configuracao, Long> {

    @Query("SELECT x.valorHora FROM Configuracao x WHERE x.valorHora is NOT NULL")
    BigDecimal findByHora();
    @Query("SELECT x.valorMinutoMulta FROM Configuracao x WHERE x.valorMinutoMulta IS NOT NULL")
    BigDecimal findValorMinutoMulta();
    @Query("SELECT x.inicioExpediente FROM Configuracao x where x.inicioExpediente IS NOT NULL")
    LocalTime findInicioExpediente();
    @Query("SELECT x.fimExpediente FROM Configuracao x where x.fimExpediente IS NOT NULL")
    LocalTime findFimExpediente();
    @Query("SELECT x.gerarDesconto FROM Configuracao x where x.gerarDesconto IS NOT NULL")
    Boolean findGerarDesconto();
}
