package com.example.demo.service.impl;

import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.entity.Oferta;
import com.example.demo.models.OfertaModel;
import com.example.demo.repository.OfertaRepository;
import com.example.demo.service.OfertaService;

@Service
public class OfertaServiceImpl implements OfertaService{
	
	@Autowired
	private OfertaRepository ofertaRepository;

	@Override
	public Oferta transform(OfertaModel ofertaModel) {
		ModelMapper modelMapper = new ModelMapper();
		return modelMapper.map(ofertaModel, Oferta.class);
	}

	@Override
	public OfertaModel transform(Oferta oferta) {
		ModelMapper modelMapper = new ModelMapper();
		return modelMapper.map(oferta, OfertaModel.class);
	}

	@Override
	public int removeOferta(int id) {
		ofertaRepository.deleteById(id);
		return 0;
	}

	@Override
	public Oferta addOferta(OfertaModel ofertaModel) {
		return ofertaRepository.save(transform(ofertaModel));
	}

	@Override
	public Oferta updateOferta(OfertaModel ofertaModel) {
		return ofertaRepository.save(transform(ofertaModel));
	}

	@Override
	public Oferta findById(int id) {
		return ofertaRepository.findById(id);
	}

	@Override
	public List<Oferta> showAll() {
		return ofertaRepository.findAll();
	}

}
