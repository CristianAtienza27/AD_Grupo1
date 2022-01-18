package com.example.demo.service;

import java.util.List;

import com.example.demo.entity.Inscrito;
import com.example.demo.entity.Usuario;
import com.example.demo.models.InscritoModel;

public interface InscritoService {
	Inscrito transform(InscritoModel inscritoModel);
	InscritoModel transform(Inscrito ciclo);
	Inscrito addInscrito(InscritoModel transform);
	List<Inscrito> listAllOfertasByAlumno(Usuario id);
}
