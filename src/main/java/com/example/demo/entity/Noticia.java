package com.example.demo.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.springframework.format.annotation.DateTimeFormat;

@Entity
public class Noticia {
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int id;
	
	@Column(name="titulo", length=50)
	@NotNull
	@NotEmpty(message="El campo título no puede ir vacío")
	@Size(min=3, max=50, message="El campo título debe tener entre 3 y 50 caracteres")
	private String titulo;
	
	@Column(name="descripcion", length=255)
	@NotNull
	@NotEmpty(message="El campo descripción no puede ir vacío")
	@Size(min=3, max=255, message="El campo descripción debe tener entre 3 y 255 caracteres")
	private String descripcion;
	
	@Column(name="imagen", length=50)
	private String imagen;
	
	@Column(name="fecha_creacion")
	@Temporal(TemporalType.DATE)
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date fecha_creacion;
	
	@ManyToOne
	@JoinColumn(name="cicloID")
	private Ciclo cicloID;
	

	public Noticia(int id,
			@NotNull @NotEmpty(message = "El campo título no puede ir vacío") @Size(min = 3, max = 50, message = "El campo título debe tener entre 3 y 50 caracteres") String titulo,
			@NotNull @NotEmpty(message = "El campo descripción no puede ir vacío") @Size(min = 3, max = 255, message = "El campo descripción debe tener entre 3 y 255 caracteres") String descripcion,
			String imagen, @NotNull(message = "El campo fecha de creacion no puede ser nulo") Date fecha_creacion,
			Ciclo cicloID) {
		super();
		this.id = id;
		this.titulo = titulo;
		this.descripcion = descripcion;
		this.imagen = imagen;
		this.fecha_creacion = fecha_creacion;
		this.cicloID = cicloID;
	}

	public Noticia() {
		super();
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getTitulo() {
		return titulo;
	}

	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public String getImagen() {
		return imagen;
	}

	public void setImagen(String imagen) {
		this.imagen = imagen;
	}

	public Ciclo getCicloID() {
		return cicloID;
	}

	public void setCicloID(Ciclo cicloID) {
		this.cicloID = cicloID;
	}

	public Date getFecha_creacion() {
		return fecha_creacion;
	}

	public void setFecha_creacion(Date fecha_creacion) {
		this.fecha_creacion = fecha_creacion;
	}
	
	
	
}
