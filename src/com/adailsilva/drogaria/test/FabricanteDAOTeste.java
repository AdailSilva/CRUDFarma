package com.adailsilva.drogaria.test;

import java.sql.SQLException;
import java.util.ArrayList;

import org.junit.Ignore;
import org.junit.Test;

import com.adailsilva.drogaria.dao.FabricanteDAO;
import com.adailsilva.drogaria.domain.FabricanteDTO;

public class FabricanteDAOTeste {

	@Test
	@Ignore
	public void salvar() throws SQLException {

		// criando objetos e setando um parâmetro
		FabricanteDTO fabricante_1 = new FabricanteDTO();
		fabricante_1.setDescricao("Descrição 1");

		FabricanteDTO fabricante_2 = new FabricanteDTO();
		fabricante_2.setDescricao("Descrição 2");

		// salvando
		FabricanteDAO fabricante_dao = new FabricanteDAO();

		fabricante_dao.salvar(fabricante_1);
		fabricante_dao.salvar(fabricante_2);

		System.out.println("Fabricantes inclusos com sucesso!");

	}

	@Test
	@Ignore
	public void buscarPorCodigo() throws SQLException {

		// os objetos aqui estarão incompletos, apenas com os seus códigos.
		FabricanteDTO fabricante_1 = new FabricanteDTO();
		fabricante_1.setIdfabricante(3L);

		FabricanteDTO fabricante_2 = new FabricanteDTO();
		fabricante_2.setIdfabricante(4L);

		FabricanteDTO fabricante_3 = new FabricanteDTO();
		fabricante_3.setIdfabricante(5L);

		// listando por código
		FabricanteDAO fabricante_dao = new FabricanteDAO();

		// necessário uma variável do mesmo tipo para pegar a resposta
		// os objetos aqui estarão completos, caso tenham sido encontrados.
		FabricanteDTO fabricante_result_1 = fabricante_dao.buscarPorCodigo(fabricante_1);
		FabricanteDTO fabricante_result_2 = fabricante_dao.buscarPorCodigo(fabricante_2);
		FabricanteDTO fabricante_result_3 = fabricante_dao.buscarPorCodigo(fabricante_3);

		// imprimindo os objetos para conferência
		// (implementar os métodos toString nos POJOS)
		System.out.println("Objeto 1: " + fabricante_result_1);
		System.out.println("Objeto 2: " + fabricante_result_2);
		System.out.println("Objeto 3: " + fabricante_result_3);

	}

	@Test
	@Ignore
	public void buscarPorDescricao() throws SQLException {

		FabricanteDTO fabricante = new FabricanteDTO();
		fabricante.setDescricao("Desc");

		FabricanteDAO fabricante_dao = new FabricanteDAO();

		ArrayList<FabricanteDTO> lista = fabricante_dao.buscarPorDescricao(fabricante);

		// laço para percorrer a lista
		for (FabricanteDTO item_da_lista : lista) {
			System.out.println(item_da_lista);
		}

	}

	@Test
	@Ignore
	public void listar() throws SQLException {

		// não tem critério de fabricante específico então ir direto para o DAO
		// não dá para checar o ArrayList como nulo, então será verificado seu tamanho.
		// 0 para vazio ou maior que 0 para existência de dados na coleção.

		FabricanteDAO fabricante_dao = new FabricanteDAO();

		ArrayList<FabricanteDTO> lista = fabricante_dao.listar();

		// usando um laço para mostrar os dados da lista
		for (FabricanteDTO fabricantes : lista) {
			System.out.println(fabricantes);
		}

	}

	@Test
	@Ignore
	public void editar() throws SQLException {

		FabricanteDTO fabricante_1 = new FabricanteDTO();
		fabricante_1.setIdfabricante(3L); // L após para mudar o tipo padrão inteiro para Long
		fabricante_1.setDescricao("Edição de um Fabricante Qualquer");

		FabricanteDTO fabricante_2 = new FabricanteDTO();
		fabricante_2.setIdfabricante(4L); // L após para mudar o tipo padrão inteiro para Long
		fabricante_2.setDescricao("Edição de um Outro Fabricante");

		// alterando
		FabricanteDAO fabricante_dao = new FabricanteDAO();

		fabricante_dao.editar(fabricante_1);
		fabricante_dao.editar(fabricante_2);

		System.out.println("Fabricantes alterados com sucesso!");

	}

	@Test
	@Ignore
	public void excluir() throws SQLException {

		// criando objetos e setando um parâmetro
		FabricanteDTO fabricante_1 = new FabricanteDTO();
		fabricante_1.setIdfabricante(1L); // L após para mudar o tipo padrão inteiro para Long

		FabricanteDTO fabricante_2 = new FabricanteDTO();
		fabricante_2.setIdfabricante(2L); // L após para mudar o tipo padrão inteiro para Long

		// excluindo
		FabricanteDAO fabricante_dao = new FabricanteDAO();
		fabricante_dao.excluir(fabricante_1);
		fabricante_dao.excluir(fabricante_2);

		System.out.println("Fabricantes removidos com sucesso!");
	}

}