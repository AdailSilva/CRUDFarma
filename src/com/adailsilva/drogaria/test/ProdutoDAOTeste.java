package com.adailsilva.drogaria.test;

import java.sql.SQLException;
import java.util.ArrayList;

import org.junit.Ignore;
import org.junit.Test;

import com.adailsilva.drogaria.dao.ProdutoDAO;
import com.adailsilva.drogaria.domain.FabricanteDTO;
import com.adailsilva.drogaria.domain.ProdutoDTO;

public class ProdutoDAOTeste {

	@Test
	@Ignore
	public void salvar() throws SQLException { // lança o erro para a camada seguinte

		ProdutoDTO produto_1 = new ProdutoDTO();
		// não precisa setar idproduto pois é autoincremento
		produto_1.setDescricao("Ebastel 10mg, caixa com 10 comprimidos");
		produto_1.setQuantidade(100L); // Valor inteiro por padrão é int mas aqui o tipo é Long
		produto_1.setCodigodebarras(1234567890);
		produto_1.setCusto(1.00D); // Valor real por padrão é Double
		produto_1.setPreco(100.00D); // Valor real por padrão é Double
		// Manipulando Datas (deixei como String no POJO)
		// conversão tempo tipo util (usado no pojo) para o tipo sql aceito no banco
		// produto_1.setFabricacao(new java.sql.Date(new java.util.Date().getTime()));
		// produto_1.setVencimento(new java.sql.Date(new java.util.Date().getTime()));
		// produto_1.setUltimoregistro(new java.sql.Date(new
		// java.util.Date().getTime()));
		// produto_1.setImagem(null);
		// produto_1.setAtivo(Boolean.TRUE);
		produto_1.setSimilar("Genérico");
		produto_1.setObservacao("Aqui será uma área maior de texto.");

		// composição
		// preparando o fabricante
		FabricanteDTO fabricante_1 = new FabricanteDTO();
		fabricante_1.setIdfabricante(1L); // Valor inteiro por padrão é int mas aqui o tipo é Long
		// setando o fabricante no produto
		produto_1.setFabricante(fabricante_1);

		// gravando no banco
		ProdutoDAO produtoDAO_1 = new ProdutoDAO();
		produtoDAO_1.salvar(produto_1);

		ProdutoDTO produto_2 = new ProdutoDTO();
		// não precisa setar idproduto pois é autoincremento
		produto_2.setDescricao("Cataflan 35mg, caixa com 25 comprimidos");
		produto_2.setQuantidade(500L); // Valor inteiro por padrão é int mas aqui o tipo é Long
		produto_2.setCodigodebarras(987654321);
		produto_2.setCusto(13.67D); // Valor real por padrão é Double
		produto_2.setPreco(75.43D); // Valor real por padrão é Double
		// Manipulando Datas (deixei como String no POJO)
		// conversão tempo tipo util (usado no pojo) para o tipo sql aceito no banco
		// produto_2.setFabricacao(new java.sql.Date(new java.util.Date().getTime()));
		// produto_2.setVencimento(new java.sql.Date(new java.util.Date().getTime()));
		// produto_2.setUltimoregistro(new java.sql.Date(new
		// java.util.Date().getTime()));
		// produto_2.setImagem(null);
		// produto_2.setAtivo(Boolean.FALSE);
		produto_2.setSimilar("outro Genérico");
		produto_2.setObservacao("Aqui será uma área maior de texto.");

		// composição
		// preparando o fabricante
		FabricanteDTO fabricante_2 = new FabricanteDTO();
		fabricante_2.setIdfabricante(2L); // Valor inteiro por padrão é int mas aqui o tipo é Long
		// setando o fabricante no produto
		produto_2.setFabricante(fabricante_2);

		// gravando no banco
		ProdutoDAO produtoDAO_2 = new ProdutoDAO();
		produtoDAO_2.salvar(produto_2);

		System.out.println("Produtos inclusos com sucesso!");

	}

