package com.example.demo.service.impl;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.example.demo.entity.Usuario;
import com.example.demo.models.UsuarioModel;
import com.example.demo.repository.UsuarioRepository;
import com.example.demo.service.UsuarioService;

@Service
public class UsuarioServiceImpl implements UsuarioService{
	
	@Autowired
	private BCryptPasswordEncoder passwordEncoder;

	@Autowired
	private UsuarioRepository alumnoRepository;

	@Override
	public Usuario register(Usuario alumno) {
		alumno.setPassword(passwordEncoder.encode(alumno.getPassword()));
		return alumnoRepository.save(alumno);
	}

	@Override
	public Usuario findByNombre(String name) {
		return alumnoRepository.findByNombre(name);
	}

	@Override
	public Usuario transform(UsuarioModel alumnoModel) {
		ModelMapper modelMapper = new ModelMapper();
		return modelMapper.map(alumnoModel, Usuario.class);
	}

	@Override
	public UsuarioModel transform(Usuario alumno) {
		ModelMapper modelMapper = new ModelMapper();
		return modelMapper.map(alumno, UsuarioModel.class);
	}

}
