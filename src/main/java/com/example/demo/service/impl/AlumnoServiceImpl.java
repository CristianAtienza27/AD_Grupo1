package com.example.demo.service.impl;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.example.demo.entity.Alumno;
import com.example.demo.models.AlumnoModel;
import com.example.demo.repository.AlumnoRepository;
import com.example.demo.service.AlumnoService;

@Service
public class AlumnoServiceImpl implements AlumnoService{
	
	@Autowired
	private BCryptPasswordEncoder passwordEncoder;

	@Autowired
	private AlumnoRepository alumnoRepository;

	@Override
	public Alumno register(Alumno alumno) {
		alumno.setPassword(passwordEncoder.encode(alumno.getPassword()));
		return alumnoRepository.save(alumno);
	}

	@Override
	public Alumno findByNombre(String name) {
		return alumnoRepository.findByNombre(name);
	}

	@Override
	public Alumno transform(AlumnoModel alumnoModel) {
		ModelMapper modelMapper = new ModelMapper();
		return modelMapper.map(alumnoModel, Alumno.class);
	}

	@Override
	public AlumnoModel transform(Alumno alumno) {
		ModelMapper modelMapper = new ModelMapper();
		return modelMapper.map(alumno, AlumnoModel.class);
	}

}
