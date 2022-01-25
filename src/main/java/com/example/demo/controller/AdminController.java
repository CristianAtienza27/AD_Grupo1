package com.example.demo.controller;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
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
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.method.annotation.MvcUriComponentsBuilder;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.example.demo.entity.Ciclo;
import com.example.demo.entity.Noticia;
import com.example.demo.entity.Oferta;
import com.example.demo.entity.Usuario;
import com.example.demo.models.CicloModel;
import com.example.demo.models.UsuarioModel;
import com.example.demo.service.CicloService;
import com.example.demo.service.InscritoService;
import com.example.demo.service.NoticiaService;
import com.example.demo.service.OfertaService;
import com.example.demo.service.UsuarioService;
import com.example.demo.upload.FileController;
import com.example.demo.upload.StorageException;
import com.example.demo.upload.StorageService;

@Controller
@RequestMapping("/admin")
public class AdminController {
	
	private static final String USERS_VIEW = "admin/usuarios";
	private static final String NOTICIAS_VIEW = "admin/noticias";
	private static final String CICLOS_VIEW = "admin/ciclos";
	private static final String SOLICITUDES_VIEW = "admin/solicitudes";

	@Autowired
	private UsuarioService usuarioService;

	@Autowired
	private CicloService cicloService;
	
	@Autowired
	private NoticiaService noticiaService;
	
	@Autowired
	private StorageService storageService;
	
	@Autowired
	private OfertaService ofertaService;
	
	@Autowired
	private InscritoService inscritoService;
	
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
	
	// 					NOTICIAS					//
	
	@GetMapping("/noticias")
	public ModelAndView showNoticias(HttpSession session, Authentication auth, Model model) {
		String username = auth.getName();
		Usuario usuario = usuarioService.findUserByEmail(username);
		session.setAttribute("usuario", usuario);
		
		ModelAndView mav = new ModelAndView(NOTICIAS_VIEW);
		mav.addObject("titulo", "Noticias");
		mav.addObject("noticia", new Noticia());
		mav.addObject("ciclos", cicloService.listAllCiclos());
		mav.addObject("noticias", noticiaService.listAllNoticias());
				
		return mav;
	}
	
