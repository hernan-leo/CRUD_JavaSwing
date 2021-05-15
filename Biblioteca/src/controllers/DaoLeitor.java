package controllers;


import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JOptionPane;

import models.Leitor;

public class DaoLeitor extends Conexao{
	
	String sql;
	SimpleDateFormat formato = new SimpleDateFormat("yyyy-MM-dd");
	
	
	public void criar(Leitor leitor) throws ParseException {
		java.sql.Date dataSQL = new Date(leitor.getDataDeCadastro().getTime());
		try {
			sql = "INSERT INTO LEITOR (ID_FUNCIONARIO,NOME,CPF,TELEFONE,EMAIL,ENDERECO,SENHA,DATADENASCIMENTO,DATADECADASTRO,"
					+ "PENDENCIAS,SITUACAO) VALUES (?,?,?,?,?,?,?,?,?,?,?)";
			Connection conn = Conexao.getConecta();
			PreparedStatement stmt = conn.prepareStatement(sql);
			
			stmt.setInt(1,leitor.getId_funcionario());
			stmt.setString(2,leitor.getNome());
			stmt.setString(3, leitor.getCpf());
			stmt.setString(4, leitor.getTelefone());
			stmt.setString(5,leitor.getEmail());
			stmt.setString(6,leitor.getEndereco());
			stmt.setString(7, leitor.getSenha());
			stmt.setDate(8,Date.valueOf(leitor.getDataDeNascimento()));
			stmt.setDate(9,dataSQL);
			stmt.setString(10, leitor.getPendencias());
			stmt.setString(11,leitor.getSituacao());
			stmt.execute();
			
			stmt.close();
		
			
			
			
		}catch (SQLException ex) {
			ex.printStackTrace();
		}finally {
			fechaConexao();
		}
	}
	public List<Leitor> listar(){
		List<Leitor> leitor = new ArrayList<>();
		try {
			sql = "SELECT * FROM LEITOR";
			Connection conn = Conexao.getConecta();
			PreparedStatement stmt = conn.prepareStatement(sql);
			ResultSet rs = stmt.executeQuery();
			while (rs.next()) {
				Leitor l = new Leitor();
				l.setId_funcionario(rs.getInt("id_funcionario"));
				l.setNome(rs.getString("nome"));
				l.setCpf(rs.getString("cpf"));
				l.setTelefone(rs.getString("telefone"));
				l.setEmail(rs.getString("email"));
				l.setEndereco(rs.getString("endereco"));
				l.setSenha(rs.getString("senha"));
				l.setDataDeNascimento(rs.getString("datadenascimento"));
				l.setDataDeCadastro(rs.getDate("datadecadastro"));
				l.setPendencias(rs.getString("pendencias"));
				l.setSituacao(rs.getString("situacao"));
				leitor.add(l);
				
				
			}
			rs.close();
			stmt.close();
			
		
		}catch(SQLException ex) {
			JOptionPane.showMessageDialog(null,"Erro ao listar Leitor");
			ex.printStackTrace();
		}finally {
			fechaConexao();
		}
		return leitor;
		
		
	}
	

}
