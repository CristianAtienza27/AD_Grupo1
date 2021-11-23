package com.example.demo.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
public class Noticia {
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int id;
	
	@Column(name="titulo", length=50)
	private String titulo;
	
	@Column(name="descripcion", length=255)
	private String descripcion;
	
	@Column(name="imagen", length=50)
	private String imagen;
	
	@ManyToOne
	@JoinColumn(name="cicloID")
	private Ciclo cicloID;

	public Noticia(int id, String titulo, String descripcion, String imagen, Ciclo cicloID) {
		super();
		this.id = id;
		this.titulo = titulo;
		this.descripcion = descripcion;
		this.imagen = imagen;
		this.cicloID = cicloID;
	}

	public Noticia() {
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