	@PostMapping("/noticia/{id}")
	public String addOrEditNoticia(@Valid @ModelAttribute("noticia") Noticia noticia, 
			BindingResult bindingResult,
			@PathVariable(name="id", required=false) Integer id,
			Model model, RedirectAttributes flash,@RequestParam("imagen") MultipartFile file) {
		
//		if(bindingResult.hasErrors()) {
//			
//			model.addAttribute("noticias", noticiaService.listAllNoticias());
//			flash.addFlashAttribute("fallo", bindingResult.getAllErrors().get(0).getDefaultMessage());
//			return "redirect:/admin/noticias";
//		}
		
		String image = null;
		String[] path = null;
		
		try {
			image = storageService.store(file, noticia.getId());
			path = MvcUriComponentsBuilder.fromMethodName(FileController.class, "serveFile", image).build().toUriString().split("/");
		}
		catch(StorageException exS) {
			System.out.println(exS.getMessage());
		}
		
		if(id == 0) {
			noticia.setImagen(path != null ? (path[path.length-1]) : null);
			Calendar fecha = new GregorianCalendar();
			
			String today=fecha.get(Calendar.YEAR)+"-"+fecha.get(Calendar.MONTH)+1+"-"+fecha.get(Calendar.DATE);
			Date date;
			try {
				date = new SimpleDateFormat("yyyy-MM-dd").parse(today);
				noticia.setFecha_creacion(date);
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			noticiaService.addNoticia(noticiaService.transform(noticia));
			flash.addFlashAttribute("mensaje", "Noticia añadida correctamente");
		}
		else {
			
			noticia.setImagen(path != null ? (path[path.length-1]) : null);
			
			if(noticia.getImagen() == null) {
				Noticia noticiaOld = noticiaService.transform(noticiaService.findNoticiaById(id));
				noticia.setImagen(noticiaOld.getImagen());
			}
			
			noticiaService.updateNoticia(noticiaService.transform(noticia));
			flash.addFlashAttribute("mensaje", "Noticia editada correctamente");
		}
		
		return "redirect:/admin/noticias";
	}
	
	@GetMapping("/noticia/delete/{id}")
	public String deleteNoticia(@PathVariable(name="id") Integer id, RedirectAttributes flash) {
		noticiaService.removeNoticia(id);
		flash.addFlashAttribute("mensaje","Noticia eliminada correctamente");
		return "redirect:/admin/noticias";
	}
	
	// 					OFERTAS					//
	
	@GetMapping({"/ofertas","/ofertas/{today}"})
	public String details(Authentication auth, HttpSession session,
			@PathVariable(name = "id", required = false) Integer id, Model model,
			@PathVariable(name="today",required=false) String fechaDeEntrada) {

		String username = auth.getName();
		Usuario usuario = usuarioService.findUserByEmail(username);
		session.setAttribute("usuario", usuario);
		
		List<Oferta> ofertasFinales = new ArrayList<Oferta>();
		
		Calendar fecha = new GregorianCalendar();
		
		String today=fecha.get(Calendar.YEAR)+"-"+fecha.get(Calendar.MONTH)+1+"-"+fecha.get(Calendar.DATE);
		System.out.println("Fecha de hoy " + today);
		
		if(fechaDeEntrada==null) {
			model.addAttribute("ofertas",ofertaService.showAll());
		}else {
			List<Oferta> ofertas = ofertaService.showAll();
		
			for (Oferta oferta : ofertas) {
				if(fecha.after(toCalendar(oferta.getFechamax()))){
					ofertasFinales.add(oferta);
				};
			}
			System.out.println("con Fecha de entrada");
			model.addAttribute("ofertas",ofertasFinales);
		}
		
		model.addAttribute("rrhh", usuarioService.showAll("ROLE_RRHH"));
		model.addAttribute("ciclos", cicloService.listAllCiclos());
		model.addAttribute("today",today);
		model.addAttribute("oferta",new Oferta());
		return "admin/ofertas";
		
	}
	
	@PostMapping({"/ofertas/{id}"})
	public String AddOrEditOferta(Authentication auth, HttpSession session,@Valid @ModelAttribute("oferta") Oferta oferta, 
			BindingResult bindingResult,
			@PathVariable(name="id", required=false) Integer id,
			Model model, RedirectAttributes flash,@RequestParam("fechamax") @DateTimeFormat(pattern = "yyyy-MM-dd") Date date) {
		
		System.out.println(date);
		
		String username = auth.getName();
		Usuario usuario = usuarioService.findUserByEmail(username);
		session.setAttribute("usuario", usuario);
		
		if(bindingResult.hasErrors()) {

			model.addAttribute("ofertas",usuario.getRrhh());
			System.out.println(bindingResult.getAllErrors().get(0).getDefaultMessage());
			flash.addFlashAttribute("fallo", bindingResult.getAllErrors().get(0).getDefaultMessage());
			return "redirect:/admin/ofertas";
		}

		oferta.setRrhhid(usuario);
		model.addAttribute("ofertas",usuario.getRrhh());
		
		if(id == 0) {
			ofertaService.addOferta(ofertaService.transform(oferta));
			flash.addFlashAttribute("mensaje", "Oferta añadida correctamente");
		}
		else {
			ofertaService.updateOferta(ofertaService.transform(oferta));
			flash.addFlashAttribute("mensaje", "Oferta editada correctamente");
		}
		
		return "redirect:/admin/ofertas";
	}
	
	@GetMapping({"/solicitudes","/solicitudes/ciclo{id}"})
	public ModelAndView showSolicitudes(@PathVariable(name = "id", required = false) Integer id,
			Authentication auth, HttpSession session, Model model) {
		
		String username = auth.getName();
		Usuario usuario = usuarioService.findUserByEmail(username);
		session.setAttribute("usuario", usuario);
		
		ModelAndView mav = new ModelAndView(SOLICITUDES_VIEW);
		
		mav.addObject("ciclos", cicloService.listAllCiclos());
		
		if(id != null) {
			CicloModel ciclo = cicloService.transform(cicloService.findCicloById(id));
			mav.addObject("filtro", ciclo.getNombre());
			mav.addObject("solicitudes", inscritoService.findSolicitudesByCiclo(id));
			
		}
		else {
			mav.addObject("solicitudes", inscritoService.findSolicitudes());
		}
		
		return mav;
	}
/	
	public static Calendar toCalendar(Date date){ 
		  Calendar cal = Calendar.getInstance();
		  cal.setTime(date);
		  return cal;
	}
	
}
