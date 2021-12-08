package com.example.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.example.demo.entity.Usuario;
import com.example.demo.models.UsuarioModel;
import com.example.demo.service.UsuarioService;

@Controller
public class LoginRegisterController {
	
	@Autowired
	private UsuarioService usuarioService;
	
	@GetMapping("/")
	public String slash() {
		return "redirect:/auth/login";
	}
	
	@GetMapping("/auth/login")
	public String loginForm(@RequestParam(value="error",required=false) String error,@RequestParam(value="logout",required=false) String logout,
			@RequestParam(value="notEnable",required=false) String enable,Model model,RedirectAttributes redirAttrs) {

		if(error!=null) {
			redirAttrs.addFlashAttribute("fallo", "Username or password are incorrect");
			return "redirect:/auth/login";
		}if(logout!=null){
			redirAttrs.addFlashAttribute("fallo", "Logout successfully");
			return "redirect:/auth/login";
		}if(enable!=null){
			redirAttrs.addFlashAttribute("fallo", "User not enable yet. Please wait");
			return "redirect:/auth/login";
		}
		else {
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
	public String register(@ModelAttribute UsuarioModel alumno, Model model) {
		alumno.setEnabled(false);
		alumno.setRole("ROLE_ALUMNO");
		model.addAttribute("usuario",usuarioService.register(alumno));
		return "redirect:/auth/login";
	}

}
