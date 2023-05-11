package br.com.uniamerica.estacionamento.estacionamentoapi.repository;

import br.com.uniamerica.estacionamento.estacionamentoapi.entity.Modelo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ModeloRepository extends JpaRepository<Modelo, Long> {

    public List<Modelo> findByAtivo(@Param("ativo") final boolean ativo);

    public Modelo findByNome(String nome);
}
