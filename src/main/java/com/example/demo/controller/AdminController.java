package com.example.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.demo.entity.Usuario;
import com.example.demo.service.UsuarioService;

@Controller
@RequestMapping("/admin")
public class AdminController {
	
	@Autowired
	private UsuarioService usuarioService;
	
	@GetMapping("/users")
	public String showAll(Model model) {
		model.addAttribute("users",usuarioService.findByRole("ROLE_ALUMNO"));
		return "admin/usuarios";
	}
	
	@PostMapping("disableUser/{id}")
	public String disable(@PathVariable Long id) {
		Usuario us = usuarioService.findById(id);
		us.setEnabled(false);
		usuarioService.register(us);
		return "redirect:/admin/users";
	}
	
	@PostMapping("enableUser/{id}")
	public String enable(@PathVariable Long id) {
		Usuario us = usuarioService.findById(id);
		us.setEnabled(true);
		usuarioService.register(us);
		return "redirect:/admin/users";
	}

}
