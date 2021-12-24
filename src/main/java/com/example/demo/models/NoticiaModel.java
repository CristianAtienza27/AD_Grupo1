package com.example.demo.models;

import com.example.demo.entity.Ciclo;

public class NoticiaModel {

	private int id;
	
	private String titulo;
	
	private String descripcion;
	
	private String imagen;
	
	private Ciclo cicloID;

	public NoticiaModel(int id, String titulo, String descripcion, String imagen) {
		super();
		this.id = id;
		this.titulo = titulo;
		this.descripcion = descripcion;
		this.imagen = imagen;
	}

	public NoticiaModel(int id, String titulo, String descripcion, String imagen, Ciclo cicloID) {
		super();
		this.id = id;
		this.titulo = titulo;
		this.descripcion = descripcion;
		this.imagen = imagen;
		this.cicloID = cicloID;
	}

	public NoticiaModel() {
		super();
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getTitulo() {
		return titulo;
	}

	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public String getImagen() {
		return imagen;
	}

	public void setImagen(String imagen) {
		this.imagen = imagen;
	}

	public Ciclo getCicloID() {
		return cicloID;
	}

	public void setCicloID(Ciclo cicloID) {
		this.cicloID = cicloID;
	}
	
}
