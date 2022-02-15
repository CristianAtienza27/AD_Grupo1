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
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.entity.Ciclo;
import com.example.demo.entity.Inscrito;
import com.example.demo.entity.Usuario;
import com.example.demo.models.CicloModel;
import com.example.demo.models.InscritoModel;
import com.example.demo.service.CicloService;
import com.example.demo.service.InscritoService;
import com.example.demo.service.OfertaService;
import com.example.demo.service.UsuarioService;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;


@CrossOrigin(origins="http://localhost:8100",methods= {RequestMethod.GET,RequestMethod.POST,RequestMethod.PUT,RequestMethod.DELETE})
@RestController
@RequestMapping("/api")
public class ApiController {
	
	@Autowired
	private UsuarioService usuarioService;
	
	@Autowired
	private AuthenticationManager authenticationManager;
	
	@Autowired
	private CicloService cicloService;
	
	@Autowired
	private OfertaService ofertaService;
	
	@Autowired 
	private InscritoService inscritoService;
	
	private String email;
	
	private String token = "";
	
	
	@PostMapping("/login")
	public Usuario login(@RequestBody Usuario user) {
		
		System.out.println(user.getEmail() + ' ' + user.getPassword());
		
		Usuario usuario = usuarioService.findUserByEmail(user.getEmail());

		if (usuario != null) {
			if (usuario.getRole().equals("ROLE_ALUMNO")) {
				email = usuario.getEmail();
				Authentication authentication = authenticationManager
						.authenticate(new UsernamePasswordAuthenticationToken(user.getEmail(), user.getPassword()));
				SecurityContextHolder.getContext().setAuthentication(authentication);
				token = getJWTToken(user.getEmail());
				usuario.setToken(token);
				return usuario;
			} 
		}
		
		return null;
	}
	
	@GetMapping("/getOfertas")
	public ResponseEntity<?> listarOfertas(){
		List<Inscrito> inscripciones = null;
		
		if(!token.isEmpty())
			inscripciones = inscritoService.findByidAlumno(usuarioService.findUserByEmail(email));

		return ResponseEntity.status(HttpStatus.OK).body(inscripciones);
	}
	
	@GetMapping("/getCicles")
	public List<CicloModel> listarCiclos(){
		if(!token.isEmpty())
			return null;
		else
			return cicloService.listAllCiclos();
	}
	
	@GetMapping("/cicle/{id}")
	public Ciclo getCiclo(@RequestHeader String token,@PathVariable int id) {
		if(token.isEmpty())
			return null;
		else
			return cicloService.findCicloById(id);
	}
	
	@PostMapping("/cicle")
	public ResponseEntity<?> newCicle(@RequestHeader String token,@RequestBody CicloModel newCiclo) {
		if(token.isEmpty())
			return ResponseEntity.noContent().build();
		else {
			cicloService.addCiclo(newCiclo);
			return ResponseEntity.status(HttpStatus.CREATED).body(newCiclo);
		}
	}
	
	@PutMapping("/cicle/{id}")
	public ResponseEntity<?> editCicle(@RequestHeader String token,@RequestBody CicloModel actual, @PathVariable int id) {
		if(token.isEmpty()) {
			return ResponseEntity.notFound().build();
		}else {
			CicloModel ciclo = cicloService.transform(cicloService.findCicloById(id));
			if(ciclo!=null) {
				actual.setId(id);
				return ResponseEntity.ok(cicloService.updateCiclo(actual));
			}
			else {
				return ResponseEntity.notFound().build();
			}
		}
	}
	
	@DeleteMapping("/cicle/{id}")
	public ResponseEntity<?> deleteCicle(@RequestHeader String token,@PathVariable int id) {
		if(token.isEmpty()) {
			return ResponseEntity.notFound().build();
		}else {
		cicloService.removeCiclo(id);
		return ResponseEntity.noContent().build();
		}
		
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
