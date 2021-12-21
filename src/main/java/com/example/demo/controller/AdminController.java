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
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.example.demo.entity.Ciclo;
import com.example.demo.entity.Noticia;
import com.example.demo.entity.Usuario;
import com.example.demo.models.UsuarioModel;
import com.example.demo.service.CicloService;
import com.example.demo.service.NoticiaService;
import com.example.demo.service.UsuarioService;

@Controller
@RequestMapping("/admin")
public class AdminController {
	
	private static final String USERS_VIEW = "admin/usuarios";
	private static final String NOTICIAS_VIEW = "admin/noticias";
	private static final String CICLOS_VIEW = "admin/ciclos";

	@Autowired
	private UsuarioService usuarioService;

	@Autowired
	private CicloService cicloService;
	
	@Autowired
	private NoticiaService noticiaService;
	
	// 					USUARIOS					// 	

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
	
	@GetMapping("/noticias")
	public ModelAndView showNoticias(HttpSession session, Authentication auth, Model model) {
		String username = auth.getName();
		Usuario usuario = usuarioService.findUserByEmail(username);
		session.setAttribute("usuario", usuario);
		
		ModelAndView mav = new ModelAndView(NOTICIAS_VIEW);
		mav.addObject("titulo", "Noticias");
		mav.addObject("noticia", new Noticia());
		mav.addObject("noticias", noticiaService.showAll());
				
		return mav;
	}
	
	@GetMapping("/RRHH")
	public ModelAndView showRRHH(HttpSession session, Authentication auth, Model model) {
		String username = auth.getName();
		Usuario usuario = usuarioService.findUserByEmail(username);
		session.setAttribute("usuario", usuario);
		
		ModelAndView mav = new ModelAndView(USERS_VIEW);
		mav.addObject("titulo", "RRHH");
		mav.addObject("user", new Usuario());
		mav.addObject("users", usuarioService.showAll("ROLE_RRHH"));
		
		return mav;
	}
	
	@PostMapping("disableUser/{id}")
	public String disable(@PathVariable int id, Model model,RedirectAttributes flash) {
		UsuarioModel us = usuarioService.findUserById(id);
		model.addAttribute("user", usuarioService.disabledUser(us));
		flash.addFlashAttribute("mensaje", "Alumno desactivado correctamente");
		return "redirect:/admin/alumnos";
	}

	@PostMapping("enableUser/{id}")
	public String enable(@PathVariable int id, Model model,RedirectAttributes flash) {
		UsuarioModel us = usuarioService.findUserById(id);
		model.addAttribute("user", usuarioService.enabledUser(us));
		flash.addFlashAttribute("mensaje", "Alumno activado correctamente");
		return "redirect:/admin/alumnos";
	}
	
	@PostMapping({"/RRHH/{id}"})
	public String AddOrEditRRHH(@Valid @ModelAttribute("user") Usuario usuario, 
			BindingResult bindingResult,
			@PathVariable(name="id", required=false) Integer id,
			Model model, RedirectAttributes flash) {
		
		if(bindingResult.hasErrors()) {
			
			model.addAttribute("users", usuarioService.showAll("ROLE_RRHH"));
			flash.addFlashAttribute("fallo", bindingResult.getAllErrors().get(0).getDefaultMessage());
			return "redirect:/admin/RRHH";
		}
		
		if(id == 0) {
			usuarioService.addUser(usuarioService.transform(usuario));
			flash.addFlashAttribute("mensaje", "Personal de RRHH añadido correctamente");
		}
		else {
			usuario.setId(id);
			usuarioService.updateUser(usuarioService.transform(usuario));
			flash.addFlashAttribute("mensaje", "Personal de RRHH editado correctamente");
		}
		
		return "redirect:/admin/RRHH";
	}
	
	@GetMapping("alumnos/delete/{id}")
	public String deleteAlumno(@PathVariable(name="id") Integer id, RedirectAttributes flash) {
		usuarioService.removeUser(id);
		flash.addFlashAttribute("mensaje", "Alumno eliminado correctamente");
		return "redirect:/admin/alumnos";
	}
	
	@GetMapping("/RRHH/delete/{id}")
	public String deleteRRHH(@PathVariable(name="id") Integer id, RedirectAttributes flash) {
		usuarioService.removeUser(id);
		flash.addFlashAttribute("mensaje", "Personal de RRHH eliminado correctamente");
		return "redirect:/admin/RRHH";
	}
	
	// 					CICLOS					// 					

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
	public String AddOrEditCiclo(@Valid @ModelAttribute("ciclo") Ciclo ciclo, 
			BindingResult bindingResult,
			@PathVariable(name="id", required=false) Integer id,
			Model model, RedirectAttributes flash) {
		
		if(bindingResult.hasErrors()) {
			
			model.addAttribute("ciclos", cicloService.listAllCiclos());
			flash.addFlashAttribute("fallo", bindingResult.getAllErrors().get(0).getDefaultMessage());
			return "redirect:/admin/ciclos";
		}
		
		if(id == 0) {
			cicloService.addCiclo(cicloService.transform(ciclo));
			flash.addFlashAttribute("mensaje", "Ciclo añadido correctamente");
		}
		else {
			ciclo.setId(id);
			cicloService.updateCiclo(cicloService.transform(ciclo));
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

}
