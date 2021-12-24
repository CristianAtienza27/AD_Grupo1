package com.example.demo.service;

import java.util.List;

import com.example.demo.entity.Ciclo;
import com.example.demo.models.CicloModel;
import com.example.demo.models.NoticiaModel;

public interface CicloService {
	Ciclo transform(CicloModel cicloModel);
	CicloModel transform(Ciclo ciclo);
	CicloModel findCicloById(int id);
	List<CicloModel> listAllCiclos();
	List<NoticiaModel> listAllNoticiasByCiclo(CicloModel ciclo);
	Ciclo addCiclo(CicloModel cicloModel);
	Ciclo updateCiclo(CicloModel cicloModel);
	int removeCiclo(int id);
}
