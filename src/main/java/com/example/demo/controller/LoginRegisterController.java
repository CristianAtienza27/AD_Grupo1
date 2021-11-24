package com.example.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.example.demo.entity.Alumno;
import com.example.demo.service.AlumnoService;

@Controller
public class LoginRegisterController {
	
	@Autowired
	private AlumnoService alumnoService;
	
	@GetMapping("/")
	public String slash() {
		return "redirect:/auth/login";
	}
	
	@GetMapping("/auth/login")
	public String loginForm(Model model) {
		model.addAttribute("alumno",new Alumno());
		return "login";
	}
	
	@GetMapping("/auth/register")
	public String registerForm(Model model) {
		model.addAttribute("usuario",new Alumno());
		return "register";
	}
	
	@PostMapping("/auth/register")
	public String register(@ModelAttribute Alumno alumno, Model model) {
		alumno.setActivo(false);
		model.addAttribute("usuario",alumnoService.register(alumno));
		return "redirect:/auth/login";
	}

}
