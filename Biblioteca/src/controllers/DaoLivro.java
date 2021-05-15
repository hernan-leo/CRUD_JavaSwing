package controllers;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JOptionPane;

import models.Livro;
import views.ListaLivro;

public class DaoLivro extends Conexao{
	
	private String sql;
	
	
	public void criar(Livro livro) throws SQLException {
		
		Connection con = getConecta();
		PreparedStatement stmt = null;
		java.sql.Date dataSQL = new Date(livro.getDataDeEntrada().getTime());
		try {
			
			sql = "INSERT INTO LIVRO (id_funcionario,titulo,genero,editora,autor,valor,datadeentrada) VALUES (?,?,?,?,?,?,?)";
			stmt = con.prepareStatement(sql);
			stmt.setInt(1,livro.getId_Funcionario());
			stmt.setString(2,livro.getTitulo());
			stmt.setString(3, livro.getGenero());
			stmt.setString(4, livro.getEditora());
			stmt.setString(5, livro.getAutor());
			stmt.setFloat(6, livro.getValorEstimado());
			stmt.setDate(7, dataSQL);
			JOptionPane.showMessageDialog(null,"O livro de titulo " + livro.getTitulo() + " cadastrado com sucesso");
			stmt.execute();
			stmt.close();
		}catch(SQLException se) {
			System.out.println("Erro ao cadastrar livro");
			se.printStackTrace();
		}finally {
			fechaConexao();
		}
		
	}
	
	public List<Livro> listar(){
		
		List<Livro> livro = new ArrayList<>();
		try {
			sql = "SELECT * FROM LIVRO";
			Connection con = getConecta();
			PreparedStatement stmt = null;
			stmt = con.prepareStatement(sql);
			ResultSet rs = null;
			rs = stmt.executeQuery();
			
			while(rs.next()) {
				Livro l = new Livro();
				l.setId_Livro(rs.getInt("id_livro"));
				l.setId_Funcionario(rs.getInt("id_funcionario"));
				l.setTitulo(rs.getString("titulo"));
				l.setGenero(rs.getString("genero"));
				l.setEditora(rs.getString("editora"));
				l.setAutor(rs.getString("autor"));
				l.setValorEstimado(rs.getFloat("valor"));
				l.setDataDeEntrada(rs.getDate("datadeentrada"));
				livro.add(l);
			}
			rs.close();
			stmt.close();
			}catch(SQLException e){
				JOptionPane.showMessageDialog(null,"Erro ao listar Livro");
				e.printStackTrace();
			}finally {
				fechaConexao();
			}
		return livro;
				
			}
	public void editar(Livro livro) throws SQLException {
		java.sql.Date DataSql = new Date(livro.getAtualizado().getTime());
		try {
			sql ="UPDATE LIVRO SET TITULO=?, GENERO=?, EDITORA=?, AUTOR=?, VALOR=?,ATUALIZADO=? WHERE ID_LIVRO =?";
			Connection con = getConecta();
			PreparedStatement stmt = null;
			stmt = con.prepareStatement(sql);
			stmt.setString(1, livro.getTitulo());
			stmt.setString(2, livro.getGenero());
			stmt.setString(3, livro.getEditora());
			stmt.setString(4, livro.getAutor());
			stmt.setFloat(5, livro.getValorEstimado());
			stmt.setDate(6, DataSql);
			stmt.setInt(7, livro.getId_Livro());
			stmt.executeUpdate();
			
			JOptionPane.showMessageDialog(null, "Livro de ID: " + livro.getId_Livro()+ " atualizado");
			stmt.close();
		}catch(SQLException ex) {
			JOptionPane.showMessageDialog(null,"Erro ao atualizar livro");
			ex.printStackTrace();
			
		}finally {
			fechaConexao();
		}
	}
	public List<Livro> pesquisarId(Livro titulo){
		List<Livro> livro = new ArrayList<>();
		Connection con = getConecta();
		PreparedStatement stmt = null;
		ResultSet rs = null;
		try {
			sql="SELECT * FROM LIVRO WHERE TITULO LIKE ?";
			
			stmt = con.prepareStatement(sql);
			stmt.setString(1, "%" + titulo.getTitulo()+ "%");
			
			
			rs = stmt.executeQuery();
			while(rs.next()) {
			Livro l = new Livro();
			l.setId_Livro(rs.getInt("id_livro"));
			l.setId_Funcionario(rs.getInt("id_funcionario"));
			l.setTitulo(rs.getString("titulo"));
			l.setGenero(rs.getString("genero"));
			l.setEditora(rs.getString("editora"));
			l.setAutor(rs.getString("autor"));
			l.setValorEstimado(rs.getFloat("valor"));
			l.setDataDeEntrada(rs.getDate("datadeentrada"));
			livro.add(l);
			
			
			}
			rs.close();
			stmt.close();
			
			
		}catch(SQLException ex) {
			JOptionPane.showMessageDialog(null,"Erro ao pesquisar por nome");
			ex.printStackTrace();
			
		}finally {
			fechaConexao();
		}
		return livro;
	}
	public void deletar(Livro l) {
		try {
			sql = "DELETE FROM LIVRO WHERE ID_LIVRO = ?";
			Connection con = getConecta();
			PreparedStatement stmt = null;
			stmt = con.prepareStatement(sql);
			stmt.setInt(1,l.getId_Livro());
			stmt.execute();
			stmt.close();
			JOptionPane.showMessageDialog(null, "Exclusão concluida");
			
		}catch(SQLException ex) {
			JOptionPane.showMessageDialog(null, "Erro ao deletar livro");
			ex.printStackTrace();
		}finally {
			fechaConexao();
		}
		
	}
	
		
		
	}


