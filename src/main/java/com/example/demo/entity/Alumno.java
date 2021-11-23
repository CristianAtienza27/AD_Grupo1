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

	public Alumno(int id, String nombre, String apellidos, boolean activo, String email, String password,
			Ciclo cicloID) {
		super();
		this.id = id;
		this.nombre = nombre;
		this.apellidos = apellidos;
		this.activo = activo;
		this.email = email;
		this.password = password;
		this.cicloID = cicloID;
	}
	

	public Alumno() {
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

	public String getApellidos() {
		return apellidos;
	}

	public void setApellidos(String apellidos) {
		this.apellidos = apellidos;
	}

	public boolean isActivo() {
		return activo;
	}

	public void setActivo(boolean activo) {
		this.activo = activo;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Ciclo getCicloID() {
		return cicloID;
	}

	public void setCicloID(Ciclo cicloID) {
		this.cicloID = cicloID;
	}
	
	
}
