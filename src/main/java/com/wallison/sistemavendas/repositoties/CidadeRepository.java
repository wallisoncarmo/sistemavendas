package com.wallison.sistemavendas.repositoties;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.wallison.sistemavendas.domain.Cidade;

@Repository
public interface CidadeRepository extends JpaRepository<Cidade, Integer> {	

}
