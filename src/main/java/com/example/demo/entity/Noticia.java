package com.example.demo.entity;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

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
	
}
