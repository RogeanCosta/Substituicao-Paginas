package Janelas;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.awt.event.ActionEvent;

import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.plaf.FileChooserUI;

import Programa.Aplicacao;

public class Frames {

	public JFrame frame;
	private JTextField Q1;
	private JTextField Q2;
	private JTextField R;
	private JTextField Caminho;

	/**
	 * Create the application.
	 */
	public Frames() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 268, 374);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		frame.setResizable(false);
		
		JLabel lblNewLabel = new JLabel("Q1:");
		lblNewLabel.setFont(new Font("Arial Black", Font.PLAIN, 15));
		lblNewLabel.setBounds(40, 29, 45, 13);
		frame.getContentPane().add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("Q2: ");
		lblNewLabel_1.setFont(new Font("Arial Black", Font.PLAIN, 15));
		lblNewLabel_1.setBounds(40, 75, 45, 13);
		frame.getContentPane().add(lblNewLabel_1);
		
		JLabel lblNewLabel_2 = new JLabel("R:");
		lblNewLabel_2.setFont(new Font("Arial Black", Font.PLAIN, 15));
		lblNewLabel_2.setBounds(51, 122, 45, 13);
		frame.getContentPane().add(lblNewLabel_2);
		
		Q1 = new JTextField();
		Q1.setBounds(95, 23, 96, 29);
		frame.getContentPane().add(Q1);
		Q1.setColumns(10);
		
		Q2 = new JTextField();
		Q2.setColumns(10);
		Q2.setBounds(95, 69, 96, 29);
		frame.getContentPane().add(Q2);
		
		R = new JTextField();
		R.setColumns(10);
		R.setBounds(95, 116, 96, 29);
		frame.getContentPane().add(R);
		
		JButton btnNewButton = new JButton("Iniciar Aplicação");
		
		btnNewButton.setBackground(new Color(0, 204, 153));
		btnNewButton.setFont(new Font("Arial Black", Font.PLAIN, 13));
		btnNewButton.setBounds(36, 295, 182, 29);
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evt) {
				Aplicacao.Q1 = Integer.parseInt(Q1.getText());
				Aplicacao.Q2 = Integer.parseInt(Q2.getText());
				Aplicacao.R = Integer.parseInt(R.getText());
		
				Aplicacao.parou.release();
			}
		});

		frame.getContentPane().add(btnNewButton);
		
		JButton btnAbrirArquivo = new JButton("Abrir Arquivo");
		btnAbrirArquivo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				// Exibe algo semelhante ao windows explorer para procurar o arquivo
				JFileChooser selecionaArquivo = new JFileChooser();
				selecionaArquivo.setFileSelectionMode(JFileChooser.FILES_ONLY);
				
				// Só é possível obter arquivos txt
				FileNameExtensionFilter filtro = new FileNameExtensionFilter("Somente .txt", "txt");
				selecionaArquivo.setAcceptAllFileFilterUsed(false);
				selecionaArquivo.addChoosableFileFilter(filtro);
				
				// Caso o usuário pressione no botão de cancelar: retorna a janela anterior
				int result = selecionaArquivo.showOpenDialog(selecionaArquivo);
				
				if(result == JFileChooser.CANCEL_OPTION)
					System.exit(1);
				
				File arquivo = selecionaArquivo.getSelectedFile();
				
				try {
					Aplicacao.conteudo = Files.readString(Paths.get(arquivo.toURI()));
				} catch (IOException e1) {
					e1.printStackTrace();
				}
				
				Caminho.setHorizontalAlignment(JTextField.CENTER);
				Caminho.setText(arquivo.getName());
			}
		});
		btnAbrirArquivo.setFont(new Font("Arial Black", Font.PLAIN, 13));
		btnAbrirArquivo.setBackground(new Color(0, 204, 153));
		btnAbrirArquivo.setBounds(50, 189, 152, 29);
		frame.getContentPane().add(btnAbrirArquivo);
		
		Caminho = new JTextField();
		Caminho.setEditable(false);
		Caminho.setColumns(10);
		Caminho.setBounds(10, 229, 229, 29);
		frame.getContentPane().add(Caminho);
	}
}
