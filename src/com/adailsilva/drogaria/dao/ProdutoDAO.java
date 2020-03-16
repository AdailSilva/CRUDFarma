package com.adailsilva.drogaria.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.adailsilva.drogaria.domain.FabricanteDTO;
import com.adailsilva.drogaria.domain.ProdutoDTO;
import com.adailsilva.drogaria.factory.ConexaoFactory;

public class ProdutoDAO {

	// Passos:
	// 1 - Definir a SQL;
	// 2 - Conectar;
	// 3 - Substituir as interrogações;
	// 4 - Executar o comando;
	// 5 - Extrair o resultado da consulta quando pesquisas forem realizadas.

	// [C]RUD
	// método salvar() // throws propagando excessões para a view
	public void salvar(ProdutoDTO produto) throws SQLException { // assinatura do método

		StringBuilder sql = new StringBuilder();
		sql.append("INSERT INTO produto ");
		sql.append("(descricao,quantidade,codigodebarras,custo,preco, ");
		sql.append("fabricacao,vencimento,similar, ");
		sql.append("observacao,fabricante_idfabricante) ");
		sql.append("VALUES (?,?,?,?,?,?,?,?,?,?)");

		Connection conexao = ConexaoFactory.conectar();

		// converter StringBuilder para String
		PreparedStatement comando = conexao.prepareStatement(sql.toString());

		// mapeamento objeto relacional
		comando.setString(1, produto.getDescricao());
		comando.setLong(2, produto.getQuantidade());
		comando.setInt(3, produto.getCodigodebarras());
		comando.setDouble(4, produto.getCusto());
		comando.setDouble(5, produto.getPreco());

		// Manipulando datas (Tipo java.util.Date - POJO)
		comando.setString(6, produto.getFabricacao());
		comando.setString(7, produto.getVencimento());

		comando.setString(8, produto.getSimilar());
		comando.setString(9, produto.getObservacao());
		comando.setLong(10, produto.getFabricante().getIdfabricante()); // navegando dentro da composição

		comando.executeUpdate();

	}

	// C[R]UD -> 1 DE 3 (POR CÓDIGO)
	// método buscarPorCodigo() // throws propagando excessões para a view
	public ProdutoDTO buscarPorCodigo(ProdutoDTO produto) throws SQLException { // assinatura do método

		StringBuilder sql = new StringBuilder();
		sql.append("SELECT p.idproduto,p.descricao,p.quantidade,p.codigodebarras, ");
		sql.append("p.custo,p.preco,p.fabricacao,p.vencimento, ");
		sql.append("p.similar,p.observacao, ");
		sql.append("f.idfabricante,f.descricao ");
		sql.append("FROM produto p ");
		sql.append("INNER JOIN fabricante f ");
		// critério da pesquisa (condicional)
		sql.append("WHERE idproduto = ?");

		Connection conexao = ConexaoFactory.conectar();

		// converter StringBuilder para String
		PreparedStatement comando = conexao.prepareStatement(sql.toString());

		// mapeamento objeto relacional
		comando.setLong(1, produto.getIdproduto());

		// interface para criar uma variável que guarda as consultas SQL
		// no ResulSet os dados são perdidos quando a conexão com o banco é encerrada
		ResultSet resultado = comando.executeQuery(); // comando para consultas, retorna o resultado na variável.

		// converter um ResultSet em um Produto
		// Verificar se realmente o resultado foi encontrado
		ProdutoDTO produtoRetornado = null; // produto retornado, padrão nulo quando não encontrar nada.

		// se tiver "próximo"
		// apenas um resultado posso usar if, quando vários será usado while.
		if (resultado.next()) {

			produtoRetornado = new ProdutoDTO(); // criar um novo objeto

			// consumindo o resultado com os métodos get do ResultSet
			produtoRetornado.setIdproduto(resultado.getLong("p.idproduto"));
			produtoRetornado.setDescricao(resultado.getString("p.descricao"));
			produtoRetornado.setQuantidade(resultado.getLong("p.quantidade"));
			produtoRetornado.setCodigodebarras(resultado.getInt("p.codigodebarras"));
			produtoRetornado.setCusto(resultado.getDouble("p.custo"));
			produtoRetornado.setCusto(resultado.getDouble("p.preco"));

			// Manipulando datas (Tipo java.util.Date - POJO)
			produtoRetornado.setFabricacao(resultado.getString("p.fabricacao"));
			produtoRetornado.setVencimento(resultado.getString("p.vencimento"));

			produtoRetornado.setSimilar(resultado.getString("p.similar"));
			produtoRetornado.setObservacao(resultado.getString("p.observacao"));

			// COMPOSIÇÃO
			// montar um objeto (fabricante)
			FabricanteDTO fabricante = new FabricanteDTO();
			// consumindo o resultado com os métodos get do ResultSet
			fabricante.setIdfabricante(resultado.getLong("f.idfabricante"));
			fabricante.setDescricao(resultado.getString("f.descricao"));

			// fabricante faz parte do produto
			produtoRetornado.setFabricante(fabricante);

		}

		return produtoRetornado;

	}

