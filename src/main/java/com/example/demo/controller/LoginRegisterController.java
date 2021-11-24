package com.example.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.example.demo.entity.Usuario;
import com.example.demo.service.UsuarioService;

@Controller
public class LoginRegisterController {
	
	@Autowired
	private UsuarioService alumnoService;
	
	@GetMapping("/")
	public String slash() {
		return "redirect:/auth/login";
	}
	
	@GetMapping("/auth/login")
	public String loginForm(@RequestParam(value="error",required=false) String error,Model model,RedirectAttributes redirAttrs) {
		if(error!=null) {
			redirAttrs.addFlashAttribute("fallo", "Username or password are incorrect");
			return "redirect:/auth/login";
		}else {
			model.addAttribute("alumno",new Usuario());
			return "login";
		}
	}
	
	@GetMapping("/auth/register")
	public String registerForm(Model model) {
		model.addAttribute("usuario",new Usuario());
		return "register";
	}
	
	@PostMapping("/auth/register")
	public String register(@ModelAttribute Usuario alumno, Model model) {
		alumno.setEnabled(false);
		model.addAttribute("usuario",alumnoService.register(alumno));
		return "redirect:/auth/login";
	}

}
