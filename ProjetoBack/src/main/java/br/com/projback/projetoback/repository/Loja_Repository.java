package br.com.projback.projetoback.repository;

import br.com.projback.projetoback.model.Loja;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface Loja_Repository extends JpaRepository<Loja,Integer> {
}