	// C[R]UD -> 2 DE 3 (POR DESCRIÇÃO)
	// método buscarPorDescricao() // throws propagando excessões para a view
	// o retorno é mais de um resultado então é usado uma coleção
	// aqui um ArrayList. (Assinatura diferente, esse recebe um critério)
	public ArrayList<ProdutoDTO> buscarPorDescricao(ProdutoDTO produto) throws SQLException {

		StringBuilder sql = new StringBuilder();
		sql.append("SELECT p.idproduto,p.descricao,p.quantidade,p.codigodebarras, ");
		sql.append("p.custo,p.preco,p.fabricacao,p.vencimento, ");
		sql.append("p.similar,p.observacao, ");
		sql.append("f.idfabricante,f.descricao ");
		sql.append("FROM produto p ");
		sql.append("INNER JOIN fabricante f ");
		// igualdade de chaves
		sql.append("ON f.idfabricante = p.fabricante_idfabricante ");
		// critério da pesquisa (condicional)
		// usuário não sabe a descrição completa, não usar o operador de igualdade [%].
		sql.append("WHERE p.descricao LIKE ? ");

		// ordenando os resultados que podem ser um ou mais.
		sql.append("ORDER BY p.descricao ASC"); // deixa por ordem de descrição

		Connection conexao = ConexaoFactory.conectar();

		// converter StringBuilder para String
		PreparedStatement comando = conexao.prepareStatement(sql.toString());

		// mapeamento objeto relacional
		// operador "%" para o LIKE desconsiderar o que vem antes e depois do digitado.
		comando.setString(1, "%" + produto.getDescricao() + "%");

		// retorno terá que ser do tipo ArrayList de produtos

		// interface para criar uma variável que guarda as consultas SQL
		// no ResulSet os dados são perdidos quando a conexão com o banco é encerrada
		ResultSet resultado = comando.executeQuery(); // comando para consultas, retorna o resultado na variável.
		// <> o símbolo do diamante é uma trava
		// a partir do java 7 não é obrigado no construtor (na direita)
		ArrayList<ProdutoDTO> lista = new ArrayList<ProdutoDTO>();
		// verificar se há algo a ser consumido
		// se tiver próximo consumir, agora há possivelmente mais de um então usa-se
		// "enquanto".
		while (resultado.next()) {

			ProdutoDTO item_da_lista = new ProdutoDTO(); // criar um novo objeto

			// consumindo o resultado com os métodos get do ResultSet
			item_da_lista.setIdproduto(resultado.getLong("p.idproduto"));
			item_da_lista.setDescricao(resultado.getString("p.descricao"));
			item_da_lista.setQuantidade(resultado.getLong("p.quantidade"));
			item_da_lista.setCodigodebarras(resultado.getInt("p.codigodebarras"));
			item_da_lista.setCusto(resultado.getDouble("p.custo"));
			item_da_lista.setPreco(resultado.getDouble("p.preco"));

			// Manipulando datas (Tipo java.util.Date - POJO)
			item_da_lista.setFabricacao(resultado.getString("p.fabricacao"));
			item_da_lista.setVencimento(resultado.getString("p.vencimento"));

			item_da_lista.setSimilar(resultado.getString("p.similar"));
			item_da_lista.setObservacao(resultado.getString("p.observacao"));

			// COMPOSIÇÃO
			// montar um objeto (fabricante)
			FabricanteDTO fabricante = new FabricanteDTO();
			// consumindo o resultado com os métodos get do ResultSet
			fabricante.setIdfabricante(resultado.getLong("f.idfabricante"));
			fabricante.setDescricao(resultado.getString("f.descricao"));

			// fabricante faz parte do produto
			item_da_lista.setFabricante(fabricante);

			// adicionando os resultados obtidos na lista
			lista.add(item_da_lista);
		}

		return lista;

	}

