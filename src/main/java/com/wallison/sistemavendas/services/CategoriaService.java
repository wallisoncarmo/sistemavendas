package com.wallison.sistemavendas.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;

import com.wallison.sistemavendas.domain.Categoria;
import com.wallison.sistemavendas.dto.CategoriaDTO;
import com.wallison.sistemavendas.repositoties.CategoriaRepository;
import com.wallison.sistemavendas.services.exceptions.DataIntegrityException;
import com.wallison.sistemavendas.services.exceptions.ObjectNotFoundException;

@Service
public class CategoriaService {

	@Autowired
	private CategoriaRepository repo;

	/**
	 * Busca uma categoria pelo seu ID
	 * 
	 * @param id
	 * @return
	 */
	public Categoria findById(Integer id) {
		Categoria obj = repo.findOne(id);

		if (obj == null) {
			throw new ObjectNotFoundException(
					"Objeto não encontrado ID: " + id + " Tipo: " + Categoria.class.getName());
		}

		return obj;
	}

	/**
	 * Cadastra uma categoria
	 * 
	 * @param obj
	 * @return
	 */
	public Categoria insert(Categoria obj) {
		obj.setId(null);
		return repo.save(obj);
	}

	/**
	 * Atualiza uma categoria
	 * 
	 * @param obj
	 * @return
	 */
	public Categoria update(Categoria obj) {
		Categoria newObj = findById(obj.getId());

		updateData(newObj, obj);

		return repo.save(newObj);
	}

	/**
	 * Exclui uma categoria
	 * 
	 * @param id
	 */
	public void delete(Integer id) {
		findById(id);
		try {
			repo.delete(id);
		} catch (DataIntegrityViolationException e) {
			throw new DataIntegrityException("Não é possível excluir uma categoria que possui produtos");
		}

	}

	/**
	 * Retorna uma lista com todas as categorias
	 * 
	 * @return
	 */
	public List<Categoria> findAll() {
		return repo.findAll();
	}

	/**
	 * Busca uma categoria com parametros de paginação
	 * 
	 * @param page
	 * @param linesPerPage
	 * @param orderBy
	 * @param direction
	 * @return
	 */
	public Page<Categoria> findPage(Integer page, Integer linesPerPage, String orderBy, String direction) {
		PageRequest pageRequest = new PageRequest(page, linesPerPage, Direction.valueOf(direction), orderBy);
		return repo.findAll(pageRequest);

	}

	/**
	 * Converte categoriaDTO para categoria
	 * 
	 * @param objDto
	 * @return
	 */
	public Categoria fromDTO(CategoriaDTO objDto) {
		return new Categoria(objDto.getId(), objDto.getNome());
	}

	/**
	 * Adiciona os campos que serão atualizados
	 * 
	 * @param newObj
	 * @param obj
	 */
	private void updateData(Categoria newObj, Categoria obj) {
		newObj.setNome(obj.getNome());
	}

}
