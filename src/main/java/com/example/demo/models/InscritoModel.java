package com.example.demo.models;

import java.util.Date;

import com.example.demo.entity.Alumno;
import com.example.demo.entity.Oferta;

public class InscritoModel {

	private int id;
	
	private Alumno idAlumno;
	
	private Oferta idOferta;
	
	private Date fecha_inscripcion;

	public InscritoModel(int id, Alumno idAlumno, Oferta idOferta, Date fecha_inscripcion) {
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

	public Alumno getIdAlumno() {
		return idAlumno;
	}

	public void setIdAlumno(Alumno idAlumno) {
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
