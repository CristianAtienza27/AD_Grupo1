package com.example.demo.repository;

import java.util.Collection;
import java.util.Date;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.entity.Inscrito;
import com.example.demo.entity.Oferta;
import com.example.demo.entity.Usuario;

@Repository
public interface InscritoRepository extends JpaRepository<Inscrito, Long>{
	public Inscrito findById(int id);
	//public Inscrito findByFecha_inscripcion(Date date);
	public Collection<Inscrito> findByidAlumno(Long alumnoId);
	public Collection<Inscrito> findByidOferta(Long ofertaId);
}
