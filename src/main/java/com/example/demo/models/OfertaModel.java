package com.example.demo.models;

import java.util.Date;

import com.example.demo.entity.Usuario;

public class OfertaModel {

	private int id;
	
	private String titular;
	
	private String descripcion;
	
	private String requisitos;
	
	private Date fechamax;
	
	private int numCandidatos;
	
	private Usuario rrhhid;
	
	
	

	public OfertaModel(int id, String titular, String descripcion, String requisitos, Date fechamax, int numCandidatos,
			Usuario rrhhid) {
		super();
		this.id = id;
		this.titular = titular;
		this.descripcion = descripcion;
		this.requisitos = requisitos;
		this.fechamax = fechamax;
		this.numCandidatos = numCandidatos;
		this.rrhhid = rrhhid;
	}

	public Usuario getRrhhid() {
		return rrhhid;
	}

	public void setRrhhid(Usuario rrhhid) {
		this.rrhhid = rrhhid;
	}

	public OfertaModel() {
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
	
	
}
