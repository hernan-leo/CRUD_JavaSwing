package views;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import controllers.Conexao;
import controllers.DaoFuncionario;
import controllers.DaoLivro;
import models.Funcionario;
import models.Livro;
import models.Sessao;
import models.TableLivro;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.JComboBox;
import javax.swing.JFormattedTextField;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JButton;
import javax.swing.UIManager;
import javax.swing.border.LineBorder;
import javax.swing.text.MaskFormatter;

import java.awt.Color;
import javax.swing.BoxLayout;

public class ListaLivro extends JFrame {

	private JPanel txtPanel;
	private JTextField txtId_livro;
	private JTextField txtTitulo;
	private JTextField txtEditora;
	private JTextField txtAutor;
	private JTextField txtValor;
	private JTextField txtData;
	private JTable table;
	JLabel lblId,lblNome,lblEditora,lblAutor;
	private List <Livro> listaLivro;
	String cpf = Sessao.getInstance().getFuncionario().getCpf();
	String senha = Sessao.getInstance().getFuncionario().getSenha();
	String[] generos = {"Selecione","Programação","Literatura","Infantil","Didatico"};
	String genero;
	Date hoje = new Date();
	SimpleDateFormat dataFormatada = new SimpleDateFormat("dd/MM/yyyy");
	

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
            //here you can put the selected theme class name in JTattoo
            UIManager.setLookAndFeel("com.jtattoo.plaf.mint.MintLookAndFeel");
 
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(ListaLivro.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(ListaLivro.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(ListaLivro.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(ListaLivro.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ListaLivro frame = new ListaLivro();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 * @throws ParseException 
	 */
	public ListaLivro() throws ParseException {
		//Cria objeto funcionario para pegar Id e Nome do Funcionario:
		
		DaoFuncionario dao = new DaoFuncionario();
		Funcionario funcionario = dao.setarNomeeId(cpf);
		//listar();
		////FIM CAMBIARRA 
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 704, 503);
		txtPanel = new JPanel();
		txtPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(txtPanel);
		
		JScrollPane scrollPane = new JScrollPane();
		
		JPanel panel = new JPanel();
		panel.setBorder(UIManager.getBorder("DesktopIcon.border"));
		
		JPanel panel_1 = new JPanel();
		panel_1.setBorder(UIManager.getBorder("DesktopIcon.border"));
		
		JPanel panel_2 = new JPanel();
		GroupLayout gl_txtPanel = new GroupLayout(txtPanel);
		gl_txtPanel.setHorizontalGroup(
			gl_txtPanel.createParallelGroup(Alignment.LEADING)
				.addComponent(panel, GroupLayout.DEFAULT_SIZE, 678, Short.MAX_VALUE)
				.addComponent(panel_1, GroupLayout.DEFAULT_SIZE, 678, Short.MAX_VALUE)
				.addComponent(scrollPane, GroupLayout.DEFAULT_SIZE, 678, Short.MAX_VALUE)
				.addComponent(panel_2, GroupLayout.DEFAULT_SIZE, 678, Short.MAX_VALUE)
		);
		gl_txtPanel.setVerticalGroup(
			gl_txtPanel.createParallelGroup(Alignment.LEADING)
				.addGroup(Alignment.TRAILING, gl_txtPanel.createSequentialGroup()
					.addComponent(panel, GroupLayout.PREFERRED_SIZE, 190, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
					.addComponent(panel_1, GroupLayout.PREFERRED_SIZE, 37, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(scrollPane, GroupLayout.PREFERRED_SIZE, 187, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(panel_2, GroupLayout.PREFERRED_SIZE, 22, GroupLayout.PREFERRED_SIZE)
					.addGap(37))
		);
		panel_2.setLayout(new BoxLayout(panel_2, BoxLayout.X_AXIS));
	
		//CAMPO JBUTTONS***********************************************
		
		JButton btnSalvar = new JButton("Gravar");
		btnSalvar.setIcon(new ImageIcon(NovoLivro.class.getResource("/img/save.png")));
		btnSalvar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg) {
				
					Livro l = new Livro();
					l.setId_Funcionario(funcionario.getId_Funcionario());
					l.setTitulo(txtTitulo.getText());
					l.setGenero(genero);
					l.setEditora(txtEditora.getText());
					l.setAutor(txtAutor.getText());
					l.setValorEstimado(Float.parseFloat(txtValor.getText()));
					l.setDataDeEntrada(hoje);
					DaoLivro dao = new DaoLivro();
					try {
						dao.criar(l);
					} catch (SQLException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					
					
			}
		});
		panel_1.add(btnSalvar);
		
		JButton btnEditar = new JButton("Excluir");
		btnEditar.setIcon(new ImageIcon(ListaLivro.class.getResource("/img/trash.png")));
		btnEditar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg) {
				int resu =	0;
				String entrada;
				entrada = JOptionPane.showInputDialog("Digite uma senha valida");
				if (entrada.equals(senha)) {
				
					resu = JOptionPane.showConfirmDialog(null, "Confirma exclusão?", "Tela de confirmação", JOptionPane.YES_NO_CANCEL_OPTION);
					if(resu == JOptionPane.YES_OPTION)
					try {
						Livro l = new Livro();
						l.setId_Livro(Integer.parseInt(txtId_livro.getText()));
						DaoLivro dao = new DaoLivro();
						dao.deletar(l);
						
						
						}catch(Exception e) {
							e.printStackTrace();
						}
					else if(resu == JOptionPane.NO_OPTION) {
						JOptionPane.showMessageDialog(null,"Exclusão cancelada");
					}
					
					
				}else
				{
					JOptionPane.showMessageDialog(null,"Senha invalida");
				
			}
			}
		});
		panel_1.add(btnEditar);
		
		JButton btnPesquisar = new JButton("Procurar");
		btnPesquisar.setIcon(new ImageIcon(ListaLivro.class.getResource("/img/find.png")));
		btnPesquisar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg) {
				String titulo = txtTitulo.getText();
				Livro l = new Livro();
				l.setTitulo(titulo);
				listaLivro = new DaoLivro().pesquisarId(l);
				if(listaLivro != null) {
					table.setModel(new TableLivro(listaLivro));
					
				}
			}
		});
		
		panel_1.add(btnPesquisar);
		
		JButton btnAtualizar = new JButton("Atualizar");
		btnAtualizar.setIcon(new ImageIcon(ListaLivro.class.getResource("/img/edit.png")));
		btnAtualizar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg) {
				try {
					Livro l = new Livro();
					l.setId_Funcionario(funcionario.getId_Funcionario());
					l.setTitulo(txtTitulo.getText());
					l.setGenero(genero);
					l.setEditora(txtEditora.getText());
					l.setAutor(txtAutor.getText());
					l.setValorEstimado(Float.parseFloat(txtValor.getText()));
					l.setAtualizado(hoje);
					l.setId_Livro(Integer.parseInt(txtId_livro.getText()));
					DaoLivro dao = new DaoLivro();
					dao.editar(l);
					
				}catch(Exception e) {
					e.printStackTrace();
				}
			}
		});
		panel_1.add(btnAtualizar);
		
		JButton btnListar = new JButton("Ver Todos");
		panel_1.add(btnListar);
		btnListar.setIcon(new ImageIcon(ListaLivro.class.getResource("/img/Clipboard.png")));
		btnListar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg) {
				listar();
				
			}
		});
		panel.setLayout(null);
		
		JButton btnLimpar = new JButton("Limpar");
		btnLimpar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg) {
				txtId_livro.setText("");
				txtTitulo.setText("");
				txtAutor.setText("");
				txtEditora.setText("");
				txtValor.setText("");
				txtData.setText("");
				genero = "";
			}
		});
		panel_1.add(btnLimpar);
		//FIM JBUTTONS
		
		//CAMPO JLABEL*************************************
		
		JLabel lblNewLabel_5 = new JLabel("Funcionario:");
		panel_2.add(lblNewLabel_5);
		
		JLabel lblId = new JLabel("");
		lblId.setText(String.valueOf(funcionario.getId_Funcionario()));
		panel_2.add(lblId);
		
		JLabel lblNome = new JLabel("");
		lblNome.setText(funcionario.getNome());
		panel_2.add(lblNome);
		panel_1.setLayout(new BoxLayout(panel_1, BoxLayout.X_AXIS));
		
		
		
		JLabel lblIdDoLivro = new JLabel("Id do Livro:");
		lblIdDoLivro.setBounds(10, 22, 65, 14);
		panel.add(lblIdDoLivro);
		
		JLabel lblNewLabel = new JLabel("Titulo:");
		lblNewLabel.setBounds(10, 47, 74, 14);
		panel.add(lblNewLabel);
				
		JLabel lblGnero = new JLabel("G\u00EAnero:");
		lblGnero.setBounds(276, 22, 45, 14);
		panel.add(lblGnero);
		
		JLabel lblNewLabel_1 = new JLabel("Editora:");
		lblNewLabel_1.setBounds(10, 72, 65, 14);
		panel.add(lblNewLabel_1);
		
		
		
		JLabel lblNewLabel_2 = new JLabel("Autor:");
		lblNewLabel_2.setBounds(10, 103, 65, 14);
		panel.add(lblNewLabel_2);
		
		
		
		JLabel lblNewLabel_3 = new JLabel("Valor:");
		lblNewLabel_3.setBounds(10, 134, 45, 14);
		panel.add(lblNewLabel_3);
		
		
		
		JLabel lblNewLabel_4 = new JLabel("Data de entrada:");
		lblNewLabel_4.setBounds(10, 165, 97, 14);
		panel.add(lblNewLabel_4);
		
		//FIM JLABELS
		
		// TextFields************************************
		
		txtId_livro = new JTextField();
		txtId_livro.setBounds(85, 19, 86, 20);
		panel.add(txtId_livro);
		txtId_livro.setColumns(10);
		
		txtTitulo = new JTextField();
		txtTitulo.setBounds(86, 44, 178, 20);
		panel.add(txtTitulo);
		txtTitulo.setColumns(10);
		
		txtAutor = new JTextField();
		txtAutor.setBounds(85, 100, 164, 20);
		panel.add(txtAutor);
		txtAutor.setColumns(10);
		
		txtEditora = new JTextField();
		txtEditora.setBounds(85, 69, 122, 20);
		panel.add(txtEditora);
		txtEditora.setColumns(10);
		
		txtValor = new JTextField();
		txtValor.setBounds(85, 131, 86, 20);
		panel.add(txtValor);
		txtValor.setColumns(10);
		
		txtData = new JTextField();
		txtData.setText(dataFormatada.format(hoje));
		dataFormatada.format(hoje);
		txtData.setBounds(110, 162, 97, 20);
		panel.add(txtData);
		txtData.setColumns(10);
		//FIM TEXTFIELDS
		
		
		//COMBOBOX 
		JComboBox comboBox = new JComboBox(generos);
		comboBox.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg) {
				JComboBox cb = (JComboBox)arg.getSource();
				genero = (String)cb.getSelectedItem();
			}
		});
		comboBox.setBounds(325, 19, 86, 20);
		panel.add(comboBox);
		//FIMCOMBOBOX
		
		
		//TABELA
		table = new JTable();
		table.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				String click_funcio = ""+ table.getValueAt(table.getSelectedRow(), 2);
				try {
					Connection con = Conexao.getConecta();
					PreparedStatement stmt = null;
					ResultSet rs = null;
					String sql = ("SELECT * FROM LIVRO WHERE TITULO = '" + click_funcio + "'");
					stmt = con.prepareStatement(sql);
					rs = stmt.executeQuery();
					while(rs.next()) {
						txtId_livro.setText(rs.getString("id_livro"));
						txtTitulo.setText(rs.getString("titulo"));
						comboBox.setSelectedItem(rs.getString("genero"));
						txtEditora.setText(rs.getString("editora"));
						txtAutor.setText(rs.getString("autor"));
						txtValor.setText(rs.getString("valor"));
						txtData.setText(rs.getString("datadeentrada"));
						
						
					}
					stmt.close();
					Conexao.fechaConexao();
				}catch(SQLException ex){
					ex.printStackTrace();
					
				}
			}
		});
		scrollPane.setViewportView(table);
		txtPanel.setLayout(gl_txtPanel);
		
		
		
	
	}
	public void listar() {
		listaLivro = new DaoLivro().listar();
		if(listaLivro != null) {
			table.setModel(new TableLivro(listaLivro));
		}
	}
}
