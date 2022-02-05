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
import javax.validation.constraints.NotNull;

@Entity
public class Inscrito {
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int id;
	
	@ManyToOne
	@JoinColumn(name="idAlumno")
	private Usuario idAlumno;
	
	@ManyToOne
	@JoinColumn(name="idOferta")
	private Oferta idOferta;
	
	@Column(name="fechasinscripcion")
	@Temporal(TemporalType.DATE)
	@NotNull(message="El campo fecha de inscripción no puede ser nulo")
	private Date fechainscripcion;
	
	public Inscrito(int id, Usuario idAlumno, Oferta idOferta, Date fecha_inscripcion) {
		super();
		this.id = id;
		this.idAlumno = idAlumno;
		this.idOferta = idOferta;
		this.fechainscripcion = fecha_inscripcion;
	}

	public Inscrito() {
		super();
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Usuario getIdAlumno() {
		return idAlumno;
	}

	public void setIdAlumno(Usuario idAlumno) {
		this.idAlumno = idAlumno;
	}

	public Oferta getIdOferta() {
		return idOferta;
	}

	public void setIdOferta(Oferta idOferta) {
		this.idOferta = idOferta;
	}

	public Date getFecha_inscripcion() {
		return fechainscripcion;
	}

	public void setFecha_inscripcion(Date fecha_inscripcion) {
		this.fechainscripcion = fecha_inscripcion;
	}

	@Override
	public String toString() {
		return "Inscrito [id=" + id + ", idAlumno=" + idAlumno + ", idOferta=" + idOferta + ", fecha_inscripcion="
				+ fechainscripcion + "]";
	}
	
	
}
