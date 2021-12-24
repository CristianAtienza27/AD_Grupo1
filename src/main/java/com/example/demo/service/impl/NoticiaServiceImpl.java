package com.example.demo.service.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.example.demo.entity.Ciclo;
import com.example.demo.entity.Noticia;
import com.example.demo.models.CicloModel;
import com.example.demo.models.NoticiaModel;
import com.example.demo.repository.NoticiaRepository;
import com.example.demo.service.NoticiaService;

@Service
public class NoticiaServiceImpl implements NoticiaService{
	
	@Autowired
	@Qualifier("noticiaRepository")
	private NoticiaRepository noticiaRepository;

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
	
	@Override
	public List<NoticiaModel> listAllNoticias() {
		return noticiaRepository.findAll().stream().
				map(c->transform(c)).collect(Collectors.toList());
	}
	
	@Override
	public Noticia addNoticia(NoticiaModel noticia) {
		return noticiaRepository.save(transform(noticia));
	}

	@Override
	public Noticia updateNoticia(NoticiaModel noticia) {
		return noticiaRepository.save(transform(noticia));
	}

	@Override
	public int removeNoticia(int id) {
		noticiaRepository.deleteById(id);
		return 0;
	}

}
