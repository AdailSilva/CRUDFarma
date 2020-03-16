package com.adailsilva.drogaria.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.adailsilva.drogaria.domain.FabricanteDTO;
import com.adailsilva.drogaria.factory.ConexaoFactory;

public class FabricanteDAO {

	// Passos:
	// 1 - Definir a SQL;
	// 2 - Conectar;
	// 3 - Substituir as interrogações;
	// 4 - Executar o comando;
	// 5 - Extrair o resultado da consulta quando pesquisas forem realizadas.

	// [C]RUD
	// método salvar() // throws propagando excessões para a view
	public void salvar(FabricanteDTO fabricante) throws SQLException { // assinatura do método

		StringBuilder sql = new StringBuilder();
		sql.append("INSERT INTO fabricante ");
		sql.append("(descricao) ");
		sql.append("VALUES (?)");

		Connection conexao = ConexaoFactory.conectar();

		PreparedStatement comando = conexao.prepareStatement(sql.toString()); // converter StringBuilder para String

		// mapeamento objeto relacional
		comando.setString(1, fabricante.getDescricao());

		comando.executeUpdate();

	}

	// C[R]UD -> 1 DE 3 (POR CÓDIGO)
	// método buscarPorCodigo() // throws propagando excessões para a view
	public FabricanteDTO buscarPorCodigo(FabricanteDTO fabricante) throws SQLException { // assinatura do método

		StringBuilder sql = new StringBuilder();
		sql.append("SELECT idfabricante,descricao ");
		sql.append("FROM fabricante ");
		// critério da pesquisa (condicional)
		sql.append("WHERE idfabricante = ?");

		Connection conexao = ConexaoFactory.conectar();

		PreparedStatement comando = conexao.prepareStatement(sql.toString()); // converter StringBuilder para String

		// mapeamento objeto relacional
		comando.setLong(1, fabricante.getIdfabricante());

		// interface para criar uma variável que guarda as consultas SQL
		// no ResulSet os dados são perdidos quando a conexão com o banco é encerrada
		ResultSet resultado = comando.executeQuery(); // comando para consultas, retorna o resultado na variável.

		// converter um ResultSet em um Fabricante
		// Verificar se realmente o resultado foi encontrado
		FabricanteDTO retorno = null; // fabricante retornado, padrão nulo quando não encontrar nada.

		// se tiver "próximo"
		// apenas um resultado posso usar if, quando vários será usado while.
		if (resultado.next()) {

			retorno = new FabricanteDTO(); // criar um novo objeto
			// consumindo o resultado com os métodos get do ResultSet
			retorno.setIdfabricante(resultado.getLong("idfabricante"));
			retorno.setDescricao(resultado.getString("descricao"));

		}

		return retorno;

	}

	// C[R]UD -> 2 DE 3 (POR DESCRIÇÃO)
	// método buscarPorDescricao() // throws propagando excessões para a view
	// o retorno é mais de um resultado então é usado uma coleção
	// aqui um ArrayList. (Assinatura diferente, esse recebe um critério)
	public ArrayList<FabricanteDTO> buscarPorDescricao(FabricanteDTO fabricante) throws SQLException {

		StringBuilder sql = new StringBuilder();
		sql.append("SELECT idfabricante,descricao ");
		sql.append("FROM fabricante ");
		// critério da pesquisa (condicional)
		// usuário não sabe a descrição completa, não usar o operador de igualdade [%].
		sql.append("WHERE descricao LIKE ? ");
		// ordenando os resultados que podem ser um ou mais.
		sql.append("ORDER BY descricao ASC"); // deixa por ordem de descrição

		Connection conexao = ConexaoFactory.conectar();

		PreparedStatement comando = conexao.prepareStatement(sql.toString()); // converter StringBuilder para String

		// mapeamento objeto relacional
		// operador "%" para o LIKE desconsiderar o que vem antes e depois do digitado.
		comando.setString(1, "%" + fabricante.getDescricao() + "%");

		// retorno terá que ser do tipo ArrayList de fabricantes

		// interface para criar uma variável que guarda as consultas SQL
		// no ResulSet os dados são perdidos quando a conexão com o banco é encerrada
		ResultSet resultado = comando.executeQuery(); // comando para consultas, retorna o resultado na variável.
		// <> o símbolo do diamante é uma trava
		// a partir do java 7 não é obrigado no construtor (na direita)
		ArrayList<FabricanteDTO> lista = new ArrayList<FabricanteDTO>();
		// verificar se há algo a ser consumido
		// se tiver próximo consumir, agora há possivelmente mais de um então usa-se
		// "enquanto".
		while (resultado.next()) {

			FabricanteDTO item_da_lista = new FabricanteDTO();
			// consumindo o resultado com os métodos get do ResultSet
			item_da_lista.setIdfabricante(resultado.getLong("idfabricante"));
			item_da_lista.setDescricao(resultado.getString("descricao"));
			// adicionando os resultados obtidos na lista
			lista.add(item_da_lista);

		}

		return lista;

	}

