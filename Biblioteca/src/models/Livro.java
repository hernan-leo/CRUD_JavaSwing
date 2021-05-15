package models;

import java.util.Date;

public class Livro {
	
	private int id_Livro;
	private int id_Funcionario;
	private String titulo;
	private String genero;
	private String editora;
	private String autor;
	private float valorEstimado;
	private Date dataDeEntrada;
	private Date atualizado;
	
	
	public int getId_Livro() {
		return id_Livro;
	}
	public void setId_Livro(int id_Livro) {
		this.id_Livro = id_Livro;
	}
	public String getTitulo() {
		return titulo;
	}
	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}
	public String getGenero() {
		return genero;
	}
	public void setGenero(String genero) {
		this.genero = genero;
	}
	public String getEditora() {
		return editora;
	}
	public void setEditora(String editora) {
		this.editora = editora;
	}
	public String getAutor() {
		return autor;
	}
	public void setAutor(String autor) {
		this.autor = autor;
	}
	public float getValorEstimado() {
		return valorEstimado;
	}
	public void setValorEstimado(float valorEstimado) {
		this.valorEstimado = valorEstimado;
	}
	public Date getDataDeEntrada() {
		return dataDeEntrada;
	}
	public void setDataDeEntrada(Date dataDeEntrada) {
		this.dataDeEntrada = dataDeEntrada;
	}
	public int getId_Funcionario() {
		return id_Funcionario;
	}
	public void setId_Funcionario(int id_Funcionario) {
		this.id_Funcionario = id_Funcionario;
	}
	public Date getAtualizado() {
		return atualizado;
	}
	public void setAtualizado(Date atualizado) {
		this.atualizado = atualizado;
	}

}
