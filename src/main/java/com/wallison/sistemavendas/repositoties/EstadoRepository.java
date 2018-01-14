package com.wallison.sistemavendas.repositoties;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.wallison.sistemavendas.domain.Estado;

@Repository
public interface EstadoRepository extends JpaRepository<Estado, Integer> {	

}
