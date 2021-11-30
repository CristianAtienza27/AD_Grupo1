package com.example.demo.service;

import com.example.demo.entity.Usuario;
import com.example.demo.models.UsuarioModel;


public interface UsuarioService {
	public Usuario findByNombre(String name);
	public Usuario findByEmail(String email);
	public Usuario register(Usuario alumno);
	public Usuario findByRole(String role);
	Usuario transform(UsuarioModel alumnoModel);
	UsuarioModel transform(Usuario alumno);
}
