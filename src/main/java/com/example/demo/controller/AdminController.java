package com.example.demo.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

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
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.example.demo.entity.Ciclo;
import com.example.demo.entity.Usuario;
import com.example.demo.models.CicloModel;
import com.example.demo.models.UsuarioModel;
import com.example.demo.service.CicloService;
import com.example.demo.service.UsuarioService;

@Controller
@RequestMapping("/admin")
public class AdminController {
	
	private static final String USERS_VIEW = "admin/usuarios";
	private static final String CICLOS_VIEW = "admin/ciclos";

	@Autowired
	private UsuarioService usuarioService;

	@Autowired
	private CicloService cicloService;

	@GetMapping("/alumnos")
	public ModelAndView showStudents(HttpSession session, Authentication auth, Model model) {
		String username = auth.getName();
		Usuario usuario = usuarioService.findUserByEmail(username);
		session.setAttribute("usuario", usuario);
		ModelAndView mav = new ModelAndView(USERS_VIEW);
		mav.addObject("titulo", "Alumnos");
		mav.addObject("users", usuarioService.showAll("ROLE_ALUMNO"));
		return mav;
	}

	@GetMapping("/RRHH")
	public ModelAndView showRRHH(HttpSession session, Authentication auth, Model model) {
		String username = auth.getName();
		Usuario usuario = usuarioService.findUserByEmail(username);
		session.setAttribute("usuario", usuario);
		ModelAndView mav = new ModelAndView(USERS_VIEW);
		mav.addObject("titulo", "RRHH");
		mav.addObject("users", usuarioService.showAll("ROLE_RRHH"));
		return mav;
	}

	@GetMapping("/ciclos")
	public ModelAndView showCiclos(HttpSession session, Authentication auth, Model model) {
		String username = auth.getName();
		Usuario usuario = usuarioService.findUserByEmail(username);
		session.setAttribute("usuario", usuario);
		ModelAndView mav = new ModelAndView(CICLOS_VIEW);
		mav.addObject("ciclo", new Ciclo());
		mav.addObject("ciclos", cicloService.listAllCiclos());
		return mav;
	}
	
	@PostMapping({"/ciclos/{id}"})
	public String AddOrEditCiclo(@PathVariable(name="id", required=false) Integer id, 
			@ModelAttribute("ciclo") CicloModel cicloModel,BindingResult bindingResult,
			Model model, RedirectAttributes flash) {
		
		if(bindingResult.hasErrors()) {
			model.addAttribute("ciclos", cicloService.listAllCiclos());
			return CICLOS_VIEW;
		}
		
		if(id == 0) {
			cicloService.addCiclo(cicloModel);
			flash.addFlashAttribute("mensaje", "Ciclo añadido correctamente");
		}
		else {
			cicloModel.setId(id);
			cicloService.updateCiclo(cicloModel);
			flash.addFlashAttribute("mensaje", "Ciclo editado correctamente");
		}
		
		return "redirect:/admin/ciclos";
	}
	
	@GetMapping("/ciclos/delete/{id}")
	public String deleteCiclo(@PathVariable(name="id") Integer id, RedirectAttributes flash) {
		cicloService.removeCiclo(id);
		flash.addFlashAttribute("mensaje","Ciclo eliminado correctamente");
		return "redirect:/admin/ciclos";
	}
	

	@PostMapping("disableUser/{id}")
	public String disable(@PathVariable int id, Model model) {
		UsuarioModel us = usuarioService.findUserById(id);
		us.setEnabled(false);
		model.addAttribute("user", usuarioService.addUser(us));
		return "redirect:/admin/users";
	}

	@PostMapping("enableUser/{id}")
	public String enable(@PathVariable int id, Model model) {
		UsuarioModel us = usuarioService.findUserById(id);
		System.out.println(us);
		us.setEnabled(true);
		model.addAttribute("user", usuarioService.addUser(us));
		return "redirect:/admin/users";
	}

}
