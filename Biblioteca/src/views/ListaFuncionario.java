package views;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;



import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.text.MaskFormatter;

import controllers.DaoFuncionario;
import controllers.Conexao;
import models.Funcionario;
import models.TableModel;
import views.NovoFuncionario;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JScrollPane;
import javax.swing.JButton;
import javax.swing.LayoutStyle.ComponentPlacement;
import java.awt.event.ActionListener;
import java.text.ParseException;
import java.util.List;
import java.awt.event.ActionEvent;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.UIManager;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.ImageIcon;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.swing.JComboBox;
import javax.swing.JFormattedTextField;

public class ListaFuncionario extends JFrame {

	private JPanel contentPane;

	
	private JTable table;
	private JTextField txtCpf;
	private JTextField txtNome;
	private JTextField textEndereco;
	private JTextField textTelefone;
	private List<Funcionario> funcionarioList;
	private JMenuBar menuBar;
	private JMenu menu, subMenu1;
	private JMenuItem menuItem;
	JMenuItem cadastrarFuncionario;
	JMenuItem cadastrarLeitor;
	JMenuItem cadastrarLivro;
	private String cargo;
	
	public static void main(String[] args) {
		try {
            //here you can put the selected theme class name in JTattoo
            UIManager.setLookAndFeel("com.jtattoo.plaf.mint.MintLookAndFeel");
 
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(ListaFuncionario.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(ListaFuncionario.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(ListaFuncionario.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(ListaFuncionario.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ListaFuncionario frame = new ListaFuncionario();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	
	public ListaFuncionario()  {
		
	    
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 590, 546);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		
		JScrollPane scrollPane = new JScrollPane();
		
		//******************Criando Menus*********************: 
		
		JMenuBar menuBar = new JMenuBar();
		menuBar.setBounds(0, 0, 97, 21);
		contentPane.add(menuBar);
		
		JMenu menu = new JMenu("Navegação");
		menuBar.add(menu);
		
		
		JMenu subMenu1 = new JMenu("Cadastrar:");
		menu.add(subMenu1);
		
		cadastrarFuncionario = new JMenuItem("Funcionario");
		cadastrarFuncionario.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try {
					NovoFuncionario f = new NovoFuncionario();
					f.setVisible(true);
					dispose();
				} catch (ParseException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
			}
		});
		subMenu1.add(cadastrarFuncionario);
		
		cadastrarLeitor = new JMenuItem("Leitor");
		subMenu1.add(cadastrarLeitor);
		
		cadastrarLivro = new JMenuItem("Livro");
		subMenu1.add(cadastrarLivro);
		
		//***************************Fim MENU.../////
		
		
	
		
		
		
		//**********************Jtexts configuração****************************
		try {
			txtCpf = new JFormattedTextField(new MaskFormatter("###.###.###-##"));
		} catch (ParseException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		txtCpf.setText("Por CPF");
		txtCpf.setColumns(10);
		txtCpf.setEditable(true);
		String cpf = txtCpf.toString();
		txtNome = new JTextField();
		txtNome.setEditable(true);
		txtNome.setColumns(10);
		
		textEndereco = new JTextField();
		textEndereco.setEditable(true);
		textEndereco.setColumns(10);
		
		try {
			textTelefone = new JFormattedTextField(new MaskFormatter("(##)####-####"));
		} catch (ParseException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		textTelefone.setEditable(true);
		textTelefone.setColumns(10);
		
		
		
		//************************************Labels e combobox
		
		JLabel lblCpf = new JLabel("CPF:");
		
		JLabel lblNewLabel_1 = new JLabel("Nome:");
		
		JLabel lblNewLabel_2 = new JLabel("Cargo:");
		//montando combobox:
		String [] cargos = {"Auxiliar","Motorista","Secretario","Analista"}; 
		JComboBox comboBox = new JComboBox(cargos);
		comboBox.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JComboBox cb = (JComboBox)e.getSource();
				cargo = (String)cb.getSelectedItem();
				
			}
		});
		comboBox.setEditable(false);
		
		JLabel lblNewLabel_3 = new JLabel("Endere\u00E7o:");
		
		JLabel lblNewLabel_4 = new JLabel("Telefone:");
		
		
		///*********************BOTOES*********************************
		
		//*********************BOTAO SALVAR*****
		JButton btnSalvar = new JButton("Salvar");
		btnSalvar.setIcon(new ImageIcon(ListaFuncionario.class.getResource("/img/save.png")));
		btnSalvar.setSelectedIcon(new ImageIcon(ListaFuncionario.class.getResource("/img/save.png")));
		btnSalvar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				Funcionario f = new Funcionario();
				DaoFuncionario dao = new DaoFuncionario();
				f.setCpf(txtCpf.getText().replaceAll("\\D", ""));	
				f.setNome(txtNome.getText());
				f.setCargo(cargo);
				f.setEndereco(textEndereco.getText());
				f.setTelefone(textTelefone.getText().replaceAll("\\D",""));
				dao.criar(f);
				
				
				
			}
		});
		
