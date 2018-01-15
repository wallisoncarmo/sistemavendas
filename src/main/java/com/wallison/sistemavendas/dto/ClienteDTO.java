package com.wallison.sistemavendas.dto;

import java.io.Serializable;

import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotEmpty;

import com.wallison.sistemavendas.domain.Cliente;

public class ClienteDTO implements Serializable {

	private static final long serialVersionUID = 1L;

	private Integer id;
	
	@NotEmpty(message="Preenchimento Obrigatório!")
	@Length(min=5, max=120, message ="O tamanho precisa ser de 5 a 120 caracter!")
	private String nome;
	
	@NotEmpty(message="Preenchimento Obrigatório!")
	@Email(message="E-mail inválido!")
	private String email;

	public ClienteDTO() {
		// TODO Auto-generated constructor stub
	}

	public ClienteDTO(Cliente obj) {
		id = obj.getId();
		nome = obj.getNome();
		email = obj.getEmail();
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

}
