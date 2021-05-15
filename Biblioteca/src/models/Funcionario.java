package models;

import java.util.List;

public class Funcionario  {
	private int id_Funcionario;
	
	private String cpf;
	private String nome;
	private String cargo;
	private String endereco;
	private String telefone;
	private String senha;

	/*public Funcionario() {
		
	}
	private static class FuncionarioHolder {
		private final static  Funcionario instance = new Funcionario();
	}
	public static Funcionario getInstance() {
		return FuncionarioHolder.instance;
	}*/
	public String getSenha() {
		return senha;
	}
	public void setSenha(String senha) {
		this.senha = senha;
	}
	public String getCpf() {
		return cpf;
	}
	public void setCpf(String cpf) {
		this.cpf = cpf;
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public String getCargo() {
		return cargo;
	}
	public void setCargo(String cargo) {
		this.cargo = cargo;
	}
	public String getEndereco() {
		return endereco;
	}
	public void setEndereco(String endereco) {
		this.endereco = endereco;
	}
	public String getTelefone() {
		return telefone;
	}
	public void setTelefone(String telefone) {
		this.telefone = telefone;
	}
	public int getId_Funcionario() {
		return id_Funcionario;
	}
	public void setId_Funcionario(int id_Funcionario) {
		this.id_Funcionario = id_Funcionario;
	}
	
	
	

}
