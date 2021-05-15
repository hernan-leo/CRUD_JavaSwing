package views;

import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFormattedTextField;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.UIManager;
import javax.swing.text.MaskFormatter;

import controllers.DaoFuncionario;
import controllers.DaoLivro;
import models.Funcionario;
import models.Livro;
import models.Sessao;
import net.miginfocom.swing.MigLayout;

public class NovoLivro extends JFrame {
	
	private JPanel panelAdd,panelTable,panelButtons;
	private JLabel lblTitulo, lblGenero,lblEditora,lblAutor,lblValorEstimado,lblDataDeEntrada, lblLogado, lblNome, lblId;
	private JTextField txtTitulo,txtGenero,txtEditora,txtAutor,txtValorEstimado,txtDataDeEntrada;
	private JButton btnCadastrar, btnLimpar;
	String [] genero = {"Selecione:","Programação", "Literatura", "Infantil", "Didatico"};
	private String lgenero;
	String cpf = Sessao.getInstance().getFuncionario().getCpf();
	Date hoje = new Date();
	SimpleDateFormat dataformatada = new SimpleDateFormat("dd/MM/yyyy");
	
	
	
	
	
	//inicio construtor
	public NovoLivro() throws ParseException {
		super ("Cadastro de livros");
		//criando o Frame
		DaoFuncionario dao = new DaoFuncionario();
		Funcionario funcionario = dao.setarNomeeId(cpf);
		String id = String.valueOf(funcionario.getId_Funcionario());
		String nome = funcionario.getNome();
		setContentPane(new JPanel());
		setLayout(null);
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		panelAdd = new JPanel(new MigLayout());
		panelAdd.setBorder(BorderFactory.createTitledBorder("Cadastrar Livros"));
		panelAdd.setBounds(5, 0, 480, 500);
		
		//FIM CRIA FRAME
		//GERANDO JTABLES
		
		lblTitulo = new JLabel ("Titulo");
		lblGenero = new JLabel ("Gênero");
		lblEditora = new JLabel ("Editora");
		lblAutor = new JLabel ("Autor");
		lblValorEstimado = new JLabel("Valor");
		lblDataDeEntrada = new JLabel("Data");
		lblLogado = new JLabel ("Funcionario: ");
		lblId = new JLabel (id);
		lblNome = new JLabel(nome);
		
		
		//FIM JTABLES
		//GERANDO JTEXTFIELDS
		
		txtTitulo = new JTextField(50);//<<"50" largura da caixa
		txtEditora = new JTextField(50);
		txtAutor = new JTextField(50);
		txtValorEstimado = new JTextField(5);
		txtDataDeEntrada = new JTextField(8);
		txtDataDeEntrada.setText(dataformatada.format(hoje));//>>>Inseri a data atual do sistema e formata para String
		dataformatada.format(hoje);
		txtDataDeEntrada.setEditable(false);
		
		//FIM JTEXTFIELDS
		//CRIADO COMBOBOX
		
		JComboBox jbGenero = new JComboBox(genero);
		jbGenero.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JComboBox cb = (JComboBox)e.getSource();
				lgenero = (String)cb.getSelectedItem();
			}
			
			
		});
		//FIM COMBOBOX
		
		//ADICIONANDO OBJETOS NA LABEL "panelAdd"
		panelAdd.add(lblLogado);
		panelAdd.add(lblId);
		panelAdd.add(lblNome, "span, growx");
		panelAdd.add(lblTitulo);
		panelAdd.add(txtTitulo, "span, growx");//<<<span: pula linha, growx: tamanho da caixa.
		
		panelAdd.add(lblGenero);
		panelAdd.add(jbGenero, "span");
		
		panelAdd.add(lblEditora);
		panelAdd.add(txtEditora, "span, growx");
		
		panelAdd.add(lblAutor);
		panelAdd.add(txtAutor, "span, growx");
		
		panelAdd.add(lblValorEstimado);
		panelAdd.add(txtValorEstimado, "wrap para");
		
		
		panelAdd.add(lblDataDeEntrada);
		panelAdd.add(txtDataDeEntrada, "wrap para");
		
		
		
		
		//FIM 
		//BOTOES
		//panelButtons = new JPanel(new MigLayout());
		ClassLoader loader = getClass().getClassLoader();	
		//panelButtons.setBorder(BorderFactory.createEtchedBorder());
		//panelButtons.setBounds(5, 150, 480, 40);
		
		btnCadastrar = new JButton("Cadastrar");
		btnCadastrar.setIcon(new ImageIcon(loader.getResource("img/save.png")));
		btnCadastrar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				float valorFloat = Float.parseFloat(txtValorEstimado.getText());
				int Id_funcionario = Integer.parseInt(lblId.getText());
				try {
				Livro l = new Livro();
				l.setId_Funcionario(Id_funcionario);
				l.setTitulo(txtTitulo.getText());
				l.setGenero(lgenero);
				l.setEditora(txtEditora.getText());
				l.setAutor(txtAutor.getText());
				l.setValorEstimado(valorFloat);
				l.setDataDeEntrada(hoje);
				DaoLivro dao = new DaoLivro();
				dao.criar(l);
				}catch(Exception ex) {
					ex.printStackTrace();
				}
			}
		});
		btnLimpar = new JButton("Limpar");
		btnLimpar.setIcon(new ImageIcon(loader.getResource("img/new.png")));
		btnLimpar.addActionListener(new ActionListener () {
			public void actionPerformed(ActionEvent e) {
				txtTitulo.setText("");
				txtEditora.setText("");
				txtAutor.setText("");
				txtValorEstimado.setText("");
				
			}
		});
		//FIM BOTOES
		
		//INSERIDO BOTOES NO "panelButtons"
		
		panelAdd.add(btnCadastrar);
		panelAdd.add(btnLimpar);
		
	
		//DIMENSIONA JANELA
		
		add(panelAdd);
		//add(panelButtons);
		setMinimumSize(new Dimension (500,300 ));
		setVisible(true);
		
		
		
		
	}
	
	public static void main(String[] args) throws ParseException {
		try {
            //here you can put the selected theme class name in JTattoo
            UIManager.setLookAndFeel("com.jtattoo.plaf.mint.MintLookAndFeel");
 
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(NovoLivro.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(NovoLivro.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(NovoLivro.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(NovoLivro.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
		new NovoLivro();
	}

	

}
