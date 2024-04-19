package br.com.projback.projetoback.repository;

import br.com.projback.projetoback.model.Endereco;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface Endereco_Repository extends JpaRepository<Endereco, Integer> {
}
