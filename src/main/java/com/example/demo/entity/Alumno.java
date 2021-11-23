package com.example.demo.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
public class Alumno {
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int id;
	
	@Column(name="nombre", length = 40)
	private String nombre;
	
	@Column(name="apellidos", length = 40)
	private String apellidos;
	
	private boolean activo;
	
	@Column(name="email", length = 50)
	private String email;
	
	private String password;
	
	@ManyToOne
	@JoinColumn(name="cicloID")
	private Ciclo cicloID;
	
	
}
