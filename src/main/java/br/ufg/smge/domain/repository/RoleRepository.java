package br.ufg.smge.domain.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.ufg.smge.domain.model.Role;

public interface RoleRepository extends JpaRepository<Role, Long> {
}
