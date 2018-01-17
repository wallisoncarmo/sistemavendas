package com.wallison.sistemavendas.services;

import java.util.Calendar;
import java.util.Date;

import org.springframework.stereotype.Service;

import com.wallison.sistemavendas.domain.PagamentoComBoleto;

@Service
public class BoletoService {
	
	public void preencherPagamentoComBoleto(PagamentoComBoleto pagto, Date instanteDoPedido) {
		
		Calendar cal = Calendar.getInstance();
		cal.add(Calendar.DAY_OF_MONTH, 7);		
		pagto.setDataVencimento(cal.getTime());
		
	}

}
