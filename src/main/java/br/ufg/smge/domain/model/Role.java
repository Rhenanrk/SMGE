package br.ufg.smge.domain.model;

import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

@Entity
@Table(name = "role", schema = "app")
public class Role extends PersistenceModel {

	private static final long serialVersionUID = -3595787119981587748L;

	@Column(name = "name")
	private String name;

	public Role() {
		// TODO Auto-generated constructor stub
	}

	public Role(String name) {
		super();
		this.name = name;
	}

	@ManyToMany(mappedBy = "roles")
	private Set<User> users;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Set<User> getUsers() {
		return users;
	}

	public void setUsers(Set<User> users) {
		this.users = users;
	}

}
