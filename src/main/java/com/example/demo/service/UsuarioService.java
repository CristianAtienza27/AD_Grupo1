package com.example.demo.service;

import java.util.List;

import com.example.demo.entity.Usuario;
import com.example.demo.models.UsuarioModel;


public interface UsuarioService {
	public Usuario findByNombre(String name);
	public Usuario findById(Long id);
	public Usuario findByEmail(String email);
	public Usuario register(Usuario alumno);
	Usuario transform(UsuarioModel alumnoModel);
	UsuarioModel transform(Usuario alumno);
}
