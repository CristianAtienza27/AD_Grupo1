package com.example.demo.repository;

import java.util.Collection;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.entity.Ciclo;
import com.example.demo.entity.Usuario;
import com.example.demo.models.UsuarioModel;

@Repository
public interface UsuarioRepository extends JpaRepository<Usuario,Long>{
	public Usuario findByNombre(String username);
	public Usuario findById(int id);
	public Usuario findByEmail(String email);
	public Usuario findByRole(String role);
	public Collection<UsuarioModel> findByCicloID(Ciclo ciclo); 
}
