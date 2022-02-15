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
import javax.persistence.OrderBy;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class Usuario {
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int id;
	
	@Column(name="nombre", length = 40)
	@NotNull
	@NotEmpty(message="El campo nombre no puede ir vacío")
	@Size(min=3, max=40, message="El campo nombre debe tener entre 3 y 40 caracteres")
	private String nombre;
	
	@Column(name="apellidos", length = 40)
	@NotNull
	@NotEmpty(message="El campo apellidos no puede ir vacío")
	@Size(min=3, max=40, message="El campo apellidos debe tener entre 3 y 60 caracteres")
	private String apellidos;
	
	@Column(name="telefono", length=10)
	@NotNull
	@NotEmpty(message="El campo teléfono no puede ir vacío")
	@Size(min=3, max=10, message="El campo teléfono debe tener entre 3 y 60 caracteres")
	private String telefono;
	
	private boolean enabled;
	
	@Column(name="email", length = 50)
	@Email(message="El formato del email no es correcto")
	@NotNull
	@NotEmpty(message="El campo email no puede ir vacío")
	@Size(min=5, max=50, message="El campo email debe tener entre 5 y 60 caracteres")
	private String email;
	
	@NotNull
	@NotEmpty(message="El campo contraseña no puede ir vacío")
	@Size(min=4, max=255, message="El campo contraseña debe tener entre 4 y 255 caracteres")
	private String password;
	
	@Column(name="role", length = 20)
	@NotNull
	@NotEmpty(message="El campo role no puede ir vacío")
	@Size(min=3, max=20, message="El campo role debe tener entre 3 y 20 caracteres")
	private String role;
	
	@Column(name="empresa", length=200)
	private String empresa;
	
	@Column(name="token")
	private String token;
	
	@ManyToOne
	@JoinColumn(name="cicloID")
	private Ciclo cicloID;
	
	@JsonIgnore
	@OneToMany(mappedBy="rrhhid", orphanRemoval=true)
	@OrderBy("fechamax ASC")
	List <Oferta> rrhh = new ArrayList<>();
	
	

	public Usuario(int id, String email, String password)
	{
		super();
		this.id = id;
		this.email = email;
		this.password = password;
	}

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
	
	public Usuario(int id, String nombre, String apellidos,String telefono, boolean enabled, String email, String password,
			String role, String empresa, Ciclo cicloID) {
		super();
		this.id = id;
		this.nombre = nombre;
		this.apellidos = apellidos;
		this.telefono = telefono;
		this.enabled = false;
		this.email = email;
		this.password = password;
		this.role = role;
		this.cicloID = cicloID;
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
	
	public Ciclo getCicloID() {
		return cicloID;
	}

	public void setCicloID(Ciclo cicloID) {
		this.cicloID = cicloID;
	}

	public List<Oferta> getRrhh() {
		return rrhh;
	}

	public void setRrhh(List<Oferta> rrhh) {
		this.rrhh = rrhh;
	}

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}
	
}
