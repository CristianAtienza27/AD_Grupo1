package com.example.demo.service;

import java.util.List;

import com.example.demo.entity.Ciclo;
import com.example.demo.entity.Oferta;
import com.example.demo.models.CicloModel;
import com.example.demo.models.OfertaModel;
import com.example.demo.models.UsuarioModel;

public interface OfertaService {
	Oferta transform(OfertaModel ofertaModel);
	OfertaModel transform(Oferta oferta);
	Oferta addOferta(OfertaModel ofertaModel);
	Oferta updateOferta(OfertaModel ofertaModel);
	int removeOferta(int id);
	Oferta findById(int id);
}
