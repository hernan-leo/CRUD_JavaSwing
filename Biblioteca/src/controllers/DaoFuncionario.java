package controllers;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

import javax.swing.JOptionPane;

import models.Funcionario;
import views.ListaFuncionario;


public class DaoFuncionario extends Conexao {
	
	
	
	String sql;
	
	public void criar(Funcionario funcionario) {
		try {
			sql = "INSERT INTO FUNCIONARIO (CPF, NOME, CARGO, ENDERECO, TELEFONE, SENHA) VALUES (?,?,?,?,?,?)";
			Connection con = getConecta();
			PreparedStatement stmt = null;
			stmt = con.prepareStatement(sql);
			
			
			stmt.setString(1,funcionario.getCpf());
			stmt.setString(2, funcionario.getNome());
			stmt.setString(3, funcionario.getCargo());
			stmt.setString(4, funcionario.getEndereco());
			stmt.setString(5, funcionario.getTelefone());
			stmt.setString(6, funcionario.getSenha());
			
			stmt.execute();
			stmt.close();
			JOptionPane.showMessageDialog(null, "Funcionario cadastrado");
		}catch(SQLException ex) {
			System.out.println("Erro ao cadastrar Funcionario");
			ex.printStackTrace();
		}finally {
			fechaConexao();
		}
	}
	
	

	
	//********************METODO UPDATE*********************************************
	public void editar(Funcionario funcionario) {
		Connection con = getConecta();
		PreparedStatement stmt = null;
		try {
			
			sql = "UPDATE FUNCIONARIO SET NOME=? ,CARGO=? ,ENDERECO=? ,TELEFONE=? WHERE CPF = ?";
			stmt = con.prepareStatement(sql);
			
			Funcionario f = new Funcionario();
			//stmt.setString(1, funcionario.getCpf());
			stmt.setString(1, funcionario.getNome());
			stmt.setString(2, funcionario.getCargo());
			stmt.setString(3, funcionario.getEndereco());
			stmt.setString(4, funcionario.getTelefone());
			stmt.setString(5, funcionario.getCpf());
			stmt.executeUpdate();
			JOptionPane.showMessageDialog(null,"Funcionario atualizado com sucesso");
		}catch(SQLException se) {
			System.out.println("Erro ao atualizar Funcionario");
			se.printStackTrace();
		}finally {
			fechaConexao();
		}
		
		
	}
		///*****************************METODO LISTAR **************************************
		public List<Funcionario> listar(){ 
			
			Connection con = getConecta();
			PreparedStatement stmt = null;
			ResultSet rs = null;
			List<Funcionario> funcionario = new ArrayList<>();
			
			try
			{
				sql = "SELECT * FROM FUNCIONARIO";
				stmt = con.prepareStatement(sql);
				rs = stmt.executeQuery();
				
				while(rs.next())
				{
					Funcionario f = new Funcionario();
					f.setId_Funcionario(rs.getInt("Id_Funcionario"));
					f.setCpf(rs.getString("cpf"));
					f.setNome(rs.getString("nome"));
					f.setCargo(rs.getString("cargo"));
					f.setEndereco(rs.getString("endereco"));
					f.setTelefone(rs.getString("telefone"));
					funcionario.add(f);
				}
				
				
			}
			catch(SQLException se) {
				System.out.println("Erro ao listar funcionario");
				se.printStackTrace();
			}finally {
				fechaConexao();
			}
			return funcionario;
			
		
		}
		//***************************METODO PESQUISAR POR CPF******************************
		public List<Funcionario> pesquisarCpf(String cpf){ 
			Connection con = getConecta();
			PreparedStatement stmt = null;
			ResultSet rs = null;
			List<Funcionario> funcionario = new ArrayList<>();
			try {
				sql = "SELECT * FROM FUNCIONARIO WHERE cpf = ? ";
				stmt = con.prepareStatement(sql);
				stmt.setString(1, cpf);
				rs = stmt.executeQuery();
				
				while(rs.next()) {
					Funcionario f = new Funcionario();
					f.setId_Funcionario(rs.getInt("Id_Funcionario"));
					f.setCpf(rs.getString("cpf"));
					f.setNome(rs.getString("nome"));
					f.setCargo(rs.getString("cargo"));
					f.setEndereco(rs.getString("endereco"));
					f.setTelefone(rs.getString("telefone"));
					funcionario.add(f);
				}
				stmt.close();
				
			}
			catch(SQLException ex) {
				System.out.println("Erro ao pesquisar funcionario");
				ex.printStackTrace();
			}finally {
				fechaConexao();
			}
			return funcionario;
			
		}
		//************************METODO DELETAR***********************************
		public void deletar(Funcionario f) { 
			Connection con = getConecta();
			PreparedStatement stmt = null;
			try {
				sql ="DELETE FROM FUNCIONARIO WHERE CPF=?";
				stmt = con.prepareStatement(sql);
				stmt.setString(1, f.getCpf());
				stmt.execute();
				stmt.close();
				JOptionPane.showMessageDialog(null,"Funcionario:  " + f.getCpf() + " excluido");
			}catch(SQLException ex) {
				System.out.println("Erro ao deleter funcionario");
				ex.printStackTrace();
			}finally {
				fechaConexao();
			}
			
		}
		
		//*************************METODO LOGAR**********************************
		public Funcionario logar(String cpf, String senha) {
			
			Funcionario funcionario = null;
			Connection con = getConecta();
			PreparedStatement stmt = null;
			try {
				sql = "SELECT cpf, senha FROM FUNCIONARIO WHERE cpf=? AND SENHA=?";
				stmt = con.prepareStatement(sql);
				stmt.setString(1,cpf);
				stmt.setString(2, senha);
				
				ResultSet rs = null;
				rs = stmt.executeQuery();
				
				if (rs.next()) {
					funcionario = new Funcionario();
					funcionario.setCpf(rs.getString("cpf"));
					funcionario.setSenha(rs.getString("senha"));
					
					
					
					
					
				}
				
								
					
				
					
				
			}catch(SQLException ex) {
				JOptionPane.showMessageDialog(null,"Erro ao recuperar Cpf e Senha");
				ex.printStackTrace();
				
			}finally {
				fechaConexao();
			}
			return funcionario;
			
		}
		public Funcionario setarNomeeId(String cpf) {
			Funcionario funcionario = null;
			
			try {
				
				Connection con = getConecta();
				PreparedStatement stmt = null;
				ResultSet rs = null;
				
			sql = "SELECT cpf, id_funcionario, nome FROM FUNCIONARIO WHERE cpf = ?";
			 stmt = con.prepareStatement(sql);
			
				 stmt.setString(1, cpf);
			 
			 rs = stmt.executeQuery();
			 
			 if (rs.next()) {
				 Funcionario f = new Funcionario();
				f.setCpf(rs.getString("cpf"));
				 f.setId_Funcionario(rs.getInt("id_funcionario"));
				 f.setNome(rs.getString("nome"));
				 funcionario = f;
				
			 }
			 stmt.close();
			
			}catch(SQLException ex) {
				JOptionPane.showMessageDialog(null,"Id e nome não encontrados");
				ex.printStackTrace();
				
			}finally {
				fechaConexao();
			}
			return funcionario;
		}

}
