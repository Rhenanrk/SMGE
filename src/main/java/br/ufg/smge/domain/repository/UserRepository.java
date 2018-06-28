package br.ufg.smge.domain.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.ufg.smge.domain.model.User;

public interface UserRepository extends JpaRepository<User, Long> {
    User findByUsername(String username);
}