	@Test
	@Ignore
	public void buscarPorCodigo() throws SQLException {

		// os objetos aqui estarão incompletos, apenas com os seus códigos.
		ProdutoDTO produto_1 = new ProdutoDTO();
		produto_1.setIdproduto(1L);

		ProdutoDTO produto_2 = new ProdutoDTO();
		produto_2.setIdproduto(2L);

		ProdutoDTO produto_3 = new ProdutoDTO();
		produto_3.setIdproduto(3L);

		// listando por código
		ProdutoDAO produto_dao = new ProdutoDAO();

		// necessário uma variável do mesmo tipo para pegar a resposta
		// os objetos aqui estarão completos, caso tenham sido encontrados.
		ProdutoDTO produto_result_1 = produto_dao.buscarPorCodigo(produto_1);
		ProdutoDTO produto_result_2 = produto_dao.buscarPorCodigo(produto_2);
		ProdutoDTO produto_result_3 = produto_dao.buscarPorCodigo(produto_3);

		// imprimindo os objetos para conferência
		// (implementar os métodos toString nos POJOS)
		System.out.println("Objeto 1: " + produto_result_1);
		System.out.println("Objeto 2: " + produto_result_2);
		System.out.println("Objeto 3: " + produto_result_3);
	}

	@Test
	@Ignore
	public void buscarPorDescricao() throws SQLException {
		ProdutoDTO produto = new ProdutoDTO();
		produto.setDescricao("E");

		ProdutoDAO produto_dao = new ProdutoDAO();

		ArrayList<ProdutoDTO> lista = produto_dao.buscarPorDescricao(produto);

		// laço para percorrer a lista
		// toString().replace() substituindo caracteres de ponto por vírgula
		for (ProdutoDTO item_da_lista : lista) {
			System.out.println("---------------------------------------------------------------------------");
			System.out.println("Código ID: " + item_da_lista.getIdproduto());
			System.out.println("Descrição: " + item_da_lista.getDescricao());
			System.out.println("Quantidade: " + item_da_lista.getQuantidade());
			System.out.println("Código de Barras: " + item_da_lista.getCodigodebarras());
			System.out.println("Custo: " + item_da_lista.getCusto().toString().replace(".", ","));
			System.out.println("Preço: " + item_da_lista.getPreco().toString().replace(".", ","));
			// System.out.println("Data de Fabricação: " + item_da_lista.getFabricacao());
			// System.out.println("Data de Vencimento: " + item_da_lista.getVencimento());
			// System.out.println("Último Registro: " + item_da_lista.getUltimoregistro());
			// System.out.println("Imagem: " + item_da_lista.getImagem());
			// System.out.println("Ativo: " + item_da_lista.getAtivo());
			System.out.println("Similar: " + item_da_lista.getSimilar());
			System.out.println("Observação: " + item_da_lista.getObservacao());
			System.out.println("Código ID do Fabricante: " + item_da_lista.getFabricante().getIdfabricante());
			System.out.println("Descrição do Fabricante: " + item_da_lista.getFabricante().getDescricao());
			System.out.println("---------------------------------------------------------------------------");
		}
	}

	@Test
	@Ignore
	public void listar() throws SQLException {

		// objeto criado para usar o método lista()
		ProdutoDAO produtoDAO = new ProdutoDAO();
		// guardando os resultados
		ArrayList<ProdutoDTO> lista = produtoDAO.listar();

		// laço para percorrer a lista
		// toString().replace() substituindo caracteres de ponto por vírgula
		for (ProdutoDTO produto : lista) {
			System.out.println("---------------------------------------------------------------------------");
			System.out.println("Código ID: " + produto.getIdproduto());
			System.out.println("Descrição: " + produto.getDescricao());
			System.out.println("Quantidade: " + produto.getQuantidade());
			System.out.println("Código de Barras: " + produto.getCodigodebarras());
			System.out.println("Custo: " + produto.getCusto().toString().replace(".", ","));
			System.out.println("Preço: " + produto.getPreco().toString().replace(".", ","));
			// System.out.println("Data de Fabricação: " + produto.getFabricacao());
			// System.out.println("Data de Vencimento: " + produto.getVencimento());
			// System.out.println("Último Registro: " + produto.getUltimoregistro());
			// System.out.println("Imagem: " + produto.getImagem());
			// System.out.println("Ativo: " + produto.getAtivo());
			System.out.println("Similar: " + produto.getSimilar());
			System.out.println("Observação: " + produto.getObservacao());
			System.out.println("Código ID do Fabricante: " + produto.getFabricante().getIdfabricante());
			System.out.println("Descrição do Fabricante: " + produto.getFabricante().getDescricao());
			System.out.println("---------------------------------------------------------------------------");

		}

	}

