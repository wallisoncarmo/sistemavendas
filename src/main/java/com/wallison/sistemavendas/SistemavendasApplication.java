package com.wallison.sistemavendas;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.wallison.sistemavendas.services.S3Service;

@SpringBootApplication
public class SistemavendasApplication implements CommandLineRunner {

	@Autowired
	private S3Service s3Service;
	
	public static void main(String[] args) {
		SpringApplication.run(SistemavendasApplication.class, args);
	}

	@Override
	public void run(String... arg0) throws Exception {
		s3Service.uploadFile("C:\\teste\\nuvem.jpg");		
	}
}
