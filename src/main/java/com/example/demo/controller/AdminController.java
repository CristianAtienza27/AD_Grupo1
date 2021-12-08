package com.example.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.demo.entity.Usuario;
import com.example.demo.models.UsuarioModel;
import com.example.demo.service.UsuarioService;

@Controller
@RequestMapping("/admin")
public class AdminController {
	
	@Autowired
//	@Qualifier("usuarioRepository")
	private UsuarioService usuarioService;
	
	@GetMapping("/users")
	public String showAll(Model model) {
		model.addAttribute("users",usuarioService.findByRole("ROLE_ALUMNO"));
		return "admin/usuarios";
	}
	
	@PostMapping("disableUser/{id}")
	public String disable(@PathVariable int id,Model model) {
		UsuarioModel us = usuarioService.findUserById(id);
		us.setEnabled(false);
		model.addAttribute("user",usuarioService.addUser(us));
		return "redirect:/admin/users";
	}
	
	@PostMapping("enableUser/{id}")
	public String enable(@PathVariable int id, Model model) {
		UsuarioModel us = usuarioService.findUserById(id);
		System.out.println(us);
		us.setEnabled(true);
		model.addAttribute("user",usuarioService.addUser(us));
		return "redirect:/admin/users";
	}

}
