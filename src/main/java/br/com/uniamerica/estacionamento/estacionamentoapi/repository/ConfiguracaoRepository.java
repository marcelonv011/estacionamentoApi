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

}
