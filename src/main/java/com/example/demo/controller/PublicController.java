package com.example.demo.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.example.demo.entity.Usuario;
import com.example.demo.service.UsuarioService;

@Controller
public class PublicController {
	
	private static final String INDEX_VIEW = "index";
	
	@Autowired
	private UsuarioService usuarioService;

	@GetMapping("/public")
	public String slash(Authentication auth,HttpSession session, 
			@RequestParam(value="logout", required=false) String logout, RedirectAttributes redirect) {
		
		if(logout != null) {
			redirect.addFlashAttribute("mensaje", "Ha hecho logout correctamente");
			return "redirect:/auth/login";
		}
		
		if(auth == null) {
			return INDEX_VIEW;
		}else {
			String username = auth.getName();
			
			if(session.getAttribute("usuario")==null) {
				
				Usuario usuario = usuarioService.findUserByEmail(username);
				
				if(usuario.isEnabled()==false)
					return "redirect:auth/login?notEnable";
				if(usuario.getRole().equals("ROLE_ADMIN")) {
					return "redirect:/admin/alumnos"; 
				}
				else if(usuario.getRole().equals("ROLE_ALUMNO")) {
					return "redirect:/alumno/noticias";
				}
				else if(usuario.getRole().equals("ROLE_RRHH")) {
					return "redirect:/rrhh/ofertas";
				}
				else {
					usuario.setPassword(null);
					session.setAttribute("usuario", usuario);
				}
			}
			return INDEX_VIEW;
		}
	}
	
}
