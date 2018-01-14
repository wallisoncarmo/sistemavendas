package com.wallison.sistemavendas.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.wallison.sistemavendas.domain.Pedido;
import com.wallison.sistemavendas.repositoties.PedidoRepository;
import com.wallison.sistemavendas.services.exceptions.ObjectNotFoundException;

@Service
public class PedidoService {

	@Autowired
	private PedidoRepository repo;

	public Pedido findById(Integer id) {
		Pedido obj = repo.findOne(id);

		if (obj == null) {
			throw new ObjectNotFoundException(
					"Objeto não encontrado ID: " + id + " Tipo: " + Pedido.class.getName());
		}

		return obj;
	}

}
