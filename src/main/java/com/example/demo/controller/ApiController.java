package com.example.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
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
	
	@PostMapping("/cicle")
	public ResponseEntity<?> newCicle(@RequestBody CicloModel newCiclo) {
		cicloService.addCiclo(newCiclo);
		return ResponseEntity.status(HttpStatus.CREATED).body(newCiclo);
	}
	
	@PutMapping("/cicle/{id}")
	public ResponseEntity<?> editCicle(@RequestBody CicloModel actual, @PathVariable int id) {
		CicloModel ciclo = cicloService.transform(cicloService.findCicloById(id));
		if(ciclo!=null) {
			actual.setId(id);
			return ResponseEntity.ok(cicloService.updateCiclo(actual));
		}
		else {
			return ResponseEntity.notFound().build();
		}
	}
	
	@DeleteMapping("/cicle/{id}")
	public ResponseEntity<?> deleteCicle(@PathVariable int id) {
		cicloService.removeCiclo(id);
		return ResponseEntity.noContent().build();
		
	}
	
}
