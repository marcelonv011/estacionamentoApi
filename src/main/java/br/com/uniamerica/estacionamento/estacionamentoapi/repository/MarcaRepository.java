package br.com.uniamerica.estacionamento.estacionamentoapi.repository;

import br.com.uniamerica.estacionamento.estacionamentoapi.entity.Marca;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MarcaRepository extends JpaRepository<Marca, Long> {
}
