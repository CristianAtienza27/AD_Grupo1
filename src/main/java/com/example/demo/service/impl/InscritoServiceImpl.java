package com.example.demo.service.impl;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import com.example.demo.entity.Inscrito;
import com.example.demo.models.InscritoModel;
import com.example.demo.service.InscritoService;

@Service
public class InscritoServiceImpl implements InscritoService{

	@Override
	public Inscrito transform(InscritoModel inscritoModel) {
		ModelMapper modelMapper = new ModelMapper();
		return modelMapper.map(inscritoModel, Inscrito.class);
	}

	@Override
	public InscritoModel transform(Inscrito inscrito) {
		ModelMapper modelMapper = new ModelMapper();
		return modelMapper.map(inscrito, InscritoModel.class);
	}
	
}
