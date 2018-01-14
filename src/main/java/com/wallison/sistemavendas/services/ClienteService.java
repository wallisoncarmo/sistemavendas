package com.wallison.sistemavendas.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.wallison.sistemavendas.domain.Cliente;
import com.wallison.sistemavendas.repositoties.ClienteRepository;
import com.wallison.sistemavendas.services.exceptions.ObjectNotFoundException;

@Service
public class ClienteService {

	@Autowired
	private ClienteRepository repo;

	public Cliente buscar(Integer id) {
		Cliente obj = repo.findOne(id);

		if (obj == null) {
			throw new ObjectNotFoundException(
					"Objeto não encontrado ID: " + id + " Tipo: " + Cliente.class.getName());
		}

		return obj;
	}

}
