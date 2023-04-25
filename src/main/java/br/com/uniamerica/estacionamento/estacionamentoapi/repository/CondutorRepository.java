package br.com.uniamerica.estacionamento.estacionamentoapi.repository;

import br.com.uniamerica.estacionamento.estacionamentoapi.entity.Condutor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CondutorRepository extends JpaRepository<Condutor, Long> {
}
