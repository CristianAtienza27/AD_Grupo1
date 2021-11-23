package com.example.demo.entity;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

public class Rrhh {
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int id;
	
	@Column(name="nombre", length=30)
	private String nombre;
	
	@Column(name="apellidos", length=50)
	private String apellidos;
	
	@Column(name="email", length=50)
	private String email;
	
	private String password;
	
	@Column(name="telefono", length=10)
	private String telefono;
	
	@Column(name="empresa", length=200)
	private String empresa;
	
	@OneToMany(mappedBy="ofertas", orphanRemoval=true)
	List <Rrhh> rrhhid = new ArrayList<>();
}
