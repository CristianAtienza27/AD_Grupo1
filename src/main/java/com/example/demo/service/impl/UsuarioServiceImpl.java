package com.example.demo.service.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.example.demo.entity.Usuario;
import com.example.demo.models.UsuarioModel;
import com.example.demo.repository.UsuarioRepository;
import com.example.demo.service.UsuarioService;

@Service
public class UsuarioServiceImpl implements UsuarioService{
	
	@Autowired
	private BCryptPasswordEncoder passwordEncoder;

	@Autowired
	@Qualifier("usuarioRepository")
	private UsuarioRepository usuarioRepository;

	@Override
	public Usuario register(UsuarioModel alumno) {
		alumno.setPassword(passwordEncoder.encode(alumno.getPassword()));
		return usuarioRepository.save(transform(alumno));
	}

	@Override
	public Usuario transform(UsuarioModel alumnoModel) {
		ModelMapper modelMapper = new ModelMapper();
		return modelMapper.map(alumnoModel, Usuario.class);
	}

	@Override
	public UsuarioModel transform(Usuario alumno) {
		ModelMapper modelMapper = new ModelMapper();
		return modelMapper.map(alumno, UsuarioModel.class);
	}

	@Override
	public List<UsuarioModel> showAll(String role) {
		return usuarioRepository.findByRole(role).stream().
				map(c->transform(c)).collect(Collectors.toList());
	}

	@Override
	public Usuario addUser(UsuarioModel usuario) {
		return usuarioRepository.save(transform(usuario));
	}

	@Override
	public Usuario updateUser(UsuarioModel usuario) {
		return usuarioRepository.save(transform(usuario));
	}

	@Override
	public int removeUser(long id) {
		usuarioRepository.deleteById(id);
		return 0;
	}

	@Override
	public UsuarioModel findUserById(long id) {
		return transform(usuarioRepository.findById(id).orElse(null));
	}

	@Override
	public UsuarioModel findUserByEmail(String email) {
		return usuarioRepository.findByEmail(email);
	}

}
