package com.example.demo.models;

import java.util.ArrayList;
import java.util.List;

import com.example.demo.entity.Alumno;

public class CicloModel {

	private int id;
	
	private String nombre;
	
	private String tipo;
	
	List <Alumno> alumnos = new ArrayList<>();

	public CicloModel(int id, String nombre, String tipo, List<Alumno> alumnos) {
		super();
		this.id = id;
		this.nombre = nombre;
		this.tipo = tipo;
		this.alumnos = alumnos;
	}
	
	

	public CicloModel() {
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
