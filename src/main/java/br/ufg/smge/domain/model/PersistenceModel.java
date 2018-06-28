package br.ufg.smge.domain.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Version;

@MappedSuperclass
public class PersistenceModel implements Serializable {

	private static final long serialVersionUID = -5672715271230634121L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "dta_registro")
	private Date createdAt;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "dta_atualizacao")
	private Date updatedAt;

	@Version
	private Integer version;

	/**
	 * 
	 */
	@PrePersist
	private void adicionarDtaRegistro() {
		this.createdAt = new Date();
	}

	/**
	 * 
	 */
	@PreUpdate
	private void updateUpdatedAt() {
		this.updatedAt = new Date();
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Date getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(Date createdAt) {
		this.createdAt = createdAt;
	}

	public Date getUpdatedAt() {
		return updatedAt;
	}

	public void setUpdatedAt(Date updatedAt) {
		this.updatedAt = updatedAt;
	}

	public Integer getVersion() {
		return version;
	}

	public void setVersion(Integer version) {
		this.version = version;
	}

}
