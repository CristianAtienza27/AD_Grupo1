package com.example.demo.controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
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

	
	private static final String FORM_RRHH_VIEW = "user/datosRRHH";
	
	@Autowired
	private UsuarioService usuarioService;
	
	@Autowired
	private OfertaService ofertaService;
	
	@Autowired
	private CicloService cicloService;
	
	@Autowired
	private InscritoService inscritoService;
	
	@Autowired
	private BCryptPasswordEncoder passwordEncoder;
	
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
		
		String username = auth.getName();
		Usuario usuario = usuarioService.findUserByEmail(username);
		session.setAttribute("usuario", usuario);
		
		if(bindingResult.hasErrors()) {

			model.addAttribute("ofertas",usuario.getRrhh());
			System.out.println(bindingResult.getAllErrors().get(0).getDefaultMessage());
			flash.addFlashAttribute("fallo", bindingResult.getAllErrors().get(0).getDefaultMessage());
			return "redirect:/rrhh/ofertas";
		}

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
	
	@GetMapping("/ofertas/solicitudes")
	public String solicitudes(Authentication auth, HttpSession session,Model model) {
		String username = auth.getName();
		Usuario usuario = usuarioService.findUserByEmail(username);
		session.setAttribute("usuario", usuario);
		
		List<Oferta> ofertas = usuario.getRrhh();
		List<Inscrito> inscrito=new ArrayList<>();
		for (Oferta oferta : ofertas) {
			if(inscritoService.findByidOferta(oferta)!=null)
				inscrito.addAll(inscritoService.findByidOferta(oferta));
		}
		
		model.addAttribute("solicitudes",inscrito);
		
		return "rrhh/solicitudes";
	}
	
	@PostMapping("/ofertas/solicitudes/filtro")
	public String solicitudesFiltro(Authentication auth, HttpSession session,Model model,
			@RequestParam("fechaInicio") @DateTimeFormat(pattern = "yyyy-MM-dd") Date inicio,
			@RequestParam("fechaFin") @DateTimeFormat(pattern = "yyyy-MM-dd") Date fin) {
		
		String username = auth.getName();
		Usuario usuario = usuarioService.findUserByEmail(username);
		session.setAttribute("usuario", usuario);
		
		List<Oferta> ofertas = usuario.getRrhh();
		List<Inscrito> inscrito=new ArrayList<>();
		List<Inscrito> inscritoFinal=new ArrayList<>();
		
		for (Oferta oferta : ofertas) {
			if(inscritoService.findByidOferta(oferta)!=null)
				inscrito.addAll(inscritoService.findByidOferta(oferta));
		}
		
		for (Inscrito inscrito2 : inscrito) {
			if(inscrito2.getFecha_inscripcion().after(inicio) && inscrito2.getFecha_inscripcion().before(fin))
				inscritoFinal.add(inscrito2);
		}
		
		model.addAttribute("solicitudes",inscritoFinal);
		
		return "rrhh/solicitudes";
	}
	
	@GetMapping("/details")
	public String details(HttpSession session, Authentication auth,
			@PathVariable(name = "id", required = false) Integer id, Model model) {

		String username = auth.getName();
		Usuario usuario = usuarioService.findUserByEmail(username);
		session.setAttribute("usuario", usuario);		

		model.addAttribute("user", usuario);
		return FORM_RRHH_VIEW;
		
	}
	
	@PostMapping("/details/update")
	public String updateUser(@Valid @ModelAttribute("user") Usuario usuario, BindingResult bindingResult, Model model,
			RedirectAttributes flash) {

		if (bindingResult.hasErrors()) {
			model.addAttribute("user", usuario);
			flash.addFlashAttribute("fallo", bindingResult.getAllErrors().get(0).getDefaultMessage());
		} else {
			usuario.setEnabled(true);
			usuario.setPassword(passwordEncoder.encode(usuario.getPassword()));
			usuarioService.updateUser(usuarioService.transform(usuario));
			flash.addFlashAttribute("mensaje", "Datos del usuario actualizados satisfactoriamente");
		}

		return "redirect:/user/details/";
	}
}
