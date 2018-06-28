package br.ufg.smge.domain.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import br.ufg.smge.domain.model.ClassRoom;
import br.ufg.smge.domain.model.TimeTable;

public interface TimeTableRepository extends JpaRepository<TimeTable, Long> {
	
	public List<TimeTable> findByClassRoom(ClassRoom classRoom);
	
}
