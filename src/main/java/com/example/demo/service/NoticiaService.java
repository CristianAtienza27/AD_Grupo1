package com.example.demo.service;

import java.util.List;

import com.example.demo.entity.Noticia;
import com.example.demo.models.CicloModel;
import com.example.demo.models.NoticiaModel;

public interface NoticiaService {
	Noticia transform(NoticiaModel noticiaModel);
	NoticiaModel transform(Noticia noticia);
	Noticia addNoticia(NoticiaModel noticia);
	Noticia updateNoticia(NoticiaModel noticia);
	int removeNoticia(int id);
	List<NoticiaModel> listAllNoticias();
}
