package com.example.demo.service;

import com.example.demo.entity.Ciclo;
import com.example.demo.models.CicloModel;

public interface CicloService {
	Ciclo transform(CicloModel cicloModel);
	CicloModel transform(Ciclo ciclo);
}