		///**********************BOTAO PESQUISAR----------
		JButton btnPesquisar = new JButton("Pesquisar");
		btnPesquisar.setIcon(new ImageIcon(ListaFuncionario.class.getResource("/img/find.png")));
		btnPesquisar.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e) {
				
				funcionarioList = new DaoFuncionario().pesquisarCpf(txtCpf.getText().replaceAll("\\D",""));
				if(funcionarioList != null) {
					table.setModel(new TableModel(funcionarioList));
				}
			}
		});
		
		//*******************************BOTAO LISTAR TODOS**********
		JButton btnListar = new JButton("Ver Todos");
		btnListar.setIcon(new ImageIcon(ListaFuncionario.class.getResource("/img/Clipboard.png")));
		btnListar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				listar();	
			}
		});
		btnListar.setSelectedIcon(new ImageIcon(ListaFuncionario.class.getResource("/img/new.png")));
		
		//****************************BOTAO LIMPAR CAMPOS****************
		JButton btnLimpar = new JButton("Limpar");
		btnLimpar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				txtCpf.setText("");
				txtNome.setText("");
				textEndereco.setText("");
				textTelefone.setText("");
			}
		});
		btnLimpar.setIcon(new ImageIcon(ListaFuncionario.class.getResource("/img/refresh.png")));
		
		///********************************BOTAO EDITAR *****************
		JButton btnEditar = new JButton("Editar");
		btnEditar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
				Funcionario f = new Funcionario();
				f.setCpf(txtCpf.getText().replaceAll("\\D", ""));
				f.setNome(txtNome.getText());
				f.setCargo(cargo);
				f.setEndereco(textEndereco.getText());
				f.setTelefone(textTelefone.getText().replaceAll("\\D",""));
				DaoFuncionario dao = new DaoFuncionario();
				dao.editar(f);
				
				
				}
				catch(Exception ex){
					
				}
				
			}
		});
		btnEditar.setIcon(new ImageIcon(ListaFuncionario.class.getResource("/img/edit.png")));
		
		//**************************BOTAO REMOVER FUNCIONARIO******
		JButton btnRemover = new JButton("Remover");
		btnRemover.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
					Funcionario f = new Funcionario();
					f.setCpf(txtCpf.getText().replaceAll("\\D", ""));
					DaoFuncionario dao = new DaoFuncionario();
					dao.deletar(f);
				
			}
		});
		btnRemover.setIcon(new ImageIcon(ListaFuncionario.class.getResource("/img/trash.png")));
		
		//CAMPOS DE LOCALIZAÇÕES DO JFRAME
		GroupLayout gl_contentPane = new GroupLayout(contentPane);
		gl_contentPane.setHorizontalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addContainerGap()
					.addComponent(btnSalvar)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(btnEditar)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(btnLimpar)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(btnRemover)
					.addPreferredGap(ComponentPlacement.RELATED, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
					.addComponent(btnListar)
					.addGap(113))
				.addGroup(gl_contentPane.createSequentialGroup()
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_contentPane.createSequentialGroup()
							.addComponent(lblNewLabel_1)
							.addPreferredGap(ComponentPlacement.UNRELATED)
							.addComponent(txtNome, 200, 200, 200))
						.addGroup(gl_contentPane.createSequentialGroup()
							.addComponent(lblNewLabel_2)
							.addPreferredGap(ComponentPlacement.UNRELATED)
							.addComponent(comboBox, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
						.addGroup(gl_contentPane.createSequentialGroup()
							.addComponent(lblNewLabel_3)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(textEndereco, GroupLayout.PREFERRED_SIZE, 221, GroupLayout.PREFERRED_SIZE))
						.addGroup(gl_contentPane.createSequentialGroup()
							.addComponent(lblNewLabel_4)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(textTelefone, GroupLayout.PREFERRED_SIZE, 109, GroupLayout.PREFERRED_SIZE)))
					.addContainerGap(290, Short.MAX_VALUE))
				.addComponent(scrollPane, GroupLayout.DEFAULT_SIZE, 564, Short.MAX_VALUE)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addContainerGap()
					.addComponent(lblCpf)
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addComponent(txtCpf, GroupLayout.PREFERRED_SIZE, 99, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addComponent(btnPesquisar)
					.addContainerGap(313, Short.MAX_VALUE))
		);
		gl_contentPane.setVerticalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addGap(24)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblCpf)
						.addComponent(txtCpf, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(btnPesquisar))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblNewLabel_1)
						.addComponent(txtNome, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblNewLabel_2)
						.addComponent(comboBox, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(17)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblNewLabel_3)
						.addComponent(textEndereco, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblNewLabel_4)
						.addComponent(textTelefone, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(20)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(btnSalvar)
						.addComponent(btnLimpar)
						.addComponent(btnListar)
						.addComponent(btnEditar)
						.addComponent(btnRemover))
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addComponent(scrollPane, GroupLayout.PREFERRED_SIZE, 241, GroupLayout.PREFERRED_SIZE)
					.addContainerGap())
		);
		
		//********************METODO SELECIONAR ITEM COM MOUSE (MOUSECLICK)*********************************
		table = new JTable();
		table.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				String click_funcio = ""+ table.getValueAt(table.getSelectedRow(),1);
				
				Connection con = Conexao.getConecta();
				PreparedStatement stmt = null;
				ResultSet rs = null;
				String sql = ("SELECT * FROM FUNCIONARIO WHERE CPF = '" + click_funcio + "'");
				try {
					
					stmt = con.prepareStatement(sql);
					rs = stmt.executeQuery();
					while(rs.next()) {
					txtCpf.setText(rs.getString("CPF"));
					txtNome.setText(rs.getString("nome"));
					comboBox.setSelectedItem(rs.getString("cargo"));
					textEndereco.setText(rs.getString("endereco"));
					textTelefone.setText(rs.getString("telefone"));
					
					
					}
					stmt.close();
				} catch (SQLException e) {
					JOptionPane.showMessageDialog(null,"Erro ao selecionar Funcionario");
					e.printStackTrace();
				}
				
			}
		});
		
		scrollPane.setViewportView(table);
		contentPane.setLayout(gl_contentPane);
		
		
		
		
			
		
	}
	public void listar() {
		funcionarioList = new DaoFuncionario().listar();
		if(funcionarioList != null) {
			table.setModel(new TableModel(funcionarioList));
		}
		
	}
	public void pesquisar(String cpf) {
		funcionarioList = new DaoFuncionario().pesquisarCpf(cpf);
		table.setModel(new TableModel(funcionarioList));
		
	}
}
