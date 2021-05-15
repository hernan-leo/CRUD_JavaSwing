package views;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import controllers.DaoFuncionario;
import models.Funcionario;
import models.Livro;
import models.Sessao;

import java.awt.Button;
import java.awt.event.ActionListener;
import java.text.ParseException;
import java.util.List;
import java.awt.event.ActionEvent;
import javax.swing.JLabel;

public class MainFuncionario extends JFrame {

	private JPanel contentPane;
	private JButton btnLivro;
	private JButton btnEmprestimo;
	private JLabel lblNome, lblID;
	String nome;
	String cpf = Sessao.getInstance().getFuncionario().getCpf();


	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MainFuncionario frame = new MainFuncionario();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public MainFuncionario() {
	
	DaoFuncionario dao = new DaoFuncionario();
	Funcionario funcionario = dao.setarNomeeId(cpf);
	
		setTitle("Area do Funcionario");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 505, 370);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		btnLivro = new JButton();
		btnLivro.setBounds(54, 87, 90, 87);
		btnLivro.setIcon(new ImageIcon(Login.class.getResource("/img/icons8-estante-de-livros-64.png")));
		btnLivro.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg) {
				ListaLivro abrir;
				try {
					abrir = new ListaLivro();
					abrir.setVisible(true);
				} catch (ParseException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
				
			}
		});
		contentPane.add(btnLivro);
		
		JButton btnUsuarios = new JButton();
		btnUsuarios.setIcon(new ImageIcon(Login.class.getResource("/img/Imagem_Usuario.png")));
		
		btnUsuarios.setBounds(206, 87, 90, 87);
		contentPane.add(btnUsuarios);
		
		btnEmprestimo = new JButton();
		btnEmprestimo.setIcon(new ImageIcon(Login.class.getResource("/img/emprestar_livro.png")));
		btnEmprestimo.setBounds(363, 87, 90, 87);
		contentPane.add(btnEmprestimo);
		
		JLabel lblNewLabel = new JLabel("Cadastro de Livros");
		lblNewLabel.setBounds(45, 185, 151, 14);
		contentPane.add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("Cadastro de Us\u00FAarios");
		lblNewLabel_1.setBounds(206, 185, 115, 14);
		contentPane.add(lblNewLabel_1);
		
		JLabel lblLogin = new JLabel("Login:");
		lblLogin.setBounds(10, 236, 46, 14);
		contentPane.add(lblLogin);
		
		JLabel lblID = new JLabel("");
		lblID.setText(String.valueOf(funcionario.getId_Funcionario()));
		lblID.setBounds(43, 236, 46, 14);
		contentPane.add(lblID);
		
		
	
		JLabel lblNome = new JLabel("");
		lblNome.setText(funcionario.getNome());
	
		lblNome.setBounds(98, 236, 46, 14);
		contentPane.add(lblNome);
		
		
	
		
	
		
	
		
	}
	
	
	
}
