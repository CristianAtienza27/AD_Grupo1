package com.example.demo.service;

import com.example.demo.entity.Oferta;
import com.example.demo.models.OfertaModel;

public interface OfertaService {
	Oferta transform(OfertaModel ofertaModel);
	OfertaModel transform(Oferta oferta);
}
