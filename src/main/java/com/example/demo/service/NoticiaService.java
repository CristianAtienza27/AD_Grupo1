package com.example.demo.service;

import com.example.demo.entity.Noticia;
import com.example.demo.models.NoticiaModel;

public interface NoticiaService {
	Noticia transform(NoticiaModel noticiaModel);
	NoticiaModel transform(Noticia noticia);
}
