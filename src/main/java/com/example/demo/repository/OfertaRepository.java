package com.example.demo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.example.demo.entity.Ciclo;
import com.example.demo.entity.Oferta;
import com.example.demo.entity.Usuario;


@Repository
public interface OfertaRepository extends JpaRepository<Oferta, Integer>{
	public Oferta findByTitular(String titular);
//	@Query("SELECT DISTINCT O.id, O.descripcion, O.fechamax, O.numCandidatos, O.requisitos, O.titular, O.cicloid, O.rrhhid "
//			+ "FROM oferta O LEFT JOIN inscrito I ON O.id = I.id_oferta "
//			+ "WHERE I.id_alumno IS NULL OR I.id_alumno !=?1")
//	public List<Oferta> findOfertasNoInscritosByAlumno(int id);
	public Oferta findById(int id);
	public List<Oferta> findByCicloid(Ciclo ciclo);
	public List<Oferta> findByRrhhid(Usuario rrhh);
}
