package com.example.demo.repository;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.example.demo.entity.Inscrito;
import com.example.demo.entity.Oferta;
import com.example.demo.entity.Usuario;

@Repository
public interface InscritoRepository extends JpaRepository<Inscrito, Long>{
	public Inscrito findById(int id);
	//public Inscrito findByFecha_inscripcion(Date date);
	public List<Inscrito> findByidAlumnoOrderByFechainscripcionDesc(Usuario alumnoId);
	public List<Inscrito> findByidOferta(Oferta ofertaId);
	
	@Query(value="SELECT I.id, I.fecha_inscripcion, I.id_alumno, I.id_oferta "
			+ "FROM oferta O JOIN inscrito I ON O.id = I.id_oferta WHERE O.rrhhid = ?1 and I.fecha_inscripcion >= ?2 and I.fecha_inscripcion <= ?3", nativeQuery=true)
	public List<Inscrito> findInscripcionesByAlumnoByFecha(int id, Date inicio , Date fin);
}
