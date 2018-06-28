package br.ufg.smge.domain.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name = "home_work", schema = "app")
public class HomeWork extends PersistenceModel {

	/**
	 * 
	 */
	private static final long serialVersionUID = -5410445082240578547L;
	
	@Column(name = "expiration_date")
	@Temporal(TemporalType.DATE)
	private Date expirationDate;

	public Date getExpirationDate() {
		return expirationDate;
	}

	public void setExpirationDate(Date expirationDate) {
		this.expirationDate = expirationDate;
	}

}
