package com.example.demo.service.impl;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.entity.Ciclo;
import com.example.demo.models.CicloModel;
import com.example.demo.repository.CicloRepository;
import com.example.demo.service.CicloService;

@Service
public class CicloServiceImpl implements CicloService{

	@Autowired
	private CicloRepository cicloRepository;
	
	@Override
	public Ciclo transform(CicloModel cicloModel) {
		ModelMapper modelMapper = new ModelMapper();
		return modelMapper.map(cicloModel, Ciclo.class);
	}

	@Override
	public CicloModel transform(Ciclo ciclo) {
		ModelMapper modelMapper = new ModelMapper();
		return modelMapper.map(ciclo, CicloModel.class);
	}

	@Override
	public Ciclo addCiclo(CicloModel cicloModel) {
		return cicloRepository.save(transform(cicloModel));
	}

	@Override
	public Ciclo updateCiclo(CicloModel cicloModel) {
		return cicloRepository.save(transform(cicloModel));
	}

	@Override
	public int removeCiclo(long id) {
		cicloRepository.deleteById(id);
		return 0;
	}
	
}
