package com.example.demo.entity;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

@Entity
public class Ciclo {
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int id;
	
	@Column(name="nombre", length=60)
	private String nombre;
	
	@Column(name="tipo", length=40)
	private String tipo;
	
	@OneToMany(mappedBy="cicloID", orphanRemoval=true)
	List <Alumno> alumnos = new ArrayList<>();

	public Ciclo(int id, String nombre, String tipo, List<Alumno> alumnos) {
		super();
		this.id = id;
		this.nombre = nombre;
		this.tipo = tipo;
		this.alumnos = alumnos;
	}
	
	

	public Ciclo() {
		super();
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getTipo() {
		return tipo;
	}

	public void setTipo(String tipo) {
		this.tipo = tipo;
	}

	public List<Alumno> getAlumnos() {
		return alumnos;
	}

	public void setAlumnos(List<Alumno> alumnos) {
		this.alumnos = alumnos;
	}
	
	
}