	@Test
	@Ignore
	public void editar() throws SQLException {

		ProdutoDTO produto_1 = new ProdutoDTO();
		produto_1.setIdproduto(1L); // L após para mudar o tipo padrão inteiro para Long
		produto_1.setDescricao("Edição de um Produto Qualquer");
		produto_1.setQuantidade(1000L); // L após para mudar o tipo padrão inteiro para Long
		produto_1.setCodigodebarras(123);
		produto_1.setCusto(23.00D);
		produto_1.setPreco(57.89D);
		// Manipulando Datas (deixei como String no POJO)
		// conversão tempo tipo util (usado no pojo) para o tipo sql aceito no banco
		// produto_1.setFabricacao(new java.sql.Date(new java.util.Date().getTime()));
		// produto_1.setVencimento(new java.sql.Date(new java.util.Date().getTime()));
		// produto_1.setUltimoregistro(new java.sql.Date(new
		// java.util.Date().getTime()));
		// produto_1.setImagem(null);
		// produto_1.setAtivo(Boolean.TRUE);
		produto_1.setSimilar("Qualquer Genérico");
		produto_1.setObservacao("Medicamento para Dor de Cabeça");

		FabricanteDTO fabricante_1 = new FabricanteDTO();
		fabricante_1.setIdfabricante(1L);

		// fabricante faz parte do produto
		produto_1.setFabricante(fabricante_1);

		// alterando
		ProdutoDAO produto_dao_1 = new ProdutoDAO();
		produto_dao_1.editar(produto_1);

		ProdutoDTO produto_2 = new ProdutoDTO();
		produto_2.setIdproduto(2L); // L após para mudar o tipo padrão inteiro para Long
		produto_2.setDescricao("Edição de um Produto Qualquer");
		produto_2.setQuantidade(89L); // L após para mudar o tipo padrão inteiro para Long
		produto_2.setCodigodebarras(555);
		produto_2.setCusto(33.20D);
		produto_2.setPreco(101.78D);
		// Manipulando Datas (deixei como String no POJO)
		// conversão tempo tipo util (usado no pojo) para o tipo sql aceito no banco
		// produto_2.setFabricacao(new java.sql.Date(new java.util.Date().getTime()));
		// produto_2.setVencimento(new java.sql.Date(new java.util.Date().getTime()));
		// produto_2.setUltimoregistro(new java.sql.Date(new
		// java.util.Date().getTime()));
		// produto_2.setImagem(null);
		// produto_2.setAtivo(Boolean.FALSE);
		produto_2.setSimilar("Qualquer outro Genérico");
		produto_2.setObservacao("Medicamento para Dor de Barriga");

		FabricanteDTO fabricante_2 = new FabricanteDTO();
		fabricante_2.setIdfabricante(2L);

		// fabricante faz parte do produto
		produto_2.setFabricante(fabricante_2);

		// alterando
		ProdutoDAO produto_dao_2 = new ProdutoDAO();
		produto_dao_2.editar(produto_2);

		System.out.println("Produtos alterados com sucesso!");
	}

	@Test
	@Ignore
	public void excluir() throws SQLException {

		ProdutoDTO produto = new ProdutoDTO();
		produto.setIdproduto(1L);

		ProdutoDAO produtoDAO = new ProdutoDAO();
		produtoDAO.excluir(produto);

		System.out.println("Produto removido com sucesso!");

	}

}