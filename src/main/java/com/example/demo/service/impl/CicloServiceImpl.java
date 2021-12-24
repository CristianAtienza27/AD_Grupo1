package com.example.demo.service.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.entity.Ciclo;
import com.example.demo.models.CicloModel;
import com.example.demo.models.NoticiaModel;
import com.example.demo.repository.CicloRepository;
import com.example.demo.repository.NoticiaRepository;
import com.example.demo.service.CicloService;

@Service
public class CicloServiceImpl implements CicloService{

	@Autowired
	private CicloRepository cicloRepository;
	
	@Autowired
	private NoticiaRepository noticiaRepository;
	
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
	public int removeCiclo(int id) {
		cicloRepository.deleteById(id);
		return 0;
	}

	@Override
	public List<CicloModel> listAllCiclos() {
		return cicloRepository.findAll().stream()
				.map(c -> transform(c)).collect(Collectors.toList());
	}

	@Override
	public CicloModel findCicloById(int id) {
		return transform(cicloRepository.findById(id).orElse(null));
	}

	@Override
	public List<NoticiaModel> listAllNoticiasByCiclo(CicloModel ciclo) {
		ModelMapper modelMapper = new ModelMapper();
		return noticiaRepository.findByCicloID(transform(ciclo)).stream()
				.map(n -> modelMapper.map(n, NoticiaModel.class))
				.collect(Collectors.toList());
	}
	
}
