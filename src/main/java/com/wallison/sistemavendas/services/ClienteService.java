package com.wallison.sistemavendas.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;

import com.wallison.sistemavendas.domain.Cliente;
import com.wallison.sistemavendas.dto.ClienteDTO;
import com.wallison.sistemavendas.repositoties.ClienteRepository;
import com.wallison.sistemavendas.services.exceptions.DataIntegrityException;
import com.wallison.sistemavendas.services.exceptions.ObjectNotFoundException;

@Service
public class ClienteService {

	@Autowired
	private ClienteRepository repo;

	public Cliente findById(Integer id) {
		Cliente obj = repo.findOne(id);

		if (obj == null) {
			throw new ObjectNotFoundException("Objeto não encontrado ID: " + id + " Tipo: " + Cliente.class.getName());
		}

		return obj;
	}

	/**
	 * Cadastra um cliente
	 * 
	 * @param obj
	 * @return
	 */
	public Cliente insert(Cliente obj) {
		obj.setId(null);
		return repo.save(obj);
	}

	/**
	 * Atualiza um cliente
	 * 
	 * @param obj
	 * @return
	 */
	public Cliente update(Cliente obj) {
		Cliente newObj = findById(obj.getId());

		updateData(newObj, obj);

		return repo.save(newObj);
	}

	/**
	 * Exclui um cliente
	 * 
	 * @param id
	 */
	public void delete(Integer id) {
		findById(id);
		try {
			repo.delete(id);
		} catch (DataIntegrityViolationException e) {
			throw new DataIntegrityException("Não é possível excluir um cliente que possui pedidos");
		}

	}

	/**
	 * Retorna um lista com todas as clientes
	 * 
	 * @return
	 */
	public List<Cliente> findAll() {
		return repo.findAll();
	}

	/**
	 * Busca um cliente com parametros de paginação
	 * 
	 * @param page
	 * @param linesPerPage
	 * @param orderBy
	 * @param direction
	 * @return
	 */
	public Page<Cliente> findPage(Integer page, Integer linesPerPage, String orderBy, String direction) {
		PageRequest pageRequest = new PageRequest(page, linesPerPage, Direction.valueOf(direction), orderBy);
		return repo.findAll(pageRequest);

	}

	/**
	 * Converte clienteDTO para cliente
	 * 
	 * @param objDto
	 * @return
	 */
	public Cliente fromDTO(ClienteDTO objDto) {
		// throw new UnsupportedOperationException(); // para lembrar que o metodo ainda não foram feito

		return new Cliente(objDto.getId(), objDto.getNome(), objDto.getEmail(), null, null);
	}

	private void updateData(Cliente newObj, Cliente obj) {
		newObj.setNome(obj.getNome());
		newObj.setEmail(obj.getEmail());
	}

}
