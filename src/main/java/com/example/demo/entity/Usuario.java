package com.example.demo.entity;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

@Entity
public class Usuario {
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int id;
	
	@Column(name="nombre", length = 40)
	private String nombre;
	
	@Column(name="apellidos", length = 40)
	private String apellidos;
	
	@Column(name="telefono", length=10)
	private String telefono;
	
	private boolean enabled;
	
	@Column(name="email", length = 50)
	private String email;
	
	private String password;
	
	@Column(name="role", length = 20)
	private String role;
	
	@Column(name="empresa", length=200)
	private String empresa;
	
	@ManyToOne
	@JoinColumn(name="cicloID")
	private Ciclo cicloID;
	
	@OneToMany(mappedBy="rrhhid", orphanRemoval=true)
	List <Oferta> rrhh = new ArrayList<>();

	public Usuario(int id, String nombre, String apellidos,String telefono, boolean enabled, String email, String password,
			String role, String empresa) {
		super();
		this.id = id;
		this.nombre = nombre;
		this.apellidos = apellidos;
		this.telefono = telefono;
		this.enabled = false;
		this.email = email;
		this.password = password;
		this.role = role;
	}

	public Usuario() {
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

	public boolean isEnabled() {
		return enabled;
	}

	public void setEnabled(boolean enabled) {
		this.enabled = enabled;
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

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	public String getEmpresa() {
		return empresa;
	}

	public void setEmpresa(String empresa) {
		this.empresa = empresa;
	}
	
	public String getTelefono() {
		return telefono;
	}

	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}


	
	
}
