package br.com.uniamerica.estacionamento.estacionamentoapi.repository;

import br.com.uniamerica.estacionamento.estacionamentoapi.entity.Marca;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MarcaRepository extends JpaRepository<Marca, Long> {

    public List<Marca> findByAtivo(@Param("ativo") final boolean ativo);

    public Marca findByNome(String nome);
}
