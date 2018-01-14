package com.wallison.sistemavendas.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.wallison.sistemavendas.domain.Categoria;
import com.wallison.sistemavendas.repositoties.CategoriaRepository;

@Service
public class CategoriaService {

	@Autowired
	private CategoriaRepository repo;

	public Categoria buscar(Integer id) {
		return repo.findOne(id);
	}

}
