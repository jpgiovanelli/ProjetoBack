package br.com.projback.projetoback.repository;

import br.com.projback.projetoback.model.DadoBancario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DadoBancario_Repository extends JpaRepository<DadoBancario, Integer> {
}
