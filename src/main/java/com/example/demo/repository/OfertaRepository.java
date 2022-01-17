package com.example.demo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.entity.Ciclo;
import com.example.demo.entity.Oferta;
import com.example.demo.entity.Usuario;


@Repository
public interface OfertaRepository extends JpaRepository<Oferta, Integer>{
	public Oferta findByTitular(String titular);
	public Oferta findById(int id);
	public List<Oferta> findByCicloid(Ciclo ciclo);
	public List<Oferta> findByRrhhid(Usuario rrhh);
}
