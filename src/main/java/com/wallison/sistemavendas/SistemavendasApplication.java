package com.wallison.sistemavendas;

import java.text.SimpleDateFormat;
import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.wallison.sistemavendas.domain.Categoria;
import com.wallison.sistemavendas.domain.Cidade;
import com.wallison.sistemavendas.domain.Cliente;
import com.wallison.sistemavendas.domain.Endereco;
import com.wallison.sistemavendas.domain.Estado;
import com.wallison.sistemavendas.domain.ItemPedido;
import com.wallison.sistemavendas.domain.Pagamento;
import com.wallison.sistemavendas.domain.PagamentoComBoleto;
import com.wallison.sistemavendas.domain.PagamentoComCartao;
import com.wallison.sistemavendas.domain.Pedido;
import com.wallison.sistemavendas.domain.Produto;
import com.wallison.sistemavendas.domain.enums.EstadoPagamento;
import com.wallison.sistemavendas.domain.enums.TipoCliente;
import com.wallison.sistemavendas.repositoties.CategoriaRepository;
import com.wallison.sistemavendas.repositoties.CidadeRepository;
import com.wallison.sistemavendas.repositoties.ClienteRepository;
import com.wallison.sistemavendas.repositoties.EnderecoRepository;
import com.wallison.sistemavendas.repositoties.EstadoRepository;
import com.wallison.sistemavendas.repositoties.ItemPedidoRepository;
import com.wallison.sistemavendas.repositoties.PagamentoRepository;
import com.wallison.sistemavendas.repositoties.PedidoRepository;
import com.wallison.sistemavendas.repositoties.ProdutoRepository;

@SpringBootApplication
public class SistemavendasApplication implements CommandLineRunner {

	@Autowired
	private CategoriaRepository categoriaRepository;
	@Autowired
	private ProdutoRepository produtoRepository;
	@Autowired
	private EstadoRepository estadoRepository;
	@Autowired
	private CidadeRepository cidadeRepository;
	@Autowired
	private ClienteRepository clienteRepository;
	@Autowired
	private EnderecoRepository enderecoRepository;
	@Autowired
	private PedidoRepository pedidoRepository;
	@Autowired
	private PagamentoRepository pagamentoRepository;
	@Autowired
	private ItemPedidoRepository itemPedidoRepository;

	public static void main(String[] args) {
		SpringApplication.run(SistemavendasApplication.class, args);
	}

	@Override
	public void run(String... arg0) throws Exception {

		Categoria cat1 = new Categoria(null, "Informática");
		Categoria cat2 = new Categoria(null, "Escritório");

		Produto p1 = new Produto(null, "Computador", 2000.00);
		Produto p2 = new Produto(null, "Impressora", 800.00);
		Produto p3 = new Produto(null, "Mouse", 80.00);

		cat1.getProdutos().addAll(Arrays.asList(p1, p2, p3));
		cat2.getProdutos().addAll(Arrays.asList(p2));

		p1.getCategorias().addAll(Arrays.asList(cat1));
		p2.getCategorias().addAll(Arrays.asList(cat1, cat2));
		p3.getCategorias().addAll(Arrays.asList(cat1));

		categoriaRepository.save(Arrays.asList(cat1, cat2));
		produtoRepository.save(Arrays.asList(p1, p2, p3));

		// Estado e cidade

		Estado est1 = new Estado(null, "Minas Gerais");
		Estado est2 = new Estado(null, "São Paulo");

		Cidade c1 = new Cidade(null, "UberLândia", est1);
		Cidade c2 = new Cidade(null, "São Paulo", est2);
		Cidade c3 = new Cidade(null, "Campinas", est2);

		est1.getCidades().addAll(Arrays.asList(c1));
		est1.getCidades().addAll(Arrays.asList(c2, c3));

		estadoRepository.save(Arrays.asList(est1, est2));
		cidadeRepository.save(Arrays.asList(c1, c2, c3));

		// Cliente e endereco

		Cliente cli1 = new Cliente(null, "Maria Silva", "maria@gmail.com", "04484386509", TipoCliente.PESSOAFISICA);
		cli1.getTelefones().addAll(Arrays.asList("984538665", "30393843"));

		Endereco e1 = new Endereco(null, "Rua Flores", "300", "Apt 303", "Jardim", "38546553", cli1, c1);
		Endereco e2 = new Endereco(null, "Avenida Matos", "105", "Sala 800", "Centro", "38577753", cli1, c2);

		cli1.getEnderecos().addAll(Arrays.asList(e1, e2));

		clienteRepository.save(Arrays.asList(cli1));
		enderecoRepository.save(Arrays.asList(e1, e2));

		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy hh:mm");
		Pedido ped1 = new Pedido(null, sdf.parse("30/09/2017 10:32"), cli1, e1);
		Pedido ped2 = new Pedido(null, sdf.parse("10/10/2017 19:32"), cli1, e2);

		Pagamento pagto1 = new PagamentoComCartao(null, EstadoPagamento.QUITADO, ped1, 6);
		ped1.setPagamento(pagto1);

		Pagamento pagto2 = new PagamentoComBoleto(null, EstadoPagamento.PENDENTE, ped2, sdf.parse("20/10/2017 00:00"),
				null);
		ped2.setPagamento(pagto2);

		cli1.getPedidos().addAll(Arrays.asList(ped1, ped2));

		pedidoRepository.save(Arrays.asList(ped1, ped2));
		pagamentoRepository.save(Arrays.asList(pagto1, pagto2));

		// Item de pedido
		ItemPedido ip1 = new ItemPedido(ped1, p1, 0.00, 1, 2000.00);
		ItemPedido ip2 = new ItemPedido(ped1, p3, 0.00, 2, 80.00);
		ItemPedido ip3 = new ItemPedido(ped2, p2, 100.00, 1, 800.00);

		ped1.getItens().addAll(Arrays.asList(ip1, ip2));
		ped2.getItens().addAll(Arrays.asList(ip3));

		p1.getItens().addAll(Arrays.asList(ip1));
		p1.getItens().addAll(Arrays.asList(ip3));
		p1.getItens().addAll(Arrays.asList(ip2));

		itemPedidoRepository.save(Arrays.asList(ip1, ip2, ip3));
	}
}
