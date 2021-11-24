package com.example.demo.service;

import com.example.demo.entity.Alumno;


public interface AlumnoService {
	public Alumno findByNombre(String name);
	public Alumno register(Alumno alumno);
}
