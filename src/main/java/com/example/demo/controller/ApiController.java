package com.example.demo.controller;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.entity.Ciclo;
import com.example.demo.entity.Usuario;
import com.example.demo.models.CicloModel;
import com.example.demo.service.CicloService;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

@RestController
@RequestMapping("/api")
public class ApiController {
	

	
	@Autowired
	private AuthenticationManager authenticationManager;
	
	@Autowired
	private CicloService cicloService;
	
	@PostMapping("/login/")
	public Usuario login(@RequestParam("user") String username,@RequestParam("password") String password) {
		Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username,password));
		SecurityContextHolder.getContext().setAuthentication(authentication);
		String token = getJWTToken(username);
		Usuario usuario = new Usuario();
		usuario.setEmail(username);
		usuario.setPassword(password);
		usuario.setToken(token);
		return usuario;
	}
	
	@GetMapping("/getCicles")
	public List<CicloModel> listarCiclos(@RequestHeader("Authentication") String token){
			return cicloService.listAllCiclos();
	}
	
	@GetMapping("/cicle/{id}")
	public Ciclo getCiclo(@PathVariable int id) {
		return cicloService.findCicloById(id);
	}
	
	@PostMapping("/cicle")
	public ResponseEntity<?> newCicle(@RequestBody CicloModel newCiclo) {
		cicloService.addCiclo(newCiclo);
		return ResponseEntity.status(HttpStatus.CREATED).body(newCiclo);
	}
	
	@PutMapping("/cicle/{id}")
	public ResponseEntity<?> editCicle(@RequestBody CicloModel actual, @PathVariable int id) {
		CicloModel ciclo = cicloService.transform(cicloService.findCicloById(id));
		if(ciclo!=null) {
			actual.setId(id);
			return ResponseEntity.ok(cicloService.updateCiclo(actual));
		}
		else {
			return ResponseEntity.notFound().build();
		}
	}
	
	@DeleteMapping("/cicle/{id}")
	public ResponseEntity<?> deleteCicle(@PathVariable int id) {
		cicloService.removeCiclo(id);
		return ResponseEntity.noContent().build();
		
	}
	
	private String getJWTToken(String username) {
		String secretKey="mySecretKey";
		List<GrantedAuthority> grantedAuthorities = AuthorityUtils.commaSeparatedStringToAuthorityList("ROLE_USER");
		
		String token = Jwts.builder()
						.setId("softtekJWT")
						.setSubject(username)
						.claim("authorities", 
								grantedAuthorities.stream()
										.map(GrantedAuthority::getAuthority)
										.collect(Collectors.toList()))
						.setIssuedAt(new Date(System.currentTimeMillis()))
						.setExpiration(new Date(System.currentTimeMillis() + 600000))
						.signWith(SignatureAlgorithm.HS512, secretKey.getBytes()).compact();
						
						return "Bearer " + token;
	
	}
	
}
