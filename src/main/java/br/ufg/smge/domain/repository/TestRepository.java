package br.ufg.smge.domain.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.ufg.smge.domain.model.Test;

public interface TestRepository extends JpaRepository<Test, Long> {
}
