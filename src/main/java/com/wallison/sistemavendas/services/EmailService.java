package com.wallison.sistemavendas.services;

import org.springframework.mail.SimpleMailMessage;

import com.wallison.sistemavendas.domain.Cliente;
import com.wallison.sistemavendas.domain.Pedido;

public interface EmailService {

	void sendOrderConfirmationEmail(Pedido obj);
	
	void sendEmail(SimpleMailMessage msg);

	void sendNewPasswordEmail(Cliente cliente, String newPass);
}
