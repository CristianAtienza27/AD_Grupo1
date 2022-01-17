package com.example.demo.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.springframework.format.annotation.DateTimeFormat;

@Entity
public class Oferta {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;

	@Column(name = "titular", length = 60)
	@NotNull
	@NotEmpty(message = "El campo titular no puede ir vacío")
	@Size(min = 3, max = 60, message = "El campo titular debe tener entre 3 y 60 caracteres")
	private String titular;

	@Column(name = "descripcion", length = 255)
	@NotNull
	@NotEmpty(message = "El campo descripción no puede ir vacío")
	@Size(min = 3, max = 255, message = "El campo descripción debe tener entre 3 y 255 caracteres")
	private String descripcion;

	@Column(name = "requisitos")
	@Lob
	@NotEmpty(message = "El campo requisitos no puede ir vacío")
	private String requisitos;

	@Column(name = "fechamax")
	@Temporal(TemporalType.DATE)
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date fechamax;

	@Column(name = "numCandidatos")
	@NotNull(message = "El campo número de candidatos no puede ir vacío")
	private int numCandidatos;

	@ManyToOne
	@JoinColumn(name = "rrhhid")
	private Usuario rrhhid;

	@ManyToOne
	@JoinColumn(name = "cicloid")
	private Ciclo cicloid;

	public Oferta(int id, String titular, String descripcion, String requisitos, Date fechamax) {
		super();
		this.id = id;
		this.titular = titular;
		this.descripcion = descripcion;
		this.requisitos = requisitos;
		this.fechamax = fechamax;
	}

	public Oferta(int id, String titular, String descripcion, String requisitos, Date fechamax, Usuario rrhhid) {
		super();
		this.id = id;
		this.titular = titular;
		this.descripcion = descripcion;
		this.requisitos = requisitos;
		this.fechamax = fechamax;
		this.rrhhid = rrhhid;
	}

	public Oferta(int id, String titular, String descripcion, String requisitos, Date fechamax, int numCandidatos,
			Usuario rrhhid, Ciclo cicloid) {
		super();
		this.id = id;
		this.titular = titular;
		this.descripcion = descripcion;
		this.requisitos = requisitos;
		this.fechamax = fechamax;
		this.numCandidatos = numCandidatos;
		this.rrhhid = rrhhid;
		this.cicloid = cicloid;
	}

	public Oferta() {
		super();
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getTitular() {
		return titular;
	}

	public void setTitular(String titular) {
		this.titular = titular;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public String getRequisitos() {
		return requisitos;
	}

	public void setRequisitos(String requisitos) {
		this.requisitos = requisitos;
	}

	public Date getFechamax() {
		return fechamax;
	}

	public void setFechamax(Date fechamax) {
		this.fechamax = fechamax;
	}

	public int getNumCandidatos() {
		return numCandidatos;
	}

	public void setNumCandidatos(int numCandidatos) {
		this.numCandidatos = numCandidatos;
	}

	public Usuario getRrhhid() {
		return rrhhid;
	}

	public void setRrhhid(Usuario rrhhid) {
		this.rrhhid = rrhhid;
	}

	public Ciclo getCicloid() {
		return cicloid;
	}

	public void setCicloid(Ciclo cicloid) {
		this.cicloid = cicloid;
	}

}
