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

@Entity
public class Oferta {
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int id;
	
	@Column(name="titular", length=60)
	private String titular;
	
	@Column(name="descripcion", length=255)
	private String descripcion;
	
	@Column(name="requisitos")
	@Lob
	private String requisitos;
	
	@Column(name="fechamax")
	@Temporal(TemporalType.DATE)
	private Date fechamax;
	
	@ManyToOne
	@JoinColumn(name="rrhhid")
	private Usuario rrhhid;

	public Oferta(int id, String titular, String descripcion, String requisitos, Date fechamax, Usuario rrhhid) {
		super();
		this.id = id;
		this.titular = titular;
		this.descripcion = descripcion;
		this.requisitos = requisitos;
		this.fechamax = fechamax;
		this.rrhhid = rrhhid;
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

	public Usuario getRrhhid() {
		return rrhhid;
	}

	public void setRrhhid(Usuario rrhhid) {
		this.rrhhid = rrhhid;
	}
	
	
}
