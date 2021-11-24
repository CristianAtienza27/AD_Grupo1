package com.example.demo.models;

import java.util.Date;

import com.example.demo.entity.Usuario;

public class OfertaModel {

	private int id;
	
	private String titular;
	
	private String descripcion;
	
	private String requisitos;
	
	private Date fechamax;

	public OfertaModel(int id, String titular, String descripcion, String requisitos, Date fechamax) {
		super();
		this.id = id;
		this.titular = titular;
		this.descripcion = descripcion;
		this.requisitos = requisitos;
		this.fechamax = fechamax;
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
	
	
}
