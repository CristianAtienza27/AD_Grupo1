package com.example.demo.repository;

import java.util.Collection;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.entity.Oferta;
import com.example.demo.entity.Usuario;
import com.example.demo.models.UsuarioModel;


@Repository
public interface OfertaRepository extends JpaRepository<Oferta, Long>{
	public Oferta findByTitular(String titular);
	public Oferta findById(int id);
	Collection<UsuarioModel> findByRrhhid(Usuario transform);
}
