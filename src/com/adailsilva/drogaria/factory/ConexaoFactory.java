package com.adailsilva.drogaria.factory;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import com.mysql.jdbc.Driver;

public class ConexaoFactory {

	// privados, não precisam ser instanciados e são constantes.
	private static final String USER = "root";
	private static final String PASSWORD = "Hacker101";
	private static final String URL = "jdbc:mysql://127.0.0.1:3306/drogaria";

	public static Connection conectar() throws SQLException { // propaguei a exceção para quem chamar a conexão
		// registro do Driver não obrigatório a partir do java 6
		DriverManager.registerDriver(new Driver());
		Connection conexao = DriverManager.getConnection(URL, USER, PASSWORD);
		return conexao;
	}

	/*
	 * // teste public static void main(String[] args) { try { Connection
	 * testeDeConexao = ConexaoFactory.conectar();
	 * System.out.println("Conexão realizada com sucesso!"); } catch (SQLException
	 * e) { e.printStackTrace();
	 * System.out.println("Não foi possível realizar a conexão!"); } }
	 */
}