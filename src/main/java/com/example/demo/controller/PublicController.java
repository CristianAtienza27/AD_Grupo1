package com.example.demo.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import com.example.demo.entity.Usuario;
import com.example.demo.service.UsuarioService;

@Controller
public class PublicController {
	
	@Autowired
	private UsuarioService alumnoService;

	@GetMapping("/public")
	public String slash(Authentication auth,HttpSession session) {
		if(auth == null) {
			return "index";
		}else {
			String username = auth.getName();
			
			if(session.getAttribute("alumno")==null) {
				Usuario alumno = alumnoService.findByNombre(username);
				alumno.setPassword(null);
				session.setAttribute("alumno", alumno);
			}
			return "index";
		}
	}
	
}
