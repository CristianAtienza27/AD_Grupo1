package com.example.demo.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.example.demo.entity.Usuario;
import com.example.demo.models.UsuarioModel;
import com.example.demo.service.CicloService;
import com.example.demo.service.UsuarioService;

@Controller
public class LoginRegisterController {
	
	private static final String LOGIN_VIEW = "login";
	private static final String REGISTER_VIEW = "register";
	
	@Autowired
	private UsuarioService usuarioService;
	
	@Autowired
	private CicloService cicloService;
	
	@GetMapping("/")
	public String slash() {
		return "redirect:/auth/login";
	}
	
	@GetMapping("/auth/login")
	public String loginForm(@RequestParam(value="error",required=false) String error,@RequestParam(value="logout",required=false) String logout,
			@RequestParam(value="notEnable",required=false) String enable,Model model,RedirectAttributes redirAttrs) {

		if(error!=null) {
			redirAttrs.addFlashAttribute("fallo", "Credenciales incorrectas");
			return "redirect:/auth/login";
		}if(logout!=null){
			redirAttrs.addFlashAttribute("fallo", "Logout con éxito");
			return "redirect:/auth/login";
		}if(enable!=null){
			redirAttrs.addFlashAttribute("fallo", "Usuario no activado, espere a que el administrador le active");
			return "redirect:/auth/login";
		}
		else {
			model.addAttribute("alumno",new Usuario());
			model.addAttribute("ciclos", cicloService.listAllCiclos());
			return LOGIN_VIEW;
		}
		
	}
	
	@GetMapping("/auth/register")
	public ModelAndView registerForm() {
		ModelAndView mav = new ModelAndView(REGISTER_VIEW);
		mav.addObject("user", new Usuario());
		mav.addObject("ciclos", cicloService.listAllCiclos());
		return mav;
	}
	
	@PostMapping("/auth/register")
	public String register(@Valid @ModelAttribute Usuario alumno, BindingResult bindingResult, RedirectAttributes flash) {
		
		if(bindingResult.hasErrors()) {
			flash.addFlashAttribute("fallo",bindingResult.getAllErrors().get(0).getDefaultMessage());
			return "redirect:/auth/register";
		}
		
		alumno.setEnabled(false);
		alumno.setRole("ROLE_ALUMNO");
		usuarioService.register(usuarioService.transform(alumno));
		flash.addFlashAttribute("mensaje","Registrado con éxito, espere a ser activado por el administrador");
		return "redirect:/auth/login";
	}

}
