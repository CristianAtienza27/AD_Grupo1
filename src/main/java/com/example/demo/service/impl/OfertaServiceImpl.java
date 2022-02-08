package com.example.demo.service.impl;

import java.util.Comparator;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.entity.Oferta;
import com.example.demo.models.CicloModel;
import com.example.demo.models.OfertaModel;
import com.example.demo.models.UsuarioModel;
import com.example.demo.repository.OfertaRepository;
import com.example.demo.service.CicloService;
import com.example.demo.service.OfertaService;
import com.example.demo.service.UsuarioService;

@Service
public class OfertaServiceImpl implements OfertaService{
	
	@Autowired
	private OfertaRepository ofertaRepository;
	
	@Autowired
	private CicloService cicloService;
	
	@Autowired
	private UsuarioService usuarioService;

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

	@Override
	public int removeOferta(int id) {
		ofertaRepository.deleteById(id);
		return 0;
	}

	@Override
	public Oferta addOferta(OfertaModel ofertaModel) {
		return ofertaRepository.save(transform(ofertaModel));
	}

	@Override
	public Oferta updateOferta(OfertaModel ofertaModel) {
		return ofertaRepository.save(transform(ofertaModel));
	}

	@Override
	public Oferta findById(int id) {
		return ofertaRepository.findById(id);
	}

	@Override
	public List<Oferta> showAll() {
		return ofertaRepository.findAll();
	}

	@Override
	public List<Oferta> listAllOfertasByCiclo(CicloModel ciclo) {
		return ofertaRepository.findByCicloid(cicloService.transform(ciclo));
	}

	@Override
	public List<Oferta> listAllOfertasByRrhh(UsuarioModel rrhh) {
		return ofertaRepository.findByRrhhid(usuarioService.transform(rrhh));
	}

	@Override
	public List<Oferta> listAllOfertasNoInscritasByAlumno(int id) {
		return ofertaRepository.findOfertasNoInscritosByAlumno(id);
	}

	@Override
	public List<Oferta> listAllOfertasNoInscritasByAlumnoAndCiclo(int alumnoId, int cicloId) {
		return ofertaRepository.findOfertasNoInscritosByAlumnoAndByCiclo(alumnoId, cicloId);
	}
	
	@Override
	public List<Oferta> listAllActivasOfertasByCiclo(CicloModel ciclo) {
		return ofertaRepository.findByCicloid(cicloService.transform(ciclo)).stream()
				.filter(oferta -> oferta.getFechamax().after(new Date(System.currentTimeMillis())))
				.sorted(Comparator.comparing(Oferta::getNumCandidatos))
		        .map(c->(c)).collect(Collectors.toList());
	}

}
