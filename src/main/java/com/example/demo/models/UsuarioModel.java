package com.example.demo.models;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import com.example.demo.entity.Ciclo;
import com.example.demo.entity.Oferta;

public class UsuarioModel {

	private int id;
	
	private String nombre;
	
	private String apellidos;
	
	private String telefono;
	
	private boolean enabled;
	
	private String email;
	
	private String password;
	
	private String role;
	
	private String empresa;
	
//	private int cicloID;
	
	public UsuarioModel(int id, String nombre, String apellidos,String telefono, boolean enabled, String email, String password,
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

	public UsuarioModel() {
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
	
//	public int getCicloID() {
//		return cicloID;
//	}
//
//	public void setCicloID(int cicloID) {
//		this.cicloID = cicloID;
//	}

	@Override
	public String toString() {
		return "UsuarioModel [id=" + id + ", nombre=" + nombre + ", apellidos=" + apellidos + ", telefono=" + telefono
				+ ", enabled=" + enabled + ", email=" + email + ", password=" + password + ", role=" + role
				+ ", empresa=" + empresa + "]";
	}
	
}
