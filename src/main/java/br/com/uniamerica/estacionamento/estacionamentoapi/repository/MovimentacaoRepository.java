package br.com.uniamerica.estacionamento.estacionamentoapi.repository;

import br.com.uniamerica.estacionamento.estacionamentoapi.entity.Movimentacao;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MovimentacaoRepository extends JpaRepository<Movimentacao, Long> {
}
