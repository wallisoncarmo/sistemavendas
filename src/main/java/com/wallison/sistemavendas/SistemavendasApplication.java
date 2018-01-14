package com.wallison.sistemavendas;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.wallison.sistemavendas.domain.Categoria;
import com.wallison.sistemavendas.repositoties.CategoriaRepository;

@SpringBootApplication
public class SistemavendasApplication implements CommandLineRunner {

	@Autowired
	private CategoriaRepository categoriaRepository;

	public static void main(String[] args) {
		SpringApplication.run(SistemavendasApplication.class, args);
	}

	@Override
	public void run(String... arg0) throws Exception {

		Categoria cat1 = new Categoria(null, "Informática");
		Categoria cat2 = new Categoria(null, "Escritório");

		categoriaRepository.save(Arrays.asList(cat1, cat2));

	}
}
