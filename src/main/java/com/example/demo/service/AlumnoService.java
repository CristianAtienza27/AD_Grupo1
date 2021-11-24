package com.example.demo.service;

import com.example.demo.entity.Alumno;
import com.example.demo.models.AlumnoModel;


public interface AlumnoService {
	public Alumno findByNombre(String name);
	public Alumno register(Alumno alumno);
	Alumno transform(AlumnoModel alumnoModel);
	AlumnoModel transform(Alumno alumno);
}
