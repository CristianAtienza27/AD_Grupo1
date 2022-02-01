package com.example.demo.service.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.example.demo.entity.Inscrito;
import com.example.demo.entity.Oferta;
import com.example.demo.entity.Usuario;
import com.example.demo.models.InscritoModel;
import com.example.demo.repository.InscritoRepository;
import com.example.demo.service.InscritoService;

@Service
public class InscritoServiceImpl implements InscritoService{

	@Autowired
	@Qualifier("inscritoRepository")
	private InscritoRepository inscritoRepository;
	
	@Override
	public Inscrito transform(InscritoModel inscritoModel) {
		ModelMapper modelMapper = new ModelMapper();
		return modelMapper.map(inscritoModel, Inscrito.class);
	}

	@Override
	public InscritoModel transform(Inscrito inscrito) {
		ModelMapper modelMapper = new ModelMapper();
		return modelMapper.map(inscrito, InscritoModel.class);
	}
	
	@Override
	public Inscrito addInscrito(InscritoModel inscritoModel) {
		return inscritoRepository.save(transform(inscritoModel));
	}

	@Override
	public List<Inscrito> findByidAlumno(Usuario id) {
		return inscritoRepository.findByidAlumnoOrderByFechainscripcionDesc(id);
	}

	@Override
	public List<Inscrito> findByidOferta(Oferta id) {
		return inscritoRepository.findByidOferta(id);
	}

	@Override
	public List<Inscrito> findSolicitudesByCiclo(int id) {
		return inscritoRepository.findAll().stream()
        .filter(solicitud -> solicitud.getIdOferta().getCicloid().getId() == id)
        .map(c->(c)).collect(Collectors.toList());
	}

	@Override
	public List<Inscrito> findSolicitudes() {
		return inscritoRepository.findAll().stream()
				.map(i->(i)).collect(Collectors.toList());
	}
	
}
