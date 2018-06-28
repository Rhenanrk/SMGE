package br.ufg.smge.domain.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.ufg.smge.domain.model.Discipline;

public interface DisciplineRepository extends JpaRepository<Discipline, Long> {
}
