package com.example.demo.entity;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

public class Ciclo {
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int id;
	
	@Column(name="nombre", length=60)
	private String nombre;
	
	@Column(name="tipo", length=40)
	private String tipo;
	
	@OneToMany(mappedBy="alumnos", orphanRemoval=true)
	List <Alumno> alumnos = new ArrayList<>();
	
}
