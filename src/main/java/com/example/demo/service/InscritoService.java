package com.example.demo.service;

import com.example.demo.entity.Inscrito;
import com.example.demo.models.InscritoModel;

public interface InscritoService {
	Inscrito transform(InscritoModel inscritoModel);
	InscritoModel transform(Inscrito ciclo);
}
