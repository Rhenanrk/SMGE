package br.ufg.smge.domain.model;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "class_room", schema = "app")
public class ClassRoom extends PersistenceModel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 3761161874421767034L;

	@Column(name = "name")
	private String name;

	@OneToMany(mappedBy = "classRoom")
	private List<TimeTable> timeTables;

	@OneToMany(mappedBy = "classRoom")
	private Set<User> users;

	public ClassRoom() {
		// TODO Auto-generated constructor stub
	}

	public ClassRoom(String name) {
		super();
		this.name = name;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public List<TimeTable> getTimeTables() {
		if (this.timeTables == null) {
			this.timeTables = new ArrayList<TimeTable>();
		}
		return timeTables;
	}

	public void setTimeTables(List<TimeTable> timeTables) {
		this.timeTables = timeTables;
	}

	public Set<User> getAlunos() {
		return users;
	}

	public void setAlunos(Set<User> alunos) {
		this.users = alunos;
	}

}