package controllers;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Conexao {
	
	final static private String url = "jdbc:postgresql://localhost:5432/biblioteca";
	final static private String usuario = "postgres";
	final static private String senha = "admin";
	final static private String driver = "org.postgresql.Driver";
	
	static Connection conn;

	public static Connection getConecta() {
		
		try {
		Class.forName(driver);
		}
		catch(ClassNotFoundException ex) {
			System.out.println("Driver não encontrado");
			ex.printStackTrace();
		}
		try {
			conn = DriverManager.getConnection(url, usuario, senha);
			
		}
		catch(SQLException se) {
			System.out.println("Não foi possivel conectar");
			se.printStackTrace();
			
		}
		
		return conn;
		
	}
	public static void fechaConexao()  {
		try {
		conn.close();
	}catch(Exception ex) {
		ex.printStackTrace();
	}
	}
}
