package views;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.UIManager;
import javax.swing.border.EmptyBorder;
import javax.swing.text.MaskFormatter;

import controllers.DaoFuncionario;
import models.Funcionario;

import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JTextField;
import javax.swing.JComboBox;
import javax.swing.JFormattedTextField;

import java.awt.event.ActionListener;
import java.text.ParseException;
import java.awt.event.ActionEvent;
import javax.swing.JButton;
import javax.swing.ImageIcon;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPopupMenu;
import java.awt.Component;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JPasswordField;

public class NovoFuncionario extends JFrame {

	private JPanel contentPane;
	private JFormattedTextField textCpf;
	private JTextField textNome;
	private JTextField textEndereco;
	private JTextField textTelefone;
	private JTextField txtsenhaAberta;
	private String cargo;
	JMenuItem submenu;
	JMenuItem cadastrarFuncionario;
	JMenuItem cadastrarLeitor;
	JMenuItem cadastrarLivro;
	private JPasswordField txtsenha;
	
	
  
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
            //here you can put the selected theme class name in JTattoo
            UIManager.setLookAndFeel("com.jtattoo.plaf.mint.MintLookAndFeel");
 
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(NovoFuncionario.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(NovoFuncionario.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(NovoFuncionario.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(NovoFuncionario.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					NovoFuncionario frame = new NovoFuncionario();
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
	public NovoFuncionario() throws ParseException {
		setTitle("Novo Funcionario");
		Funcionario f = new Funcionario();
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 342, 430);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		
		JLabel lblNovoFuncionario = new JLabel("NOVO FUNCIONARIO");
		lblNovoFuncionario.setBounds(101, 31, 125, 14);
		contentPane.add(lblNovoFuncionario);
		
		JLabel lblCpf = new JLabel("CPF:");
		lblCpf.setBounds(27, 80, 46, 14);
		contentPane.add(lblCpf);
		
		textCpf = new JFormattedTextField(new MaskFormatter("###.###.###-##"));
		textCpf.setBounds(64, 77, 104, 20);
		contentPane.add(textCpf);
		textCpf.setColumns(10);
		
		
		
		
		
		
		JLabel lblNone = new JLabel("Nome:");
		lblNone.setBounds(27, 105, 46, 14);
		contentPane.add(lblNone);
		
		textNome = new JTextField();
		textNome.setBounds(64, 102, 92, 20);
		contentPane.add(textNome);
		textNome.setColumns(10);
		
		JLabel lblNewLabel = new JLabel("Cargo:");
		lblNewLabel.setBounds(27, 130, 46, 14);
		contentPane.add(lblNewLabel);
		
		
		
		String [] itens = {"Auxiliar","Motorista","Secretario","Analista"};
		
		JComboBox comboBox = new JComboBox(itens);
		comboBox.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				JComboBox cb = (JComboBox)e.getSource();
				cargo = (String)cb.getSelectedItem();
				
				
				
				
			}
		});
		comboBox.setBounds(64, 127, 92, 20);
		contentPane.add(comboBox);
		
		JLabel lblNewLabel_1 = new JLabel("Endere\u00E7o:");
		lblNewLabel_1.setBounds(10, 154, 63, 14);
		contentPane.add(lblNewLabel_1);
		
		textEndereco = new JTextField();
		textEndereco.setBounds(64, 155, 204, 20);
		contentPane.add(textEndereco);
		textEndereco.setColumns(10);
		
		JLabel lblNewLabel_2 = new JLabel("Telefone:");
		lblNewLabel_2.setBounds(10, 179, 56, 14);
		contentPane.add(lblNewLabel_2);
		
		textTelefone = new JFormattedTextField(new MaskFormatter("(##) ####-####"));
		textTelefone.setBounds(64, 179, 92, 20);
		contentPane.add(textTelefone);
		textTelefone.setColumns(10);
		
		txtsenha = new JPasswordField();
		
	
		
		
		
	
		
	
		
		JButton btnCadastrar = new JButton("Cadastrar");
		btnCadastrar.setIcon(new ImageIcon(NovoFuncionario.class.getResource("/img/save.png")));
		btnCadastrar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				 
				f.setCpf(textCpf.getText().replaceAll("\\D",""));
				f.setNome(textNome.getText());
				f.setCargo(cargo);
				f.setEndereco(textEndereco.getText());
				f.setTelefone(textTelefone.getText().replaceAll("\\D",""));
				f.setSenha(new String(txtsenha.getPassword()));			
				DaoFuncionario dao = new DaoFuncionario();
				dao.criar(f);
				
			}
		});
		btnCadastrar.setBounds(64, 241, 101, 23);
		contentPane.add(btnCadastrar);
		
		JMenuBar menuBar = new JMenuBar();
		menuBar.setBounds(0, 0, 97, 21);
		contentPane.add(menuBar);
		
		JMenu mnNewMenu = new JMenu("New menu");
		menuBar.add(mnNewMenu);
		
		JMenuItem mntmNewMenuItem = new JMenuItem("Gerenciar Funcionario");
		mntmNewMenuItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				ListaFuncionario abrir = new ListaFuncionario();
				abrir.setVisible(true);
				dispose();
			}
		});
		mnNewMenu.add(mntmNewMenuItem);
		mnNewMenu.addSeparator();
		
		JMenu submenu_1 = new JMenu("Cadastrar:");
		mnNewMenu.add(submenu_1);
		
		
		
		cadastrarFuncionario = new JMenuItem("Funcionario");
		
		submenu_1.add(cadastrarFuncionario);
		
		cadastrarLeitor = new JMenuItem("Leitor");
		submenu_1.add(cadastrarLeitor);
		
		cadastrarLivro = new JMenuItem("Livro");
		submenu_1.add(cadastrarLivro);
		
		JLabel lblSenha = new JLabel("Senha:");
		lblSenha.setBounds(20, 204, 46, 14);
		contentPane.add(lblSenha);
		
		
		txtsenha.setBounds(64, 201, 92, 20);
		contentPane.add(txtsenha);
	}
}