	// C[R]UD -> 3 DE 3 (TODOS)
	// método listar() // throws propagando excessões para a view
	// o retorno é mais de um resultado então é usado uma coleção
	// aqui um ArrayList.
	public ArrayList<FabricanteDTO> listar() throws SQLException {

		StringBuilder sql = new StringBuilder();
		sql.append("SELECT idfabricante,descricao ");
		sql.append("FROM fabricante ");

		// NÃO PRECISO FILTRAR E ORDENAR AQUI - FAREI NO COMPONENTE DO PRIMEFACES
		// ordenando os resultados que podem ser um ou mais.
		// sql.append("ORDER BY descricao ASC"); // deixa por ordem de descrição

		Connection conexao = ConexaoFactory.conectar();

		PreparedStatement comando = conexao.prepareStatement(sql.toString()); // converter StringBuilder para String

		// mapeamento objeto relacional

		// retorno terá que ser do tipo ArrayList de fabricantes

		// interface para criar uma variável que guarda as consultas SQL
		// no ResulSet os dados são perdidos quando a conexão com o banco é encerrada
		ResultSet resultado = comando.executeQuery(); // comando para consultas, retorna o resultado na variável.

		// <> o símbolo do diamante é uma trava
		// a partir do java 7 não é obrigado no construtor (na direita)
		ArrayList<FabricanteDTO> lista = new ArrayList<FabricanteDTO>();

		// verificar se há algo a ser consumido
		// se tiver próximo consumir, possivelmente mais de um então usa-se "enquanto".
		while (resultado.next()) {

			FabricanteDTO fabricante = new FabricanteDTO();
			// consumindo o resultado com os métodos get do ResultSet
			fabricante.setIdfabricante(resultado.getLong("idfabricante"));
			fabricante.setDescricao(resultado.getString("descricao"));
			// adicionando os resultados obtidos na lista
			lista.add(fabricante);

		}

		return lista;

	}

	// CR[U]D
	// método editar() // throws propagando excessões para a view
	public void editar(FabricanteDTO fabricante) throws SQLException { // assinatura do método

		StringBuilder sql = new StringBuilder();
		sql.append("UPDATE fabricante ");
		sql.append("SET descricao = ? ");
		sql.append("WHERE idfabricante = ?");

		Connection conexao = ConexaoFactory.conectar();

		PreparedStatement comando = conexao.prepareStatement(sql.toString()); // converter StringBuilder para String

		// mapeamento objeto relacional
		comando.setString(1, fabricante.getDescricao());
		comando.setLong(2, fabricante.getIdfabricante());

		comando.executeUpdate();
	}

	// CRU[D]
	// método excluir() // throws propagando excessões para a view
	public void excluir(FabricanteDTO fabricante) throws SQLException { // assinatura do método

		StringBuilder sql = new StringBuilder();
		sql.append("DELETE FROM fabricante ");
		sql.append("WHERE idfabricante = ?");

		Connection conexao = ConexaoFactory.conectar();

		PreparedStatement comando = conexao.prepareStatement(sql.toString());
		// converter StringBuilder para String

		// mapeamento objeto relacional
		comando.setLong(1, fabricante.getIdfabricante());

		comando.executeUpdate();

	}

}