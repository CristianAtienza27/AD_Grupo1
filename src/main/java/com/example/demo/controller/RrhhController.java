package com.example.demo.controller;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.example.demo.entity.Oferta;
import com.example.demo.entity.Usuario;
import com.example.demo.service.OfertaService;
import com.example.demo.service.UsuarioService;

@Controller
@RequestMapping("/rrhh")
public class RrhhController {

	
	@Autowired
	private UsuarioService usuarioService;
	
	@Autowired
	private OfertaService ofertaService;
	
	@GetMapping("/ofertas")
	public String details(Authentication auth, HttpSession session,
			@PathVariable(name = "id", required = false) Integer id, Model model) {

		String username = auth.getName();
		Usuario usuario = usuarioService.findUserByEmail(username);
		session.setAttribute("usuario", usuario);

		model.addAttribute("oferta",new Oferta());
		model.addAttribute("ofertas",usuario.getRrhh());
		
		//TODO
        
		return "rrhh/ofertas";
	}
	
	@PostMapping({"/ofertas/{id}"})
	public String AddOrEditOferta(Authentication auth, HttpSession session,@Valid @ModelAttribute("oferta") Oferta oferta, 
			BindingResult bindingResult,
			@PathVariable(name="id", required=false) Integer id,
			Model model, RedirectAttributes flash,@RequestParam("fechamax") String date) {
		
		if(bindingResult.hasErrors()) {
			
			String username = auth.getName();
			Usuario usuario = usuarioService.findUserByEmail(username);
			session.setAttribute("usuario", usuario);

			model.addAttribute("ofertas",usuario.getRrhh());
			flash.addFlashAttribute("fallo", bindingResult.getAllErrors().get(0).getDefaultMessage());
			return "redirect:/rrhh/ofertas";
		}
		
		String username = auth.getName();
		Usuario usuario = usuarioService.findUserByEmail(username);
		session.setAttribute("usuario", usuario);

		model.addAttribute("ofertas",usuario.getRrhh());
		
		
		Date date1=null;
		try {
			date1 = new SimpleDateFormat("yyyy-MM-dd").parse(date);
			oferta.setFechamax(date1);
			
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}  
		
		
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
