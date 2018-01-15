package com.wallison.sistemavendas.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;

import com.wallison.sistemavendas.domain.Cidade;
import com.wallison.sistemavendas.domain.Cliente;
import com.wallison.sistemavendas.domain.Endereco;
import com.wallison.sistemavendas.domain.enums.TipoCliente;
import com.wallison.sistemavendas.dto.ClienteDTO;
import com.wallison.sistemavendas.dto.ClienteNewDTO;
import com.wallison.sistemavendas.repositoties.CidadeRepository;
import com.wallison.sistemavendas.repositoties.ClienteRepository;
import com.wallison.sistemavendas.repositoties.EnderecoRepository;
import com.wallison.sistemavendas.services.exceptions.DataIntegrityException;
import com.wallison.sistemavendas.services.exceptions.ObjectNotFoundException;

@Service
public class ClienteService {

	@Autowired
	private ClienteRepository repo;
	@Autowired
	private CidadeRepository cidadeRepo;
	@Autowired
	private EnderecoRepository enderecoRepo;

	public Cliente findById(Integer id) {
		Cliente obj = repo.findOne(id);

		if (obj == null) {
			throw new ObjectNotFoundException("Objeto não encontrado ID: " + id + " Tipo: " + Cliente.class.getName());
		}

		return obj;
	}


	public Cliente insert(Cliente obj) {
		obj.setId(null);
		
		obj = repo.save(obj);
		
		enderecoRepo.save(obj.getEnderecos());
		
		return repo.save(obj);
	}


	public Cliente update(Cliente obj) {
		Cliente newObj = findById(obj.getId());

		updateData(newObj, obj);

		return repo.save(newObj);
	}


	public void delete(Integer id) {
		findById(id);
		try {
			repo.delete(id);
		} catch (DataIntegrityViolationException e) {
			throw new DataIntegrityException("Não é possível excluir um cliente que possui pedidos");
		}

	}


	public List<Cliente> findAll() {
		return repo.findAll();
	}


	public Page<Cliente> findPage(Integer page, Integer linesPerPage, String orderBy, String direction) {
		PageRequest pageRequest = new PageRequest(page, linesPerPage, Direction.valueOf(direction), orderBy);
		return repo.findAll(pageRequest);

	}


	public Cliente fromDTO(ClienteDTO objDto) {
		// throw new UnsupportedOperationException(); // para lembrar que o metodo ainda
		// não foram feito

		return new Cliente(objDto.getId(), objDto.getNome(), objDto.getEmail(), null, null);
	}


	public Cliente fromDTO(ClienteNewDTO objDto) {

		Cliente cli = new Cliente(null, objDto.getNome(), objDto.getEmail(), objDto.getCpfOuCnpj(),
				TipoCliente.toEnum(objDto.getTipo()));

		Cidade cid = cidadeRepo.findOne(objDto.getCidadeId());

		Endereco end = new Endereco(null, objDto.getLogadouro(), objDto.getNumero(), objDto.getComplemento(),
				objDto.getBairro(), objDto.getCep(), cli, cid);

		cli.getEnderecos().add(end);
		cli.getTelefones().add(objDto.getTelefone1());
		
		if (objDto.getTelefone2() != null) {
			cli.getTelefones().add(objDto.getTelefone2());
		}

		if (objDto.getTelefone3() != null) {
			cli.getTelefones().add(objDto.getTelefone3());
		}

		return cli;
	}

	private void updateData(Cliente newObj, Cliente obj) {
		newObj.setNome(obj.getNome());
		newObj.setEmail(obj.getEmail());
	}

}
