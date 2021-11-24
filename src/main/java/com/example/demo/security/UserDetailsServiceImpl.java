package com.example.demo.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.User.UserBuilder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.example.demo.entity.Alumno;
import com.example.demo.repository.AlumnoRepository;
@Service
public class UserDetailsServiceImpl implements UserDetailsService{

	@Autowired
	private AlumnoRepository usuarioRepository;
	
	@Override
	public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
		Alumno alumno = usuarioRepository.findByEmail(email);
		UserBuilder builder = null;
		
		if(alumno != null) {
			builder = User.withUsername(email);
			builder.disabled(false);
			builder.password(alumno.getPassword());
			builder.authorities(new SimpleGrantedAuthority("ROLE_USER"));
		}else {
			throw new UsernameNotFoundException("username not found");
		}
		return builder.build();
	}

}
