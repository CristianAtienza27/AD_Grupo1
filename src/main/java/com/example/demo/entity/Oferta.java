package com.example.demo.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.fasterxml.jackson.annotation.JsonSubTypes.Type;

public class Oferta {
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int id;
	
	@Column(name="titular", length=60)
	private String titular;
	
	@Column(name="descripcion", length=255)
	private String descripcion;
	
	@Column(name="requisitos")
	@Lob
	private String requisitos;
	
	@Column(name="fechamax")
	@Temporal(TemporalType.DATE)
	private Date fechamax;
	
	@ManyToOne
	@JoinColumn(name="rrhhid")
	private Rrhh rrhhid;
	
}
