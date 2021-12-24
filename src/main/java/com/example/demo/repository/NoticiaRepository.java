package com.example.demo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.entity.Ciclo;
import com.example.demo.entity.Noticia;

@Repository
public interface NoticiaRepository extends JpaRepository<Noticia, Integer>{
	public Noticia findById(int id);
	public Noticia findByTitulo(String titulo);
	public List<Noticia> findByCicloID(Ciclo ciclo);
}
