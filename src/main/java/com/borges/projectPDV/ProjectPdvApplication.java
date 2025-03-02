package com.borges.projectPDV;

import java.text.SimpleDateFormat;
import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.borges.projectPDV.domain.Categoria;
import com.borges.projectPDV.domain.Cidade;
import com.borges.projectPDV.domain.Cliente;
import com.borges.projectPDV.domain.Endereco;
import com.borges.projectPDV.domain.Estado;
import com.borges.projectPDV.domain.ItemPedido;
import com.borges.projectPDV.domain.Pagamento;
import com.borges.projectPDV.domain.PagamentoComBoleto;
import com.borges.projectPDV.domain.PagamentoComCartao;
import com.borges.projectPDV.domain.Pedido;
import com.borges.projectPDV.domain.Produto;
import com.borges.projectPDV.domain.enums.EstadoPagamento;
import com.borges.projectPDV.domain.enums.TipoCliente;
import com.borges.projectPDV.repositories.CategoriaRepository;
import com.borges.projectPDV.repositories.CidadeRepository;
import com.borges.projectPDV.repositories.ClienteRepository;
import com.borges.projectPDV.repositories.EnderecoRepository;
import com.borges.projectPDV.repositories.EstadoRepository;
import com.borges.projectPDV.repositories.ItemPedidoRepository;
import com.borges.projectPDV.repositories.PagamentoRepository;
import com.borges.projectPDV.repositories.PedidoRepository;
import com.borges.projectPDV.repositories.ProdutoRepository;

@SpringBootApplication
public class ProjectPdvApplication implements CommandLineRunner{

	@Autowired
	private ProdutoRepository produtoRepository;
	@Autowired
	private CategoriaRepository categoriaRepository;	
	@Autowired
	private CidadeRepository cidadeRepository;	
	@Autowired
	private EstadoRepository estadoRepository;	
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
		SpringApplication.run(ProjectPdvApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		
		Categoria cat1 = new Categoria(null, "Informática");
		Categoria cat2 = new Categoria(null, "Escritório");
		Categoria cat3 = new Categoria(null, "Brinquedos");	
		Categoria cat4 = new Categoria(null, "Cama me e banho");
		Categoria cat5 = new Categoria(null, "Cozinha");
		Categoria cat6 = new Categoria(null, "Frios");
		Categoria cat7 = new Categoria(null, "Açougue");
		Categoria cat8 = new Categoria(null, "Padaria");
		Categoria cat9 = new Categoria(null, "Games");
		Categoria cat10 = new Categoria(null, "Celulared");
		Categoria cat11 = new Categoria(null, "Cosméticos");
		Categoria cat12 = new Categoria(null, "Ferramentas");
		Categoria cat13 = new Categoria(null, "Vestuários");
		Categoria cat14 = new Categoria(null, "Sacolão");
		Categoria cat15 = new Categoria(null, "Lanchonete");
		Categoria cat16 = new Categoria(null, "Papelaria");
			
		
		
		Produto p1 = new Produto(null, "Computador", 2000.00 );
		Produto p2 = new Produto(null, "Impressora", 800.00 );
		Produto p3 = new Produto(null, "Mouse", 80.00 );
		
		cat1.getProdutos().addAll(Arrays.asList(p1,p2,p3));
		cat2.getProdutos().addAll(Arrays.asList(p2));
		
		p1.getCategorias().addAll(Arrays.asList(cat1));
		p2.getCategorias().addAll(Arrays.asList(cat1,cat2));
		p3.getCategorias().addAll(Arrays.asList(cat1));
		
		categoriaRepository.saveAll(Arrays.asList(cat1, cat2, cat3, cat4, cat5, cat6, cat7, cat8, cat9, cat10, cat11, cat12,cat13, cat14, cat15, cat16));
		produtoRepository.saveAll(Arrays.asList(p1,p2,p3));
		
		Estado est1 = new Estado(null, "Minas Gerais");
		Estado est2 = new Estado(null, "São Paulo");
		
		Cidade c1 = new Cidade(null, "Betim", est1);
		Cidade c2 = new Cidade(null, "Campinas", est2);
		Cidade c3 = new Cidade(null, "São Paulo", est2);
		
		est1.getCidades().addAll(Arrays.asList(c1));
		est2.getCidades().addAll(Arrays.asList(c2,c3));
			
		
	
		estadoRepository.saveAll(Arrays.asList(est1, est2));
		cidadeRepository.saveAll(Arrays.asList(c1,c2,c3));
		
		Cliente cli1 = new Cliente(null, "Edson Borges", "ed_borg@hotmail.com", "046.793.296-06", TipoCliente.PESSOAFISICA);
		cli1.getTelefones().addAll(Arrays.asList("3591-8858","98886-7399"));
		Cliente cli2 =  new Cliente(null, "Creonice Oliveira", "creoli@gmail.com", "415.874.584.08", TipoCliente.PESSOAFISICA);
		cli2.getTelefones().addAll(Arrays.asList("3448-6265","98574-6854"));
		
		Endereco e1 = new Endereco(null, "Av Antônio Carlos", "599", "casa", "Jardim Teresópolis", "32681-405", cli1, c1);
		Endereco e2 = new Endereco(null, "Rua mensageiro", "26", "casa", "Jardim Teresópolis", "32681-405", cli1, c1);	
		Endereco e3 = new Endereco(null,"Rua das Palmeiras" ,"214", "casa", "Vila Atalntica", "38785-510", cli2, c2 );
		
		cli1.getEnderecos().addAll(Arrays.asList(e1,e2,e3));
		
		clienteRepository.saveAll(Arrays.asList(cli1,cli2));
		enderecoRepository.saveAll(Arrays.asList(e1,e2,e3));
		
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy hh:mm");
		
		Pedido ped1 = new Pedido(null, sdf.parse("30/09/2017 10:32"), cli1, e1);
		Pedido ped2 = new Pedido(null, sdf.parse("10/10/2017 19:35"), cli1, e2);
		
		Pagamento pagto1 = new PagamentoComCartao(null, EstadoPagamento.QUITADO	, ped1, 6);
		ped1.setPagamento(pagto1);
		
		Pagamento pagto2 = new PagamentoComBoleto(null, EstadoPagamento.PENDENTE, ped2, sdf.parse("20/10/2017 00:00"), null);
		ped2.setPagamento(pagto2);
		
		cli1.getPedidos().addAll(Arrays.asList(ped1,ped2));
		
		pedidoRepository.saveAll(Arrays.asList(ped1,ped2));
		pagamentoRepository.saveAll(Arrays.asList(pagto1, pagto2));
		
		ItemPedido ip1 = new ItemPedido(ped1, p1, 0.00, 1,2000.00);
		ItemPedido ip2 = new ItemPedido(ped1, p3, 0.00, 2, 80.00);
		ItemPedido ip3 = new ItemPedido(ped2, p2, 100.00, 1, 800.00);
		
		ped1.getItens().addAll(Arrays.asList(ip1,ip2));
		ped2.getItens().addAll(Arrays.asList(ip3));
		
		p1.getItens().addAll(Arrays.asList(ip1));
		p2.getItens().addAll(Arrays.asList(ip3));
		p3.getItens().addAll(Arrays.asList(ip2));
		
		itemPedidoRepository.saveAll(Arrays.asList(ip1,ip2,ip3));
		
		
		
		
	}
	
	

}
