package models;

import java.sql.Date;
import java.util.List;

import javax.swing.table.AbstractTableModel;

public class TableLivro extends AbstractTableModel{
	
	private static final int COL_ID_LIVRO = 0;
	private static final int COL_ID_FUNCIONARIO = 1;
	private static final int COL_TITULO = 2;
	private static final int COL_GENERO = 3;
	private static final int COL_EDITORA = 4;
	private static final int COL_AUTOR = 5;
	private static final int COL_VALOR = 6;
	private static final int COL_DATADEENTRADA = 7;
	private List<Livro> dados; 
	

	public TableLivro(List<Livro> dados) {// Inseri um List no constructor
		this.dados = dados;
	}
	
	public String getColumnName(int column) {///Nomeia as colunas
		String coluna ="";
		switch(column) {
		case COL_ID_LIVRO:
			coluna = "ID_LIVRO";
			break;
		case COL_ID_FUNCIONARIO:
			coluna = "ID_FUNCIONARIO";
			break;
		case COL_TITULO:
			coluna ="Titulo";
			break;
		case COL_GENERO:
			coluna ="Gênero";
			break;
		case COL_EDITORA:
			coluna ="Editora";
			break;
		case COL_AUTOR:
			coluna ="Autor";
			break;
		case COL_VALOR:
			coluna ="Valor";
			break;
		case COL_DATADEENTRADA:
			coluna ="Data_de_Entrada";
			break;
		}
		return coluna;
	}
	@Override
	public Class<?> getColumnClass(int coluna){//Especifica o tipo de coluna
		if(coluna == COL_ID_LIVRO) {
		      return int.class;
		}else if(coluna == COL_ID_FUNCIONARIO) {
			return int.class;
		}else if(coluna == COL_TITULO) {
			return String.class;
		}else if(coluna == COL_GENERO) {
			return String.class;
		}else if (coluna == COL_EDITORA) {
			return String.class;
		}else if (coluna == COL_AUTOR) {
			return String.class;
		}else if (coluna == COL_VALOR) {
			return float.class;
		}else if (coluna == COL_DATADEENTRADA) {
			return Date.class;
		}
		return null;
	}

	@Override
	public int getColumnCount() {//especifica a quantidade de colunas
		// TODO Auto-generated method stub
		return 8;
	}

	@Override
	public int getRowCount() {
		// TODO Auto-generated method stub
		return dados.size();
	}

	@Override
	public Object getValueAt(int linha, int coluna) {///especifica 
		Livro livro = dados.get(linha);
		if(coluna == COL_ID_LIVRO) {
			return livro.getId_Livro();
		}else if(coluna == COL_ID_FUNCIONARIO) {
			return livro.getId_Funcionario();
		}else if(coluna == COL_TITULO) {
			return livro.getTitulo();
		}else if(coluna == COL_GENERO) {
			return livro.getGenero();
		}else if (coluna == COL_EDITORA) {
			return livro.getEditora();
		}else if (coluna == COL_AUTOR) {
			return livro.getAutor();
		}else if (coluna == COL_VALOR) {
			return livro.getValorEstimado();
		}else if (coluna == COL_DATADEENTRADA) {
			return livro.getDataDeEntrada();
		}
		return null;
	}
	public Livro get(int row) {
		return dados.get(row);
	}
	
}
	

