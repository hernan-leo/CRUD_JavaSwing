package models;

import java.util.ArrayList;
import java.util.List;

import javax.swing.table.AbstractTableModel;

public class TableModel extends AbstractTableModel  {
	private static final int COL_ID_FUNCIONARIO = 0;
	private static final int COL_CPF = 1;
	private static final int COL_NOME = 2;
	private static final int COL_CARGO = 3;
	private static final int COL_ENDERECO = 4;
	private static final int COL_TELEFONE = 5;
	private List<Funcionario> dados;
	
	
	public TableModel(List<Funcionario> dados) {
		this.dados = dados;
	}
	
	public String getColumnName(int column) {
		String coluna ="";
		switch(column) {
		case COL_ID_FUNCIONARIO:
			coluna = "Id_Funcionario";
			break;
		case COL_CPF:
			coluna = "CPF";
			break;
		case COL_NOME:
			coluna = "Nome";
			break;
		case COL_CARGO:
			coluna = "Cargo";
			break;
		case COL_ENDERECO:
			coluna ="Endereço";
			break;
		case COL_TELEFONE:
			coluna ="Telefone";
			break;
		
		}
		return coluna;
	}
	@Override
	public Class<?> getColumnClass(int coluna){
		if(coluna == COL_ID_FUNCIONARIO) {
			return int.class;
		}
		else if(coluna == COL_CPF) {
			return String.class;
		}else if(coluna == COL_NOME) {
			return String.class;
		}else if(coluna == COL_CARGO) {
			return String.class;
		}else if(coluna == COL_ENDERECO) {
			return String.class;
		}else if(coluna == COL_TELEFONE) {
			return String.class;
		}
		return null;
	}
	

	
	@Override
	public int getRowCount() {
		
		return dados.size();
	}
	@Override
	public int getColumnCount() {
		
		return 6;
	}

	

	@Override
	public Object getValueAt(int linha, int coluna) {
		Funcionario funcionario = dados.get(linha);
		if(coluna == COL_ID_FUNCIONARIO) {
			return funcionario.getId_Funcionario();
		}
		else if(coluna == COL_CPF) {
			return funcionario.getCpf();
		}else if(coluna == COL_NOME) {
			return funcionario.getNome();
		}else if(coluna == COL_CARGO) {
			return funcionario.getCargo();
		}else if(coluna == COL_ENDERECO) {
			return funcionario.getEndereco();
		}else if(coluna == COL_TELEFONE) {
			return funcionario.getTelefone();
		}
		
		
		// TODO Auto-generated method stub
		return null;
	}
	
	public Funcionario get(int row) {
		return dados.get(row);
	}



	
	
	

}
