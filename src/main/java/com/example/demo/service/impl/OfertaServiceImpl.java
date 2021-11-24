package com.example.demo.service.impl;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import com.example.demo.entity.Oferta;
import com.example.demo.models.OfertaModel;
import com.example.demo.service.OfertaService;

@Service
public class OfertaServiceImpl implements OfertaService{

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

}
