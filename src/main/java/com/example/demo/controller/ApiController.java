package com.example.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.entity.Ciclo;
import com.example.demo.models.CicloModel;
import com.example.demo.service.CicloService;

@RestController
@RequestMapping("/api")
public class ApiController {
	
	@Autowired
	private CicloService cicloService;
	
	@GetMapping("/getCicles")
	public List<CicloModel> listarCiclos(){
		return cicloService.listAllCiclos();
	}
	
	@GetMapping("/cicle/{id}")
	public Ciclo getCiclo(@PathVariable int id) {
		return cicloService.findCicloById(id);
	}

}
