package com.example.demo.controller;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
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
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.example.demo.entity.Inscrito;
import com.example.demo.entity.Oferta;
import com.example.demo.entity.Usuario;
import com.example.demo.models.CicloModel;
import com.example.demo.service.CicloService;
import com.example.demo.service.InscritoService;
import com.example.demo.service.NoticiaService;
import com.example.demo.service.OfertaService;
import com.example.demo.service.PdfService;
import com.example.demo.service.UsuarioService;

@Controller
@RequestMapping("/alumno")
public class AlumnoController{

	private static final String FORM_ALUMNO_VIEW = "user/datosAlumno";
	private static final String FORM_RRHH_VIEW = "user/datosRRHH";
	private static final String NOTICIAS_VIEW = "user/noticias";
	private static final String OFERTAS_VIEW = "user/ofertas";
	private static final String MY_OFERTAS_VIEW = "user/misOfertas";

	@Autowired
	private UsuarioService usuarioService;

	@Autowired
	private CicloService cicloService;
	
	@Autowired 
	private NoticiaService noticiaService;
	
	@Autowired 
	private OfertaService ofertaService;
	
	@Autowired 
	private InscritoService inscritoService;
	
	@Autowired
	private PdfService pdfService;
	
	@Autowired
	private BCryptPasswordEncoder passwordEncoder;

	@GetMapping("/details")
	public String details(HttpSession session, Authentication auth,
			@PathVariable(name = "id", required = false) Integer id, Model model) {
		
		String username = auth.getName();
		Usuario usuario = usuarioService.findUserByEmail(username);
		session.setAttribute("usuario", usuario);		

		model.addAttribute("user", usuario);
		model.addAttribute("ciclos", cicloService.listAllCiclos());
		return FORM_ALUMNO_VIEW;
		
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
	
	@GetMapping("/noticias")
	public String showNoticias(Authentication auth, HttpSession session, Model model) {
		
		String username = auth.getName();
		Usuario usuario = usuarioService.findUserByEmail(username);
		session.setAttribute("usuario", usuario);
		
		model.addAttribute("noticias", cicloService.listAllNoticiasByCiclo(cicloService.transform(usuario.getCicloID())));

		return NOTICIAS_VIEW;
	}
	
	@GetMapping("/MisOfertas")
	public String showMyOfertas(Authentication auth, HttpSession session, Model model) {
		
		String username = auth.getName();
		Usuario usuario = usuarioService.findUserByEmail(username);
		session.setAttribute("usuario", usuario);
		model.addAttribute("ofertas", inscritoService.findByidAlumno(usuario));
		return MY_OFERTAS_VIEW;
	}
	
	@GetMapping({"/ofertas","/ofertas/ciclo{id}"})
	public String showOfertas(@PathVariable(name="id",required=false) Integer id,
			Authentication auth, HttpSession session, Model model) {
		
		String username = auth.getName();
		Usuario usuario = usuarioService.findUserByEmail(username);
		session.setAttribute("usuario", usuario);
		
		List<Oferta> ofertas = null;
		CicloModel ciclo = null;
		
		if(id == null)
		{
			ofertas = ofertaService.listAllOfertasNoInscritasByAlumno(usuario.getId());
			model.addAttribute("filtro", "Mostrar todas");
		}
		else {
			ciclo = cicloService.transform(cicloService.findCicloById(id));
			ofertas = ofertaService.listAllOfertasNoInscritasByAlumnoAndCiclo(usuario.getId(), id);
			model.addAttribute("filtro", ciclo.getNombre());
		}
		
		model.addAttribute("ciclos", cicloService.listAllCiclos());
		model.addAttribute("ofertas", ofertas);

		return OFERTAS_VIEW;
	}
	
	
	@PostMapping("/oferta/{idOferta}/{idUsuario}")
	public String inscripcion(@PathVariable int idOferta,@PathVariable int idUsuario, 
			Authentication auth, HttpSession session, RedirectAttributes flash, Model model) {
		
		String username = auth.getName();
		Usuario usuario = usuarioService.findUserByEmail(username);
		session.setAttribute("usuario", usuario);
		
		Calendar cal = new GregorianCalendar();
		        
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		String today=cal.get(Calendar.YEAR)+"-"+cal.get(Calendar.MONTH)+1+"-"+cal.get(Calendar.DATE);
		Date convertedCurrentDate = null;
		
		try {
			convertedCurrentDate = sdf.parse(today);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		Inscrito inscrito = new Inscrito();
		inscrito.setFecha_inscripcion(convertedCurrentDate);
		inscrito.setIdAlumno(usuario);
		inscrito.setIdOferta(ofertaService.findById(idOferta));
		
		inscritoService.addInscrito(inscritoService.transform(inscrito));
		flash.addFlashAttribute("mensaje", "Inscripción realizada con éxito");
		
		return "redirect:/user/ofertas";
	}

}
