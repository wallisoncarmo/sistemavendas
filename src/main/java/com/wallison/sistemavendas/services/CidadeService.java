package com.wallison.sistemavendas.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.wallison.sistemavendas.domain.Cidade;
import com.wallison.sistemavendas.domain.Estado;
import com.wallison.sistemavendas.repositoties.CidadeRepository;
import com.wallison.sistemavendas.repositoties.EstadoRepository;

@Service
public class CidadeService {

	@Autowired
	private CidadeRepository repo;

	@Autowired
	private EstadoRepository estadoRepository;

	public List<Cidade> findCidade(Integer id) {		
		Estado obj = estadoRepository.findOne(id);
		return repo.findByEstado(obj);
	}

}
