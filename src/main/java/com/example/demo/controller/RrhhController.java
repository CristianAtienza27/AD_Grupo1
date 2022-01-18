package com.example.demo.controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.example.demo.entity.Inscrito;
import com.example.demo.entity.Oferta;
import com.example.demo.entity.Usuario;
import com.example.demo.service.CicloService;
import com.example.demo.service.InscritoService;
import com.example.demo.service.OfertaService;
import com.example.demo.service.UsuarioService;

@Controller
@RequestMapping("/rrhh")
public class RrhhController {

	
	@Autowired
	private UsuarioService usuarioService;
	
	@Autowired
	private OfertaService ofertaService;
	
	@Autowired
	private CicloService cicloService;
	
	@Autowired
	private InscritoService inscritoService;
	
	@GetMapping("/alumnos/{id}")
	public ModelAndView alumnos(@PathVariable int id,Authentication auth, HttpSession session) {
		
		Oferta oferta = ofertaService.findById(id);
		List<Inscrito> inscritos = inscritoService.findByidOferta(oferta);
		List<Usuario> usuarios = new ArrayList<>();
		
		for (Inscrito inscrito : inscritos) {
			usuarios.add(inscrito.getIdAlumno());
		}
		
		String username = auth.getName();
		Usuario usuario = usuarioService.findUserByEmail(username);
		session.setAttribute("usuario", usuario);
		
		
		ModelAndView mav = new ModelAndView("admin/usuarios");
		mav.addObject("titulo", "Alumnos");
		mav.addObject("users", usuarios);
		return mav;
	}
	
	@GetMapping("/ofertas")
	public String details(Authentication auth, HttpSession session,
			@PathVariable(name = "id", required = false) Integer id, Model model) {

		String username = auth.getName();
		Usuario usuario = usuarioService.findUserByEmail(username);
		session.setAttribute("usuario", usuario);
		
		model.addAttribute("ciclos", cicloService.listAllCiclos());
		model.addAttribute("oferta",new Oferta());
		model.addAttribute("ofertas",ofertaService.listAllOfertasByRrhh(usuarioService.transform(usuario)));
		model.addAttribute("rrhh", usuarioService.findByRole("ROLE_RRHH"));
        
		return "rrhh/ofertas";
	}
	
	@PostMapping({"/ofertas/{id}"})
	public String AddOrEditOferta(Authentication auth, HttpSession session,@Valid @ModelAttribute("oferta") Oferta oferta, 
			BindingResult bindingResult,
			@PathVariable(name="id", required=false) Integer id,
			Model model, RedirectAttributes flash,@RequestParam("fechamax") @DateTimeFormat(pattern = "yyyy-MM-dd") Date date) {
		
		System.out.println(date);
		
		if(bindingResult.hasErrors()) {
			
			String username = auth.getName();
			Usuario usuario = usuarioService.findUserByEmail(username);
			session.setAttribute("usuario", usuario);

			model.addAttribute("ofertas",usuario.getRrhh());
			System.out.println(bindingResult.getAllErrors().get(0).getDefaultMessage());
			flash.addFlashAttribute("fallo", bindingResult.getAllErrors().get(0).getDefaultMessage());
			return "redirect:/rrhh/ofertas";
		}
		
		String username = auth.getName();
		Usuario usuario = usuarioService.findUserByEmail(username);
		session.setAttribute("usuario", usuario);

		model.addAttribute("ofertas",usuario.getRrhh());
		
		if(id == 0) {
			oferta.setId(id);
			oferta.setRrhhid(usuario);
			ofertaService.addOferta(ofertaService.transform(oferta));
			flash.addFlashAttribute("mensaje", "Oferta añadida correctamente");
		}
		else {
			Oferta o = ofertaService.findById(oferta.getId());
			oferta.setRrhhid(o.getRrhhid());
			ofertaService.updateOferta(ofertaService.transform(oferta));
			flash.addFlashAttribute("mensaje", "Oferta editada correctamente");
		}
		
		return "redirect:/rrhh/ofertas";
	}
	
	@GetMapping("/ofertas/delete/{id}")
	public String deleteCiclo(Authentication auth, HttpSession session,@PathVariable(name="id") Integer id, RedirectAttributes flash) {
		
		String username = auth.getName();
		Usuario usuario = usuarioService.findUserByEmail(username);
		session.setAttribute("usuario", usuario);
		
		ofertaService.removeOferta(id);
		flash.addFlashAttribute("mensaje","Oferta eliminada correctamente");
		
		if(usuario.getRole().equals("ROLE_ADMIN"))
			return "redirect:/admin/ofertas";
		else
			return "redirect:/rrhh/ofertas";
			
	}
}
