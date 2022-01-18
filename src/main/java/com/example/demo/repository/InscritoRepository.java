package com.example.demo.repository;

import java.util.Collection;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.entity.Inscrito;
import com.example.demo.entity.Usuario;

@Repository
public interface InscritoRepository extends JpaRepository<Inscrito, Long>{
	public Inscrito findById(int id);
	//public Inscrito findByFecha_inscripcion(Date date);
	public List<Inscrito> findByidAlumno(Usuario alumno);
	public Collection<Inscrito> findByidOferta(int ofertaId);
}
