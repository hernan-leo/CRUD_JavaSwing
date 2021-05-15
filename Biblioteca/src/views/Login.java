package views;


import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import controllers.DaoFuncionario;
import models.Funcionario;
import models.Sessao;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.UIManager;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.JButton;
import javax.swing.JPasswordField;
import javax.swing.JComboBox;

public class Login extends JFrame {

	private JPanel contentPane;
	private JTextField txtCpf;
	private JPasswordField txtSenha;
	String escolha;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
            //here you can put the selected theme class name in JTattoo
            UIManager.setLookAndFeel("com.jtattoo.plaf.mint.MintLookAndFeel");
 
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(Login.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Login.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Login.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Login.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Login frame = new Login();
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
	public Login() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 650, 500);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		
		JLabel lblCpf = new JLabel("CPF:");
		
		txtCpf = new JTextField();
		txtCpf.setColumns(10);
		txtSenha = new JPasswordField();
		char[] chars = txtSenha.getPassword();
		String password = String.valueOf(chars);
		
		JLabel lblSenha = new JLabel("Senha:");
		JLabel lblPerfil = new JLabel("Perfil:");
		//********************INICIO BOTOES
		JButton btnLogar = new JButton("Logar");
		btnLogar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				switch(escolha) {  ///SWITCH PEGA OS ITENS DO COMBOBOX E FAZ A SEPARAÇÃO
				case "Selecione:":
					JOptionPane.showMessageDialog(null,"Escolha um perfil válido");
					break;
				case "Funcionario":
					
				DaoFuncionario dao = new DaoFuncionario();
				
				String cpf = txtCpf.getText();
				String senha = new String (txtSenha.getPassword());
				Funcionario funcionario = dao.logar(cpf, senha);
			
				
				Sessao sessao = Sessao.getInstance();
				sessao.setFuncionario(funcionario);
					
				
				MainFuncionario abrir = new MainFuncionario();
				
				if(funcionario == null) {
					
				JOptionPane.showMessageDialog(null,"CPF ou senha não conferem");
					
				}else {
					abrir.setVisible(true);
				}
				break;
				case "Usuario":
					JOptionPane.showMessageDialog(null,"Não fiz ainda");
					break;
				}
				
				
			}
		});
		
		JButton btnLimpar = new JButton("Limpar");
		//FIM BOTOES
		
		//INICIO COMBOBOX		
		String[] perfil = {"Selecione:","Funcionario","Usuario","Admin"};//<<CRIA ARRAY COM A LISTA DO COMBOBOX
		
		JComboBox comboBox = new JComboBox(perfil);
		comboBox.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg) {
				
				JComboBox cb = (JComboBox)arg.getSource();
				escolha = (String)cb.getSelectedItem();
				
			}
		});
		//FIM COMBOBOX
		
		GroupLayout gl_contentPane = new GroupLayout(contentPane);
		gl_contentPane.setHorizontalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(Alignment.TRAILING, gl_contentPane.createSequentialGroup()
					.addContainerGap(339, Short.MAX_VALUE)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.TRAILING)
						.addGroup(gl_contentPane.createSequentialGroup()
							.addComponent(btnLogar, GroupLayout.PREFERRED_SIZE, 69, GroupLayout.PREFERRED_SIZE)
							.addGap(41)
							.addComponent(btnLimpar))
						.addGroup(gl_contentPane.createSequentialGroup()
							.addGroup(gl_contentPane.createParallelGroup(Alignment.TRAILING)
								.addComponent(lblPerfil)
								.addComponent(lblCpf)
								.addComponent(lblSenha))
							.addGap(18)
							.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
								.addComponent(comboBox, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
								.addComponent(txtSenha, 118, 118, 118)
								.addComponent(txtCpf, GroupLayout.PREFERRED_SIZE, 109, GroupLayout.PREFERRED_SIZE))))
					.addGap(312))
		);
		gl_contentPane.setVerticalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addGap(162)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.TRAILING)
						.addComponent(lblPerfil)
						.addComponent(comboBox, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(txtCpf, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblCpf))
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(txtSenha, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblSenha))
					.addGap(18)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(btnLimpar)
						.addComponent(btnLogar))
					.addContainerGap(353, Short.MAX_VALUE))
		);
		contentPane.setLayout(gl_contentPane);
	}
}
