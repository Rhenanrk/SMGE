package br.ufg.smge.domain.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.ufg.smge.domain.model.ClassRoom;

public interface ClassRoomRepository extends JpaRepository<ClassRoom, Long> {
}
