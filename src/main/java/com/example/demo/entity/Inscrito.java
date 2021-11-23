package com.example.demo.entity;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import java.util.Date;

public class Inscrito {
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int id;
	
	@ManyToOne
	@JoinColumn(name="idAlumno")
	private Alumno idAlumno;
	
	@ManyToOne
	@JoinColumn(name="idOferta")
	private Oferta idOferta;
	
	@Column(name="fecha_inscripcion")
	@Temporal(TemporalType.DATE)
	private Date fecha_inscripcion;
	
}
