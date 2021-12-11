package com.example.demo.service;

import java.util.List;

import com.example.demo.entity.Ciclo;
import com.example.demo.models.CicloModel;

public interface CicloService {
	Ciclo transform(CicloModel cicloModel);
	CicloModel transform(Ciclo ciclo);
	CicloModel findCicloById(int id);
	List<CicloModel> listAllCiclos();
	Ciclo addCiclo(CicloModel cicloModel);
	Ciclo updateCiclo(CicloModel cicloModel);
	int removeCiclo(int id);
}
