package br.ufg.smge.domain.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import br.ufg.smge.domain.model.Atividade;

public interface AtividadeRepository extends JpaRepository<Atividade, Long> {

}
