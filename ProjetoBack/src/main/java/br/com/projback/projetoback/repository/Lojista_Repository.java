package br.com.projback.projetoback.repository;
import br.com.projback.projetoback.model.Lojista;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface Lojista_Repository extends JpaRepository<Lojista,Integer> {
}