	// C[R]UD -> 3 DE 3 (TODOS)
	// método listar() // throws propagando excessões para a view
	// o retorno é mais de um resultado então é usado uma coleção
	// aqui um ArrayList.
	public ArrayList<ProdutoDTO> listar() throws SQLException {

		StringBuilder sql = new StringBuilder();
		sql.append("SELECT p.idproduto,p.descricao,p.quantidade,p.codigodebarras, ");
		sql.append("p.custo,p.preco,p.fabricacao,p.vencimento, ");
		sql.append("p.similar,p.observacao, ");
		sql.append("f.idfabricante,f.descricao ");
		sql.append("FROM produto p ");
		sql.append("INNER JOIN fabricante f ");
		// igualdade de chaves
		sql.append("ON f.idfabricante = p.fabricante_idfabricante ");

		// NÃO PRECISO FILTRAR E ORDENAR AQUI - FAREI NO COMPONENTE DO PRIMEFACES
		// ordenando os resultados que podem ser um ou mais.
		// sql.append("ORDER BY p.descricao ASC"); // deixa por ordem de descrição

		Connection conexao = ConexaoFactory.conectar();

		PreparedStatement comando = conexao.prepareStatement(sql.toString()); // converter StringBuilder para String

		// mapeamento objeto relacional

		// retorno terá que ser do tipo ArrayList de produtos

		// interface para criar uma variável que guarda as consultas SQL
		// no ResulSet os dados são perdidos quando a conexão com o banco é encerrada
		ResultSet resultado = comando.executeQuery(); // comando para consultas, retorna o resultado na variável.

		// <> o símbolo do diamante é uma trava
		// a partir do java 7 não é obrigado no construtor (na direita)
		ArrayList<ProdutoDTO> lista = new ArrayList<ProdutoDTO>();

		// verificar se há algo a ser consumido
		// se tiver próximo consumir, possivelmente mais de um então usa-se "enquanto".
		while (resultado.next()) {

			// COMPOSIÇÃO
			// montar dois objetos (fabricante e produto)
			FabricanteDTO fabricante = new FabricanteDTO();
			// consumindo o resultado com os métodos get do ResultSet
			fabricante.setIdfabricante(resultado.getLong("f.idfabricante"));
			fabricante.setDescricao(resultado.getString("f.descricao"));

			ProdutoDTO produto = new ProdutoDTO();
			// consumindo o resultado com os métodos get do ResultSet
			produto.setIdproduto(resultado.getLong("p.idproduto"));
			produto.setDescricao(resultado.getString("p.descricao"));
			produto.setQuantidade(resultado.getLong("p.quantidade"));
			produto.setCodigodebarras(resultado.getInt("p.codigodebarras"));
			produto.setCusto(resultado.getDouble("p.custo"));
			produto.setPreco(resultado.getDouble("p.preco"));

			// Manipulando datas (Tipo java.util.Date - POJO)
			produto.setFabricacao(resultado.getString("p.fabricacao"));
			produto.setVencimento(resultado.getString("p.vencimento"));

			produto.setSimilar(resultado.getString("p.similar"));
			produto.setObservacao(resultado.getString("p.observacao"));

			// fabricante faz parte do produto
			produto.setFabricante(fabricante);

			// adicionando os resultados obtidos na lista
			lista.add(produto);
		}

		return lista;

	}

	// CR[U]D
	// método editar() // throws propagando excessões para a view
	public void editar(ProdutoDTO produto) throws SQLException { // assinatura do método

		StringBuilder sql = new StringBuilder();
		sql.append("UPDATE produto ");
		sql.append("SET descricao=?,quantidade=?,codigodebarras=?, ");
		sql.append("custo=?,preco=?,fabricacao=?,vencimento=?, ");
		sql.append("similar=?,observacao=?, ");
		sql.append("fabricante_idfabricante=? ");
		sql.append("WHERE idproduto = ?");

		Connection conexao = ConexaoFactory.conectar();

		PreparedStatement comando = conexao.prepareStatement(sql.toString()); // converter StringBuilder para String

		// mapeamento objeto relacional
		comando.setString(1, produto.getDescricao());
		comando.setLong(2, produto.getQuantidade());
		comando.setInt(3, produto.getCodigodebarras());
		comando.setDouble(4, produto.getCusto());
		comando.setDouble(5, produto.getPreco());

		// Manipulando datas (Tipo java.util.Date - POJO)
		comando.setString(6, produto.getFabricacao());
		comando.setString(7, produto.getVencimento());

		comando.setString(8, produto.getSimilar());
		comando.setString(9, produto.getObservacao());
		comando.setLong(10, produto.getFabricante().getIdfabricante()); // navegando dentro da composição
		comando.setLong(11, produto.getIdproduto());

		comando.executeUpdate();

	}

	// CRU[D]
	// método excluir() // throws propagando excessões para a view
	public void excluir(ProdutoDTO produto) throws SQLException { // assinatura do método

		StringBuilder sql = new StringBuilder();
		sql.append("DELETE FROM produto ");
		sql.append("WHERE idproduto = ?");

		Connection conexao = ConexaoFactory.conectar();

		// converter StringBuilder para String
		PreparedStatement comando = conexao.prepareStatement(sql.toString());

		// mapeamento objeto relacional
		comando.setLong(1, produto.getIdproduto());

		comando.executeUpdate();

	}

}