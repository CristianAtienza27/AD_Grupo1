package com.example.demo.service;

import java.util.List;

import com.example.demo.entity.Usuario;
import com.example.demo.models.UsuarioModel;


public interface UsuarioService {
	Usuario register(UsuarioModel usuario);
	List<UsuarioModel> showAll(String role);
	Usuario transform(UsuarioModel usuarioModel);
	UsuarioModel transform(Usuario usuario);
	Usuario addUser(UsuarioModel usuario); 
	Usuario updateUser(UsuarioModel usuario);
	UsuarioModel findUserById(int id);
	Usuario findUserByEmail(String email);
	Usuario enabledUser(UsuarioModel usuario);
	Usuario disabledUser(UsuarioModel usuario);
	int removeUser(int id);
	List<Usuario> findByRole(String string);
}
