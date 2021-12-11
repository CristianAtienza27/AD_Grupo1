package com.example.demo.controller;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.example.demo.entity.Usuario;
import com.example.demo.models.UsuarioModel;
import com.example.demo.service.UsuarioService;

@Controller
@RequestMapping("/user")
public class UserController {
	
	private static final String FORM_VIEW = "user/datos";
	
	@Autowired
	private UsuarioService usuarioService;
	
	@GetMapping("/details/{id}")
	public String details(Authentication auth,HttpSession session, 
			@PathVariable(name="id", required=false) Integer id, Model model) {
		
		if(session.getAttribute("usuario")==null) {
			
			String username = auth.getName();
			Usuario usuario = usuarioService.findUserByEmail(username);
			session.setAttribute("usuario", usuario);
			
			model.addAttribute("user", usuarioService.findUserById(id));
			return FORM_VIEW;
		}
		
		return "redirect:/auth/login";

	}
	
	@PostMapping("/details/update")
	public String updateUser(@Valid @ModelAttribute("user") UsuarioModel usuarioModel,
	BindingResult bindingResult,Model model, RedirectAttributes flash) {
		
		if(bindingResult.hasErrors()) {
			model.addAttribute("user", usuarioModel);
			return "FORM_VIEW";
		}
		else {
			usuarioService.updateUser(usuarioModel);
			flash.addFlashAttribute("mensaje","Datos del usuario actualizados satisfactoriamente");
		}
		
		return "redirect:/user/details/" + usuarioModel.getId();
	}
	
}
