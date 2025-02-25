package com.borges.projectPDV;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.borges.projectPDV.CategoriaRepositories.CategoriaRepository;
import com.borges.projectPDV.CategoriaRepositories.CidadeRepository;
import com.borges.projectPDV.CategoriaRepositories.ClienteRepository;
import com.borges.projectPDV.CategoriaRepositories.EnderecoRepository;
import com.borges.projectPDV.CategoriaRepositories.EstadoRepository;
import com.borges.projectPDV.CategoriaRepositories.ProdutoRepository;
import com.borges.projectPDV.domain.Categoria;
import com.borges.projectPDV.domain.Cidade;
import com.borges.projectPDV.domain.Cliente;
import com.borges.projectPDV.domain.Endereco;
import com.borges.projectPDV.domain.Estado;
import com.borges.projectPDV.domain.Produto;
import com.borges.projectPDV.domain.enums.TipoCliente;

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
	
	public static void main(String[] args) {
		SpringApplication.run(ProjectPdvApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		
		Categoria cat1 = new Categoria(null, "Informática");
		Categoria cat2 = new Categoria(null, "Escritório");
		
		Produto p1 = new Produto(null, "Computador", 2000.00 );
		Produto p2 = new Produto(null, "Impressora", 800.00 );
		Produto p3 = new Produto(null, "Mouse", 80.00 );
		
		cat1.getProdutos().addAll(Arrays.asList(p1,p2,p3));
		cat2.getProdutos().addAll(Arrays.asList(p2));
		
		p1.getCategorias().addAll(Arrays.asList(cat1));
		p2.getCategorias().addAll(Arrays.asList(cat1,cat2));
		p3.getCategorias().addAll(Arrays.asList(cat1));
		
		categoriaRepository.saveAll(Arrays.asList(cat1, cat2));
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
		
		
	}
	
	

}
