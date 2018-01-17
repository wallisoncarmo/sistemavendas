package com.wallison.sistemavendas.services;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.wallison.sistemavendas.domain.ItemPedido;
import com.wallison.sistemavendas.domain.PagamentoComBoleto;
import com.wallison.sistemavendas.domain.Pedido;
import com.wallison.sistemavendas.domain.enums.EstadoPagamento;
import com.wallison.sistemavendas.repositoties.ItemPedidoRepository;
import com.wallison.sistemavendas.repositoties.PagamentoRepository;
import com.wallison.sistemavendas.repositoties.PedidoRepository;
import com.wallison.sistemavendas.repositoties.ProdutoRepository;
import com.wallison.sistemavendas.services.exceptions.ObjectNotFoundException;

@Service
public class PedidoService {

	@Autowired
	private PedidoRepository repo;
	@Autowired
	private PagamentoRepository pagamentoRepository;
	
	@Autowired
	private ProdutoRepository produtoRepository;
	
	@Autowired
	private ItemPedidoRepository itemPedidoRepository;

	@Autowired
	private BoletoService boletoService;

	public Pedido findById(Integer id) {
		Pedido obj = repo.findOne(id);

		if (obj == null) {
			throw new ObjectNotFoundException("Objeto não encontrado ID: " + id + " Tipo: " + Pedido.class.getName());
		}

		return obj;
	}

	public Pedido insert(Pedido obj) {
		obj.setId(null);
		obj.setInstante(new Date());
		obj.getPagamento().setEstado(EstadoPagamento.PENDENTE);
		obj.getPagamento().setPedido(obj);

		if (obj.getPagamento() instanceof PagamentoComBoleto) {
			PagamentoComBoleto pagto = (PagamentoComBoleto) obj.getPagamento();
			boletoService.preencherPagamentoComBoleto(pagto, obj.getInstante());
		}

		obj = repo.save(obj);		
		pagamentoRepository.save(obj.getPagamento());
		
		for(ItemPedido ip: obj.getItens()) {
			ip.setDesconto(0.0);
			ip.setPreco(produtoRepository.findOne(ip.getProduto().getId()).getPreco());
			ip.setPedido(obj);
		}
		
		itemPedidoRepository.save(obj.getItens());
		return obj;
	}

}
