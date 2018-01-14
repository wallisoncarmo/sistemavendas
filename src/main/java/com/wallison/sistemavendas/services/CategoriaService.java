package com.wallison.sistemavendas.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import com.wallison.sistemavendas.domain.Categoria;
import com.wallison.sistemavendas.repositoties.CategoriaRepository;
import com.wallison.sistemavendas.services.exceptions.DataIntegrityException;
import com.wallison.sistemavendas.services.exceptions.ObjectNotFoundException;

@Service
public class CategoriaService {

	@Autowired
	private CategoriaRepository repo;

	public Categoria findById(Integer id) {
		Categoria obj = repo.findOne(id);

		if (obj == null) {
			throw new ObjectNotFoundException(
					"Objeto não encontrado ID: " + id + " Tipo: " + Categoria.class.getName());
		}

		return obj;
	}

	public Categoria insert(Categoria obj) {
		obj.setId(null);
		return repo.save(obj);
	}

	public Categoria update(Categoria obj) {
		findById(obj.getId());
		return repo.save(obj);
	}

	public void delete(Integer id) {
		findById(id);
		try {
			repo.delete(id);
		} catch (DataIntegrityViolationException e) {
			throw new DataIntegrityException("Não é possível excluir uma categoria que possui produtos");
		}

	}

}
