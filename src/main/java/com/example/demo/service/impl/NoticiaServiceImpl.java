package com.example.demo.service.impl;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import com.example.demo.entity.Noticia;
import com.example.demo.models.NoticiaModel;
import com.example.demo.service.NoticiaService;

@Service
public class NoticiaServiceImpl implements NoticiaService{

	@Override
	public Noticia transform(NoticiaModel noticiaModel) {
		ModelMapper modelMapper = new ModelMapper();
		return modelMapper.map(noticiaModel, Noticia.class);
	}

	@Override
	public NoticiaModel transform(Noticia noticia) {
		ModelMapper modelMapper = new ModelMapper();
		return modelMapper.map(noticia, NoticiaModel.class);
	}

}
