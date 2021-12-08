package com.example.demo.repository;

import java.util.Collection;
import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.entity.Ciclo;
import com.example.demo.entity.Usuario;
import com.example.demo.models.UsuarioModel;

@Repository
public interface UsuarioRepository extends JpaRepository<Usuario,Long>{
	public UsuarioModel findByNombre(String username);
	public UsuarioModel findByEmail(String email);
	public List<Usuario> findByRole(String role);
	public Collection<UsuarioModel> findByCicloID(Ciclo ciclo); 
}
