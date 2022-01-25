package com.example.demo.models;

import java.util.Date;

import com.example.demo.entity.Usuario;
import com.example.demo.entity.Oferta;

public class InscritoModel {

	private int id;
	
	private Usuario idAlumno;
	
	private Oferta idOferta;
	
	private Date fecha_inscripcion;
	

	public InscritoModel(int id, Usuario idAlumno, Oferta idOferta, Date fecha_inscripcion) {
		super();
		this.id = id;
		this.idAlumno = idAlumno;
		this.idOferta = idOferta;
		this.fecha_inscripcion = fecha_inscripcion;
	}
	
	public InscritoModel() {
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
		return fecha_inscripcion;
	}

	public void setFecha_inscripcion(Date fecha_inscripcion) {
		this.fecha_inscripcion = fecha_inscripcion;
	}
	
	
}
