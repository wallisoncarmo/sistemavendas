package com.wallison.sistemavendas.repositoties;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.wallison.sistemavendas.domain.Cidade;
import com.wallison.sistemavendas.domain.Estado;

@Repository
public interface CidadeRepository extends JpaRepository<Cidade, Integer> {

	@Transactional(readOnly = true)
	public List<Cidade> findByEstado(Estado obj);	
}
