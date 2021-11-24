package com.example.demo.models;

import java.util.ArrayList;
import java.util.List;

import com.example.demo.entity.Usuario;

public class CicloModel {

	private int id;
	
	private String nombre;
	
	private String tipo;

	public CicloModel(int id, String nombre, String tipo) {
		super();
		this.id = id;
		this.nombre = nombre;
		this.tipo = tipo;
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
	
	
}
