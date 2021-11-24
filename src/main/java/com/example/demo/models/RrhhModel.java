package com.example.demo.models;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import com.example.demo.entity.Oferta;

public class RrhhModel {
	
	private int id;
	
	private String nombre;
	
	private String apellidos;
	
	private String email;
	
	private String password;
	
	private String telefono;
	
	private String empresa;
	
	List <Oferta> rrhhid = new ArrayList<>();

	public RrhhModel(int id, String nombre, String apellidos, String email, String password, String telefono, String empresa,
			List<Oferta> rrhhid) {
		super();
		this.id = id;
		this.nombre = nombre;
		this.apellidos = apellidos;
		this.email = email;
		this.password = password;
		this.telefono = telefono;
		this.empresa = empresa;
		this.rrhhid = rrhhid;
	}

	public RrhhModel() {
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

	public String getTelefono() {
		return telefono;
	}

	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}

	public String getEmpresa() {
		return empresa;
	}

	public void setEmpresa(String empresa) {
		this.empresa = empresa;
	}

	public List<Oferta> getRrhhid() {
		return rrhhid;
	}

	public void setRrhhid(List<Oferta> rrhhid) {
		this.rrhhid = rrhhid;
	}
	
}
