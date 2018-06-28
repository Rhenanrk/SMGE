package br.ufg.smge.domain.model;

import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name = "test", schema = "app")
public class Test extends PersistenceModel {

	private static final long serialVersionUID = 4414071267297463745L;

	@Column(name = "expiration_date")
	@Temporal(TemporalType.DATE)
	private Date expirationDate;

	@Column(name = "max_grade", scale = 2)
	private BigDecimal maxGrade;

	@Column(name = "description")
	private String description;

	public Test() {
		// TODO Auto-generated constructor stub
	}

	public Test(Date expirationDate, BigDecimal maxGrade, String description) {
		super();
		this.expirationDate = expirationDate;
		this.maxGrade = maxGrade;
		this.setDescription(description);
	}

	public Date getExpirationDate() {
		return expirationDate;
	}

	public void setExpirationDate(Date expirationDate) {
		this.expirationDate = expirationDate;
	}

	public BigDecimal getMaxGrade() {
		return maxGrade;
	}

	public void setMaxGrade(BigDecimal maxGrade) {
		this.maxGrade = maxGrade;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

}
