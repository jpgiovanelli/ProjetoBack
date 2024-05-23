package br.com.projback.projetoback.repository;

import br.com.projback.projetoback.model.Loja;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface Loja_Repository extends JpaRepository<Loja,Integer> {
    Optional<Loja> findByCnpj(String cnpj);
}
