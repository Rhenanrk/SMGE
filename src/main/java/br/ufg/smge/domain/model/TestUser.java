package br.ufg.smge.domain.model;

import java.io.Serializable;
import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "test_user", schema = "app")
public class TestUser implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -1297918316858827373L;

	@Id
	@ManyToOne
	@JoinColumn(name = "test_id")
	private Test test;

	@Id
	@ManyToOne
	@JoinColumn(name = "user_id")
	private User user;

	@Column(name = "grade", scale = 2)
	private BigDecimal grade;

	public Test getTest() {
		return test;
	}

	public void setTest(Test test) {
		this.test = test;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public BigDecimal getGrade() {
		return grade;
	}

	public void setGrade(BigDecimal grade) {
		this.grade = grade;
	}

}
