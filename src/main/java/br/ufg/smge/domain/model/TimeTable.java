package br.ufg.smge.domain.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import br.ufg.smge.domain.model.enumerator.WeekDay;

@Entity
@Table(name = "time_table", schema = "app")
public class TimeTable extends PersistenceModel {

	private static final long serialVersionUID = 6825653497048366930L;

	@ManyToOne
	@JoinColumn(name = "discipline_id")
	private Discipline discipline;

	@ManyToOne
	@JoinColumn(name = "class_room_id")
	private ClassRoom classRoom;

	@Enumerated(EnumType.STRING)
	private WeekDay day;

	@Column(name = "ordinal_time")
	private Integer ordinalTime;
	
	public TimeTable() {
		// TODO Auto-generated constructor stub
	}
	
	public TimeTable(Discipline discipline, ClassRoom classRoom, WeekDay day, Integer ordinalTime) {
		super();
		this.discipline = discipline;
		this.classRoom = classRoom;
		this.day = day;
		this.ordinalTime = ordinalTime;
	}

	public Discipline getDiscipline() {
		return discipline;
	}

	public void setDiscipline(Discipline discipline) {
		this.discipline = discipline;
	}

	public WeekDay getDay() {
		return day;
	}

	public void setDay(WeekDay day) {
		this.day = day;
	}

	public Integer getOrdinalTime() {
		return ordinalTime;
	}

	public void setOrdinalTime(Integer ordinalTime) {
		this.ordinalTime = ordinalTime;
	}

	public ClassRoom getClassRoom() {
		return classRoom;
	}

	public void setClassRoom(ClassRoom classRoom) {
		this.classRoom = classRoom;
	}

}
